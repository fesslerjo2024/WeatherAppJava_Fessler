import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class ZipCodeToLatLong {

    public static String[] getLatLong(String zipCode) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Country: ");
            String countryInput = scanner.nextLine();

            String URL = WeatherData.getURLForCountry(countryInput);


            String apiURL = "https://" + URL + zipCode;
            System.out.println(apiURL);
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 status OK
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                // Parse the response
                JSONObject jsonResponse = new JSONObject(content.toString());
                String latitude = jsonResponse.getJSONArray("places").getJSONObject(0).getString("latitude");
                String longitude = jsonResponse.getJSONArray("places").getJSONObject(0).getString("longitude");

                // Returning the latitude and longitude as a string array
                return new String[]{latitude, longitude};

            } else {
                System.out.println("Error: Unable to fetch data for zip code " + zipCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null; // Return null in case of error
    }
}
