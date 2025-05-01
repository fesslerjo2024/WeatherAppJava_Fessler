public class WeatherData {

    private String location;
    private double temperature;
    private static String weatherDescription;
    private String state;
    private String placeName;
    private String country;
    private double windDirection;
    private double windSpeed;

    // Constructor to initialize WeatherData
    public WeatherData(String location, double temperature, String weatherDescription, double windDirection, double windSpeed) {
        this.location = location;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    // Getter and Setter methods
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public static String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Helper method to convert degrees (angles) to wind direction
    public static String getCompassDirection(double angle) {
        String[] directions = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest", "North"};
        return directions[(int) Math.round(((angle % 360) / 45))];
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "location='" + location + '\'' +
                ", temperature=" + temperature +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                ", state='" + state + '\'' +
                ", placeName='" + placeName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    // Method to map weather codes to descriptions
    public static String getWeatherDescription(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return "Clear sky";
            case 1:
                return "Mainly clear";
            case 2:
                return "Partly cloudy";
            case 3:
                return "Overcast";
            case 45:
            case 48:
                return "Foggy";
            case 51:
                return "Light Drizzle";
            case 53:
                return "Moderate Drizzle";
            case 55:
                return "Dense Drizzle";
            case 56:
                return "Light Freezing Drizzle";
            case 57:
                return "Dense Freezing Drizzle";
            case 61:
                return "Slight Rain";
            case 63:
                return "Moderate rain";
            case 65:
                return "Heavy Rain";
            case 66:
                return "Light Freezing Rain";
            case 67:
                return "Heavy Freezing Rain";
            case 71:
                return "Slight snow fall";
            case 73:
                return "Moderate snow fall";
            case 75:
                return "Heavy and intense snow";
            case 77:
                return "Snow grains";
            case 80:
                return "Slight rain showers";
            case 81:
                return "Moderate rain showers";
            case 82:
                return "Violent rain showers";
            case 85:
                return "Slight snow showers";
            case 86:
                return "Moderate snow showers";
            case 95:
                return "Thunderstorms";
            case 96:
                return "Thunderstorms with slight hail";
            case 99:
                return "Thunderstorms with heavy hail";
            default:
                return "Unknown weather condition";
        }
    }

    // Method to check listBox input and assign weather code to it
    public static String getURLForCountry(String countryInput) {
        String country = countryInput.toLowerCase().trim();

        switch (country.toLowerCase()) {
            case "andorra":
                return "AD";
            case "argentina":
                return "AR";
            case "american samoa":
                return "AS";
            case "austria":
                return "AT";
            case "australia":
                return "AU";
            case "bangladesh":
                return "BD";
            case "belgium":
                return "BE";
            case "bulgaria":
                return "BG";
            case "brazil":
                return "BR";
            case "canada":
                return "CA";
            case "switzerland":
                return "CH";
            case "czech republic":
                return "CZ";
            case "germany":
                return "DE";
            case "denmark":
                return "DK";
            case "dominican republic":
                return "DO";
            case "spain":
                return "ES";
            case "finland":
                return "FI";
            case "faroe islands":
                return "FO";
            case "france":
                return "FR";
            case "great britain":
                return "GB";
            case "french guyana":
                return "GF";
            case "guernsey":
                return "GG";
            case "greenland":
                return "GL";
            case "guadeloupe":
                return "GP";
            case "guatemala":
                return "GT";
            case "guam":
                return "GU";
            case "guyana":
                return "GY";
            case "croatia":
                return "HR";
            case "hungary":
                return "HU";
            case "isle of man":
                return "IM";
            case "india":
                return "IN";
            case "iceland":
                return "IS";
            case "italy":
                return "IT";
            case "jersey":
                return "JE";
            case "japan":
                return "JP";
            case "liechtenstein":
                return "LI";
            case "sri lanka":
                return "LK";
            case "lithuania":
                return "LT";
            case "luxembourg":
                return "LU";
            case "monaco":
                return "MC";
            case "moldavia":
                return "MD";
            case "marshall islands":
                return "MH";
            case "macedonia":
                return "MK";
            case "northern mariana islands":
                return "MP";
            case "martinique":
                return "MQ";
            case "mexico":
                return "MX";
            case "malaysia":
                return "MY";
            case "holland":
                return "NL";
            case "norway":
                return "NO";
            case "new zealand":
                return "NZ";
            case "phillippines":
                return "PH";
            case "pakistan":
                return "PK";
            case "poland":
                return "PL";
            case "saint pierre and miquelon":
                return "PM";
            case "puerto rico":
                return "PR";
            case "portugal":
                return "PT";
            case "french reunion":
                return "RE";
            case "russia":
                return "RU";
            case "sweden":
                return "SE";
            case "slovenia":
                return "SI";
            case "svalbard & jan mayen islands":
                return "SJ";
            case "slovak republic":
                return "SK";
            case "san marino":
                return "SM";
            case "thailand":
                return "TH";
            case "turkey":
                return "TR";
            case "united states":
                return "US";
            case "vatican":
                return "VA";
            case "virgin islands":
                return "VI";
            case "mayotte":
                return "YT";
            case "south africa":
                return "ZA";
            default:
                return "Country not found or invalid input!";
        }
    }

    // Method to list supported country codes
    public static void listSupportedCountryCodes() {
        System.out.println("Supported Country Codes:");
        System.out.println("Country - Code");

        // List of countries and their codes
        System.out.println("Andorra - AD");
        System.out.println("Argentina - AR");
        System.out.println("American Samoa - AS");
        System.out.println("Austria - AT");
        System.out.println("Australia - AU");
        System.out.println("Bangladesh - BD");
        System.out.println("Belgium - BE");
        System.out.println("Bulgaria - BG");
        System.out.println("Brazil - BR");
        System.out.println("Canada - CA");
        System.out.println("Switzerland - CH");
        System.out.println("Czech Republic - CZ");
        System.out.println("Germany - DE");
        System.out.println("Denmark - DK");
        System.out.println("Dominican Republic - DO");
        System.out.println("Spain - ES");
        System.out.println("Finland - FI");
        System.out.println("Faroe Islands - FO");
        System.out.println("France - FR");
        System.out.println("Great Britain - GB");
        System.out.println("French Guyana - GF");
        System.out.println("Guernsey - GG");
        System.out.println("Greenland - GL");
        System.out.println("Guadeloupe - GP");
        System.out.println("Guatemala - GT");
        System.out.println("Guam - GU");
        System.out.println("Guyana - GY");
        System.out.println("Croatia - HR");
        System.out.println("Hungary - HU");
        System.out.println("Isle of Man - IM");
        System.out.println("India - IN");
        System.out.println("Iceland - IS");
        System.out.println("Italy - IT");
        System.out.println("Jersey - JE");
        System.out.println("Japan - JP");
        System.out.println("Liechtenstein - LI");
        System.out.println("Sri Lanka - LK");
        System.out.println("Lithuania - LT");
        System.out.println("Luxembourg - LU");
        System.out.println("Monaco - MC");
        System.out.println("Moldavia - MD");
        System.out.println("Marshall Islands - MH");
        System.out.println("Macedonia - MK");
        System.out.println("Northern Mariana Islands - MP");
        System.out.println("Martinique - MQ");
        System.out.println("Mexico - MX");
        System.out.println("Malaysia - MY");
        System.out.println("Holland - NL");
        System.out.println("Norway - NO");
        System.out.println("New Zealand - NZ");
        System.out.println("Phillippines - PH");
        System.out.println("Pakistan - PK");
        System.out.println("Poland - PL");
        System.out.println("Saint Pierre and Miquelon - PM");
        System.out.println("Puerto Rico - PR");
        System.out.println("Portugal - PT");
        System.out.println("French Reunion - RE");
        System.out.println("Russia - RU");
        System.out.println("Sweden - SE");
        System.out.println("Slovenia - SI");
        System.out.println("Svalbard & Jan Mayen Islands - SJ");
        System.out.println("Slovak Republic - SK");
        System.out.println("San Marino - SM");
        System.out.println("Thailand - TH");
        System.out.println("Turkey - TR");
        System.out.println("United States - US");
        System.out.println("Vatican - VA");
        System.out.println("Virgin Islands - VI");
        System.out.println("Mayotte - YT");
        System.out.println("South Africa - ZA");

        System.out.println();
    }
}
