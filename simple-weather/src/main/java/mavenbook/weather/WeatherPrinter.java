package mavenbook.weather;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Weather object formatter.
 */
public class WeatherPrinter {

    private static final Logger log = LoggerFactory.getLogger(WeatherPrinter.class);
    
    void print(Weather weather, PrintWriter writer) {
        log.info("Formatting weather data");
        var context = new VelocityContext();
        context.put("weather", weather);
        var classLoader = getClass().getClassLoader();
        var templateStream = classLoader.getResourceAsStream("weather-report.vm");
        var reader = new InputStreamReader(templateStream);
        Velocity.evaluate(context, writer, "", reader);
    }
    
}
