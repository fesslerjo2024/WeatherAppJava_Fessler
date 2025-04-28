public class WeatherData {

    private String location;
    private double temperature;
    private static String weatherDescription;
    private String state; // not used yet
    private String placeName; // not used yet
    private String country; // not used yet
    private double windDirection;
    private double windSpeed;

    public WeatherData(String location, double temperature, String weatherDescription, double windDirection, double windSpeed) {
        this.location = location;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

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

    // Helper method to convert degrees (angles) to wind direction
    public static String getCompassDirection(double angle) {
        String[] directions = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West","Northwest", "North"};
        return directions[(int)Math.round(((angle % 360) / 45))];
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "location='" + location + '\'' +
                ", temperature=" + temperature +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                '}';
    }

    public static String getWeatherDescription(int weatherCode) {
        // Map weather codes to descriptions (based on Open-Meteo API documentation)
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
            case 53:
            case 55:
                return "Drizzle";
            case 61:
            case 63:
                return "Moderate rain";
            case 65:
                return "Rain";
            case 71:
                return "Slight snow fall";
            case 73:
                return "Moderate snow fall";
            case 75:
                return "Heavy and intense snow";
            case 80:
                return "Slight rain showers";
            case 81:
                return "Moderate rain showers";
            case 95:
                return "Thunderstorms";
            case 96:
            case 99:
                return "Severe thunderstorms";
            default:
                return "Unknown weather condition";
        }
    }

    public static String getURLForCountry(String countryInput) {
        String country = countryInput.toUpperCase().trim();
        String countryName = countryInput.trim().toLowerCase();

        switch (country) {
            case "ad": case "andorra":
                return "api.zippopotam.us/AD/";
            case "ar": case "argentina":
                return "api.zippopotam.us/AR/";
            case "as": case "american samoa":
                return "api.zippopotam.us/AS/";
            case "at": case "austria":
                return "api.zippopotam.us/AT/";
            case "au": case "australia":
                return "api.zippopotam.us/AU/";
            case "bd": case "bangladesh":
                return "api.zippopotam.us/BD/";
            case "be": case "belgium":
                return "api.zippopotam.us/BE/";
            case "bg": case "bulgaria":
                return "api.zippopotam.us/BG/";
            case "br": case "brazil":
                return "api.zippopotam.us/BR/";
            case "ca": case "canada":
                return "api.zippopotam.us/CA/";
            case "ch": case "switzerland":
                return "api.zippopotam.us/CH/";
            case "cz": case "czech republic":
                return "api.zippopotam.us/CZ/";
            case "de": case "germany":
                return "api.zippopotam.us/DE/";
            case "dk": case "denmark":
                return "api.zippopotam.us/DK/";
            case "do": case "dominican republic":
                return "api.zippopotam.us/DO/";
            case "es": case "spain":
                return "api.zippopotam.us/ES/";
            case "fi": case "finland":
                return "api.zippopotam.us/FI/";
            case "fo": case "faroe islands":
                return "api.zippopotam.us/FO/";
            case "fr": case "france":
                return "api.zippopotam.us/FR/";
            case "gb": case "great britain":
                return "api.zippopotam.us/GB/";
            case "gf": case "french guyana":
                return "api.zippopotam.us/GF/";
            case "gg": case "guernsey":
                return "api.zippopotam.us/GG/";
            case "gl": case "greenland":
                return "api.zippopotam.us/GL/";
            case "gp": case "guadeloupe":
                return "api.zippopotam.us/GP/";
            case "gt": case "guatemala":
                return "api.zippopotam.us/GT/";
            case "gu": case "guam":
                return "api.zippopotam.us/GU/";
            case "gy": case "guyana":
                return "api.zippopotam.us/GY/";
            case "hr": case "croatia":
                return "api.zippopotam.us/HR/";
            case "hu": case "hungary":
                return "api.zippopotam.us/HU/";
            case "im": case "isle of man":
                return "api.zippopotam.us/IM/";
            case "in": case "india":
                return "api.zippopotam.us/IN/";
            case "is": case "iceland":
                return "api.zippopotam.us/IS/";
            case "it": case "italy":
                return "api.zippopotam.us/IT/";
            case "je": case "jersey":
                return "api.zippopotam.us/JE/";
            case "jp": case "japan":
                return "api.zippopotam.us/JP/";
            case "li": case "liechtenstein":
                return "api.zippopotam.us/LI/";
            case "lk": case "sri lanka":
                return "api.zippopotam.us/LK/";
            case "lt": case "lithuania":
                return "api.zippopotam.us/LT/";
            case "lu": case "luxembourg":
                return "api.zippopotam.us/LU/";
            case "mc": case "monaco":
                return "api.zippopotam.us/MC/";
            case "md": case "moldavia":
                return "api.zippopotam.us/MD/";
            case "mh": case "marshall islands":
                return "api.zippopotam.us/MH/";
            case "mk": case "macedonia":
                return "api.zippopotam.us/MK/";
            case "mp": case "northern mariana islands":
                return "api.zippopotam.us/MP/";
            case "mq": case "martinique":
                return "api.zippopotam.us/MQ/";
            case "mx": case "mexico":
                return "api.zippopotam.us/MX/";
            case "my": case "malaysia":
                return "api.zippopotam.us/MY/";
            case "nl": case "holland":
                return "api.zippopotam.us/NL/";
            case "no": case "norway":
                return "api.zippopotam.us/NO/";
            case "nz": case "new zealand":
                return "api.zippopotam.us/NZ/";
            case "ph": case "philippines":
                return "api.zippopotam.us/PH/";
            case "pk": case "pakistan":
                return "api.zippopotam.us/PK/";
            case "pl": case "poland":
                return "api.zippopotam.us/PL/";
            case "pm": case "saint pierre and miquelon":
                return "api.zippopotam.us/PM/";
            case "pr": case "puerto rico":
                return "api.zippopotam.us/PR/";
            case "pt": case "portugal":
                return "api.zippopotam.us/PT/";
            case "re": case "french reunion":
                return "api.zippopotam.us/RE/";
            case "ru": case "russia":
                return "api.zippopotam.us/RU/";
            case "se": case "sweden":
                return "api.zippopotam.us/SE/";
            case "si": case "slovenia":
                return "api.zippopotam.us/SI/";
            case "sj": case "svalbard & jan mayen islands":
                return "api.zippopotam.us/SJ/";
            case "sk": case "slovak republic":
                return "api.zippopotam.us/SK/";
            case "sm": case "san marino":
                return "api.zippopotam.us/SM/";
            case "th": case "thailand":
                return "api.zippopotam.us/TH/";
            case "tr": case "turkey":
                return "api.zippopotam.us/TR/";
            case "us": case "united states":
                return "api.zippopotam.us/US/";
            case "va": case "vatican":
                return "api.zippopotam.us/VA/";
            case "vi": case "virgin islands":
                return "api.zippopotam.us/VI/";
            case "yt": case "mayotte":
                return "api.zippopotam.us/YT/";
            case "za": case "south africa":
                return "api.zippopotam.us/ZA/";

            default:
                return "Country not found or invalid input!";
        }
    }

}