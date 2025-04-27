public class WeatherData {

    private String location;
    private double temperature;
    private String weatherDescription;
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

    public String getWeatherDescription() {
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
}