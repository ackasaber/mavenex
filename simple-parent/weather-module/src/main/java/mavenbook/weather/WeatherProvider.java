package mavenbook.weather;

public interface WeatherProvider {
    Weather getWeatherAt(String city) throws Exception;
}
