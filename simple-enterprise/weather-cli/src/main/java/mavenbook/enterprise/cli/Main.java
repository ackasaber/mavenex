package mavenbook.enterprise.cli;

import java.io.PrintWriter;
import mavenbook.enterprise.WeatherService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Weather CLI main class.
 */
public class Main implements AutoCloseable {
    private final ConfigurableApplicationContext context;
    private final WeatherService weatherService;
    private final WeatherFormatter weatherFormatter;
    
    /**
     * Creates the application instance and initializes it.
     */
    public Main() {
        context = new ClassPathXmlApplicationContext("context.xml");
        weatherService = context.getBean(WeatherService.class);
        weatherFormatter = new WeatherFormatter();
    }
    
    @Override
    public void close() {
        context.close();
    }
    
    /**
     * Writes the current weather report for the given location to
     * the standard output.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     */
    public void reportCurrentWeather(String location) {
        var weatherReport = weatherService.getWeatherAt(location);
        var writer = new PrintWriter(System.out);
        weatherFormatter.format(location, weatherReport, writer);
        writer.flush();
    }
    
    /**
     * Writes the weather request history for the given location to the 
     * standard output.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     */
    public void reportRequestHistory(String location) {
        var reports = weatherService.getRecent(location);
        var writer = new PrintWriter(System.out);
        weatherFormatter.format(location, reports, writer);
        writer.flush();
    }
    
    /**
     * Runs the command line application.
     * 
     * @param args two command line arguments: location [weather|history]
     */
    public static void main(String[] args) {
        var location = args[0];
        var operation = args[1];
        
        try (var app = new Main()) {
            if (operation.equals("weather")) {
                app.reportCurrentWeather(location);
            } else if (operation.equals("history")) {
                app.reportRequestHistory(location);
            }
        }
    }
}
