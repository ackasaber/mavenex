package mavenbook.weather;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for parsing OpenWeatherMap current weather report.
 */
public class OpenWeatherMapParserTest 
{
    @Test
    public void testParsing() throws Exception
    {
        var classLoader = getClass().getClassLoader();
        var minskXml = classLoader.getResourceAsStream("minsk-weather.xml");
        var parser = new OpenWeatherMapParser();
        var weather = parser.parse(minskXml);
        assertEquals(weather.getCity(), "Minsk");
        assertEquals(weather.getCountry(), "BY");
        assertEquals(weather.getSunrise(),
                     LocalDateTime.of(2021, Month.OCTOBER, 17, 4, 40, 57));
        assertEquals(weather.getSunset(),
                     LocalDateTime.of(2021, Month.OCTOBER, 17, 15, 8, 52));
        assertEquals(weather.getTemperature(), new BigDecimal("7.86"));
        assertEquals(weather.getTemperatureMin(), new BigDecimal("7.63"));
        assertEquals(weather.getTemperatureMax(), new BigDecimal("7.86"));
        assertEquals(weather.getFeelsLike(), new BigDecimal("4.96"));
        assertEquals(weather.getHumidity(), 78);
        assertEquals(weather.getPressure(), 1016);
        assertEquals(weather.getWindSpeed(), new BigDecimal("4.9"));
        assertEquals(weather.getWindDirection(), "West-southwest");
        assertEquals(weather.getClouds(), "broken clouds");
        assertEquals(weather.getPrecipitation(), "no");
        assertEquals(weather.getWeather(), "broken clouds");
        assertEquals(weather.getLastUpdated(),
                     LocalDateTime.of(2021, Month.OCTOBER, 17, 19, 41, 42));
    }
}
