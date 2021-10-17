package mavenbook.weather;

import java.io.PrintWriter;
import java.util.Properties;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) throws Exception {
        // Configure Velocity with default settings.
        Velocity.init();
        // Retrieve the appID key from the application properties.
        var classLoader = Main.class.getClassLoader();
        var appConfigStream = classLoader.getResourceAsStream("application.properties");
        var properties = new Properties();
        properties.load(appConfigStream);
        var appID = properties.getProperty("openweathermap.appid");
        log.debug("Retrieved the OpenWeatherMap appID {}", appID);
        // Initialize the weather provider.
        var weatherProvider = new OpenWeatherMapProvider(appID);
        // The city for a weather check is provided as a command line argument.
        var city = (args.length > 0) ? args[0] : "Vilnius";
        log.info("Requesting weather data for {}", city);
        // Retrieve and print the weather.
        var weather = weatherProvider.getWeatherAt(city);
        var weatherFormatter = new WeatherFormatter();
        var writer = new PrintWriter(System.out);
        weatherFormatter.format(weather, writer);
        writer.flush();
    }

}
