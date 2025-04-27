import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class LatLongToWeather {

    public static void getWeatherByZipCode(String zipCode) {
        try {
            // Step 1: Get latitude and longitude from the zip code
            String[] latLong = ZipCodeToLatLong.getLatLong(zipCode);
            if (latLong != null) {
                String latitude = latLong[0];
                String longitude = latLong[1];

                // Step 2: Construct the weather API URL using latitude and longitude
                String apiURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude
                        + "&hourly=temperature_2m,rain,wind_speed_10m,wind_direction_10m,weather_code,precipitation_probability&forecast_days=1&wind_speed_unit=mph&temperature_unit=fahrenheit";

                // Step 3: Make the API request and get the response
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    // Step 4: Read the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    // Step 5: Parse the response
                    JSONObject jsonResponse = new JSONObject(content.toString());
                    JSONArray temperatures = jsonResponse.getJSONObject("hourly").getJSONArray("temperature_2m");
                    JSONArray rains = jsonResponse.getJSONObject("hourly").getJSONArray("rain");
                    JSONArray windSpeeds = jsonResponse.getJSONObject("hourly").getJSONArray("wind_speed_10m");
                    JSONArray windDirections = jsonResponse.getJSONObject("hourly").getJSONArray("wind_direction_10m");
                    JSONArray weatherCodes = jsonResponse.getJSONObject("hourly").getJSONArray("weather_code");
                    JSONArray precipitationProbabilities = jsonResponse.getJSONObject("hourly").getJSONArray("precipitation_probability");

                    // Get the index of the last element (latest data)
                    int latestIndex = temperatures.length() - 1;

                    // Get the latest temperature, rain, wind speed, wind direction, weather code, and precipitation probability
                    double latestTemperature = temperatures.getDouble(latestIndex);
                    double latestRain = rains.getDouble(latestIndex);
                    double latestWindSpeed = windSpeeds.getDouble(latestIndex);
                    int latestWindDirection = windDirections.getInt(latestIndex);
                    int latestWeatherCode = weatherCodes.getInt(latestIndex);
                    double latestPrecipitationProbability = precipitationProbabilities.getDouble(latestIndex);

                    // Directly call the static getCompassDirection method from WeatherData class
                    String windCompassDirection = WeatherData.getCompassDirection(latestWindDirection);

                    // Format the values (e.g., to one decimal place)
                    String formattedTemperature = String.format("%.1f", latestTemperature);
                    String formattedRain = String.format("%.1f", latestRain);
                    String formattedWindSpeed = String.format("%.1f", latestWindSpeed);
                    String formattedPrecipitationProbability = String.format("%.1f", latestPrecipitationProbability);

                    // Step 6: Print the weather data
                    System.out.println("Most Recent Weather Forecast:");
                    System.out.println("Temperature: " + formattedTemperature + "°F");
                    System.out.println("Rain: " + formattedRain + " mm");
                    System.out.println("Wind Speed: " + formattedWindSpeed + " mph");
                    System.out.println("Wind Direction: " + windCompassDirection);
                    System.out.println("Weather Code: " + latestWeatherCode);
                    System.out.println("Precipitation Probability: " + formattedPrecipitationProbability + "%");

                } else {
                    System.out.println("Error: Unable to fetch weather data. Response Code: " + responseCode);
                }
            } else {
                System.out.println("Error: Unable to retrieve coordinates for zip code " + zipCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
