import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;

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
                        countryLabel.setText(info.country);
                        // Checks to see if selected country is United States
                        // if true the state is displayed as well as city
                        if ("United States".equals(country)) {
                            cityWeatherLabel.setText(info.city + ", " + info.state);
                        } else {
                            // by default just displays city
                            cityWeatherLabel.setText(info.city);
                        }
                        temperatureLabel.setText("Temperature: " + info.temperature);
                        rainLabel.setText("Rain: " + info.rain);
                        windSpeedLabel.setText("Wind Speed: " + info.windSpeed);
                        windDirectionLabel.setText("Wind Direction: " + info.windDirection);
                        weatherCodeLabel.setText("Weather Code: " + info.weatherCode);
                        precipitationLabel.setText("Precipitation Probability: " + info.precipitationProbability);
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
}
