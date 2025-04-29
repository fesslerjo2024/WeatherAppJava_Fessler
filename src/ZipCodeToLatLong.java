import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ZipCodeToLatLong {

    public static String[] getLatLong(String input) {
        try {
            String[] parts = input.split(",");
            String zipCode = parts[0].trim();
            String countryCode = parts[1].trim();

            String urlCode = countryCode.toLowerCase();
            String apiURL = "https://api.zippopotam.us/" + urlCode + "/" + zipCode;

            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }

                in.close();
                conn.disconnect();

                JSONObject jsonResponse = new JSONObject(content.toString());
                JSONObject place = jsonResponse.getJSONArray("places").getJSONObject(0);

                String latitude = place.getString("latitude");
                String longitude = place.getString("longitude");
                String city = place.getString("place name");
                String state = place.getString("state");
                String country = jsonResponse.getString("country");

                return new String[]{latitude, longitude, country, city, state};
            } else {
                System.out.println("Error: Unable to fetch data for zip code " + zipCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
