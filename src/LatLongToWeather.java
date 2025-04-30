import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.json.JSONArray;
import org.json.JSONObject;

public class LatLongToWeather {

    public static WeatherInfo getWeatherByZipCode(String zipCodeWithCountry) {
        try {
            // Gets array of information from ZipCodeToLatLong
            String[] locationData = ZipCodeToLatLong.getLatLong(zipCodeWithCountry);
            if (locationData != null) {
                // Reassigns information to Strings
                String latitude = locationData[0];
                String longitude = locationData[1];
                String country = locationData[2];
                String city = locationData[3];
                String state = locationData[4];

                // Inputs longitude and latitude into apiURL
                String apiURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude
                        + "&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,weather_code,precipitation_probability&forecast_days=1&wind_speed_unit=mph&temperature_unit=fahrenheit";
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Tests response and gets information
                if (conn.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder content = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    // Puts the jsonResponse into an JSONArray
                    JSONObject jsonResponse = new JSONObject(content.toString());
                    JSONArray times = jsonResponse.getJSONObject("hourly").getJSONArray("time");
                    JSONArray temperatures = jsonResponse.getJSONObject("hourly").getJSONArray("temperature_2m");
                    JSONArray rains = jsonResponse.getJSONObject("hourly").getJSONArray("rain");
                    JSONArray windSpeeds = jsonResponse.getJSONObject("hourly").getJSONArray("wind_speed_10m");
                    JSONArray windDirections = jsonResponse.getJSONObject("hourly").getJSONArray("wind_direction_10m");
                    JSONArray weatherCodes = jsonResponse.getJSONObject("hourly").getJSONArray("weather_code");
                    JSONArray precipitationProbabilities = jsonResponse.getJSONObject("hourly").getJSONArray("precipitation_probability");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);

                    // Uses time array in JSON to find the closest index
                    // then uses that index to display the weather data closest to the current time
                    int closestIndex = -1;
                    long smallestDiff = Long.MAX_VALUE;
                    for (int i = 0; i < times.length(); i++) {
                        ZonedDateTime forecastTime = LocalDateTime.parse(times.getString(i), formatter).atZone(ZoneOffset.UTC);
                        long diff = Math.abs(Duration.between(nowUTC, forecastTime).toMinutes());
                        if (diff < smallestDiff) {
                            smallestDiff = diff;
                            closestIndex = i;
                        }
                    }

                    if (closestIndex == -1) {
                        throw new RuntimeException("No suitable time found in weather data.");
                    }

                    // Uses WeatherInfo class to store information using closestIndex
                    WeatherInfo info = new WeatherInfo();
                    info.country = country;
                    info.city = city;
                    info.state = state;
                    info.temperature = String.format("%.1f°F", temperatures.getDouble(closestIndex));
                    info.rain = String.format("%.1f mm", rains.getDouble(closestIndex));
                    info.windSpeed = String.format("%.1f mph", windSpeeds.getDouble(closestIndex));
                    info.windDirection = WeatherData.getCompassDirection(windDirections.getInt(closestIndex));
                    info.weatherCode = WeatherData.getWeatherDescription(weatherCodes.getInt(closestIndex));
                    info.precipitationProbability = String.format("%.1f%%", precipitationProbabilities.getDouble(closestIndex));
                    return info;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
