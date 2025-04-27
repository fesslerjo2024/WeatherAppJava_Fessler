import java.util.Scanner;

public class WeatherAppDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Zip Code: ");
        String zipCode = scanner.nextLine();

        LatLongToWeather.getWeatherByZipCode(zipCode);

    }
}