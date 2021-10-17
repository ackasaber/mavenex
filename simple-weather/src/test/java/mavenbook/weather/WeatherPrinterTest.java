package mavenbook.weather;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherPrinterTest {
    
    @Test
    public void testPrinting() throws Exception {
        var weather = new Weather();
        weather.setCity("Minsk");
        weather.setCountry("BY");
        weather.setSunrise(LocalDateTime.of(2021, Month.OCTOBER, 17, 4, 40, 57));
        weather.setSunset(LocalDateTime.of(2021, Month.OCTOBER, 17, 15, 8, 52));
        weather.setTemperature(new BigDecimal("7.86"));
        weather.setTemperatureMin(new BigDecimal("7.63"));
        weather.setTemperatureMax(new BigDecimal("7.86"));
        weather.setFeelsLike(new BigDecimal("4.96"));
        weather.setHumidity(78);
        weather.setPressure(1016);
        weather.setWindSpeed(new BigDecimal("4.9"));
        weather.setWindDirection("West-southwest");
        weather.setClouds("broken clouds");
        weather.setPrecipitation("no");
        weather.setWeather("broken clouds");
        weather.setLastUpdated(LocalDateTime.of(2021, Month.OCTOBER, 17, 19, 41, 42));
        
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        var printer = new WeatherFormatter();
        printer.format(weather, printWriter);
        
        var classLoader = getClass().getClassLoader();
        try (var stream = classLoader.getResourceAsStream("minsk-expected.txt")) {
            var correct = IOUtils.toString(stream, StandardCharsets.UTF_8);
            assertEquals(stringWriter.toString(), correct);
        }
    }
}
