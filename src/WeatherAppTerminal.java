import java.util.Scanner;

public class WeatherAppTerminal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Enter Zip Code and Country Code");
            System.out.println("2. List Supported Country Codes");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter a Zip Code and Country Code");
                    System.out.println("Example: 68780,US");
                    String input = scanner.nextLine();
                    LatLongToWeather.getWeatherByZipCode(input);
                    break;

                case 2:
                    // Lists all the available countries as well as the codes
                    // required to make
                    WeatherData.listSupportedCountryCodes();
                    break;

                case 3:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
