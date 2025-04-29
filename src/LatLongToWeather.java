import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class LatLongToWeather {

    public static WeatherInfo getWeatherByZipCode(String zipCodeWithCountry) {
        try {
            String[] locationData = ZipCodeToLatLong.getLatLong(zipCodeWithCountry);
            if (locationData != null) {
                String latitude = locationData[0];
                String longitude = locationData[1];
                String country = locationData[2];
                String city = locationData[3];
                String state = locationData[4];

                String apiURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude
                        + "&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,weather_code,precipitation_probability&forecast_days=1&wind_speed_unit=mph&temperature_unit=fahrenheit";

                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder content = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    JSONObject jsonResponse = new JSONObject(content.toString());
                    JSONArray temperatures = jsonResponse.getJSONObject("hourly").getJSONArray("temperature_2m");
                    JSONArray rains = jsonResponse.getJSONObject("hourly").getJSONArray("rain");
                    JSONArray windSpeeds = jsonResponse.getJSONObject("hourly").getJSONArray("wind_speed_10m");
                    JSONArray windDirections = jsonResponse.getJSONObject("hourly").getJSONArray("wind_direction_10m");
                    JSONArray weatherCodes = jsonResponse.getJSONObject("hourly").getJSONArray("weather_code");
                    JSONArray precipitationProbabilities = jsonResponse.getJSONObject("hourly").getJSONArray("precipitation_probability");

                    int latestIndex = temperatures.length() - 1;

                    WeatherInfo info = new WeatherInfo();
                    info.country = country;
                    info.city = city;
                    info.state = state;
                    info.temperature = String.format("%.1f°F", temperatures.getDouble(latestIndex));
                    info.rain = String.format("%.1f mm", rains.getDouble(latestIndex));
                    info.windSpeed = String.format("%.1f mph", windSpeeds.getDouble(latestIndex));
                    info.windDirection = WeatherData.getCompassDirection(windDirections.getInt(latestIndex));
                    info.weatherCode = WeatherData.getWeatherDescription(weatherCodes.getInt(latestIndex));
                    info.precipitationProbability = String.format("%.1f%%", precipitationProbabilities.getDouble(latestIndex));
                    return info;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
