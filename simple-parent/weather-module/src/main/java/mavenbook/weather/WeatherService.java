package mavenbook.weather;

import java.io.PrintWriter;

public class WeatherService {
    private final WeatherProvider weatherProvider;
    
    public WeatherService(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
    
    public void retrieveForecast(String city, PrintWriter writer) throws Exception {
        var weather = weatherProvider.getWeatherAt(city);
        var weatherFormatter = new WeatherFormatter();
        weatherFormatter.format(weather, writer);
    }
}
