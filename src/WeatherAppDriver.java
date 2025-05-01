import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherAppDriver {

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private TextField zipCodeField;

    @FXML
    private Button getWeatherButton;

    @FXML
    private Label countryLabel;

    @FXML
    private Label cityWeatherLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label rainLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private Label windDirectionLabel;

    @FXML
    private Label weatherCodeLabel;

    @FXML
    private Label precipitationLabel;

    @FXML
    private ImageView backgroundImageView; // Reference to ImageView in FXML

    @FXML
    public void initialize() {
        // Loads comboBox with available countries
        ObservableList<String> countries = FXCollections.observableArrayList(
                "Andorra", "Argentina", "American Samoa", "Austria", "Australia",
                "Bangladesh", "Belgium", "Bulgaria", "Brazil", "Canada",
                "Switzerland", "Czech Republic", "Germany", "Denmark", "Dominican Republic",
                "Spain", "Finland", "Faroe Islands", "France", "Great Britain",
                "French Guyana", "Guernsey", "Greenland", "Guadeloupe", "Guatemala",
                "Guam", "Guyana", "Croatia", "Hungary", "Isle of Man", "India",
                "Iceland", "Italy", "Jersey", "Japan", "Liechtenstein", "Sri Lanka",
                "Lithuania", "Luxembourg", "Monaco", "Moldavia", "Marshall Islands",
                "Macedonia", "Northern Mariana Islands", "Martinique", "Mexico", "Malaysia",
                "Holland", "Norway", "New Zealand", "Philippines", "Pakistan", "Poland",
                "Saint Pierre and Miquelon", "Puerto Rico", "Portugal", "French Reunion",
                "Russia", "Sweden", "Slovenia", "Svalbard & Jan Mayen Islands", "Slovak Republic",
                "San Marino", "Thailand", "Turkey", "United States", "Vatican",
                "Virgin Islands", "Mayotte", "South Africa"
        );

        countryComboBox.setItems(countries);
    }

    @FXML
    public void onGetWeatherButtonClick() {
        // Gets zip and country from text field and combo box
        String zip = zipCodeField.getText();
        String country = countryComboBox.getValue();

        if (zip != null && !zip.isEmpty() && country != null && !country.isEmpty()) {
            String countryCode = WeatherData.getURLForCountry(country);

            if (!"Country not found or invalid input!".equals(countryCode)) {
                Task<WeatherInfo> fetchTask = new Task<>() {
                    @Override
                    protected WeatherInfo call() {
                        // Returns as a string that can be deciphered by getWeatherByZipCode
                        String zipCodeWithCountry = zip + "," + countryCode;
                        return LatLongToWeather.getWeatherByZipCode(zipCodeWithCountry);
                    }
                };

                fetchTask.setOnSucceeded(event -> {
                    WeatherInfo info = fetchTask.getValue();
                    if (info != null) {
                        boolean isCityStateSame = info.city != null && info.city.equalsIgnoreCase(info.state);
                        boolean isStateCountrySame = info.state != null && info.state.equalsIgnoreCase(info.country);

                        String locationDisplay;
                        if (isStateCountrySame) {
                            locationDisplay = info.country;
                        } else if (isCityStateSame) {
                            locationDisplay = info.city;
                        } else if ("United States".equals(country)) {
                            locationDisplay = info.city + ", " + info.state;
                        } else {
                            locationDisplay = info.city;
                        }

                        countryLabel.setText(info.country);
                        cityWeatherLabel.setText(locationDisplay);
                        temperatureLabel.setText("Temperature: " + info.temperature);
                        rainLabel.setText("Rain: " + info.rain);
                        windSpeedLabel.setText("Wind Speed: " + info.windSpeed);
                        windDirectionLabel.setText("Wind Direction: " + info.windDirection);
                        weatherCodeLabel.setText("Weather Code: " + info.weatherCode);
                        System.out.println(info.weatherCode);
                        precipitationLabel.setText("Precipitation Probability: " + info.precipitationProbability);

                        // Update background image based on weather condition
                        updateWeatherImage(info.weatherCode);
                    } else {
                        // Error handling if zip code is invalid
                        countryLabel.setText("Invalid Zip Code");
                        cityWeatherLabel.setText("");
                        temperatureLabel.setText("");
                        rainLabel.setText("");
                        windSpeedLabel.setText("");
                        windDirectionLabel.setText("");
                        weatherCodeLabel.setText("");
                        precipitationLabel.setText("");
                    }
                });

                fetchTask.setOnFailed(event -> countryLabel.setText("Error fetching data."));
                new Thread(fetchTask).start();
            } else {
                countryLabel.setText("Invalid Country Code.");
            }
        } else {
            countryLabel.setText("Please enter a valid zip code and select a country.");
        }
    }

    // Method to update background image based on weather condition
    private void updateWeatherImage(String weatherCode) {
        String imageFileName;
        switch (weatherCode) {
            case "Clear sky":
                imageFileName = "clearsky.jpg";
                break;
            case "Mainly clear":
            case "Partly cloudy":
                imageFileName = "partly_cloudy.jpeg";
                break;
            case "Overcast":
                imageFileName = "cloudy.jpg";
                break;
            case "Foggy":
                imageFileName = "foggy.jpg";
                break;
            case "Light Drizzle":
            case "Moderate Drizzle":
            case "Dense Drizzle":
            case "Light Freezing Drizzle":
            case "Dense Freezing Drizzle":
            case "Slight Rain":
            case "Moderate rain":
            case "Heavy Rain":
            case "Light Freezing Rain":
            case "Heavy Freezing Rain":
            case "Slight rain showers":
            case "Moderate rain showers":
            case "Violent rain showers":
                imageFileName = "rainy.jpg";
                break;
            case "Slight snow fall":
            case "Moderate snow fall":
            case "Heavy and intense snow":
            case "Snow grains":
            case "Slight snow showers":
            case "Moderate snow showers":
                imageFileName = "snowy.webp";
                break;
            case "Thunderstorms":
            case "Thunderstorms with slight hail":
            case "Thunderstorms with heavy hail":
                imageFileName = "stormy.jpg";
                break;
            default:
                imageFileName = "default.jpg";
                break;
        }
        // Load the appropriate image based on the weather code
        Image newImage = new Image(getClass().getResourceAsStream("/images/" + imageFileName));
        backgroundImageView.setImage(newImage);
    }
}
