package recordemo;

public record WeatherData(double temperatureCelsius, String conditions)
{
    public double temperatureFahrenheit()
    {
        return temperatureCelsius * 9 / 5 + 32;
    }

    public String getSummary()
    {
        return String.format("Current weather: %.1f°C (%.1f°F) and %s",
                temperatureCelsius, temperatureFahrenheit(), conditions);
    }

    public static WeatherData fromFahrenheit(double tempFahrenheit, String conditions)
    {
        double tempCelsius = (tempFahrenheit - 32) * 5 / 9;
        return new WeatherData(tempCelsius, conditions);
    }

    public static void main(String[] args)
    {
        WeatherData today = new WeatherData(25.0, "Sunny");
        WeatherData yesterday = WeatherData.fromFahrenheit(50.0, "Cloudy");
        System.out.println("Today's weather: " + today.getSummary());
        System.out.println("Yesterday's weather: " + yesterday.getSummary());
    }
}
