// API Documentation: https://zippopotam.us/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ZipCodeToLatLong {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter zip code: ");

        String zipCode = scanner.next();

        try {
            String apiURL = "https://api.zippopotam.us/us/" + zipCode;
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
                String city = jsonResponse.getJSONArray("places").getJSONObject(0).getString("place name");
                String state = jsonResponse.getJSONArray("places").getJSONObject(0).getString("state abbreviation");

                System.out.println();
                System.out.println(city + ", " + state);
                System.out.println("Lattitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            } else {
                System.out.println("Error: Unable to fetch data from " + zipCode + " try again!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

// return an array of string and from that parse them out into a double