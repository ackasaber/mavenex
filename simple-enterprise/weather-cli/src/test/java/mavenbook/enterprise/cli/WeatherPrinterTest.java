package mavenbook.enterprise.cli;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import mavenbook.enterprise.entities.Atmosphere;
import mavenbook.enterprise.entities.City;
import mavenbook.enterprise.entities.Sun;
import mavenbook.enterprise.entities.Temperature;
import mavenbook.enterprise.entities.Weather;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.entities.Wind;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherPrinterTest {
    
    private static final WeatherReport report;
    
    static {
        var city = new City();
        city.setCityId(625144);
        city.setCity("Minsk");
        city.setCountryCode("BY");
        
        var temperature = new Temperature();
        temperature.setCurrent(new BigDecimal("9.86"));
        temperature.setFeelsLike(new BigDecimal("7.16"));
        temperature.setCityMin(new BigDecimal("9.85"));
        temperature.setCityMax(new BigDecimal("9.86"));
        
        var wind = new Wind();
        wind.setDirection("South");
        wind.setSpeed(new BigDecimal("5.7"));
        
        var atmosphere = new Atmosphere();
        atmosphere.setHumidity(69);
        atmosphere.setPressure(1023);
        atmosphere.setClouds("few clouds");
        atmosphere.setVisibility(10_000);
        
        var sun = new Sun();
        sun.setSunrise(LocalDateTime.of(2021, Month.OCTOBER, 30, 5, 6, 1));
        sun.setSunset(LocalDateTime.of(2021, Month.OCTOBER, 30, 14, 40, 35));

        var weather = new Weather();
        weather.setSummary("few clouds");
        weather.setTemperature(temperature);
        weather.setPrecipitation("no");
        weather.setWind(wind);
        weather.setAtmosphere(atmosphere);
        weather.setSun(sun);
        
        report = new WeatherReport();
        report.setCity(city);
        report.setUpdatedAt(LocalDateTime.of(2021, Month.OCTOBER, 30, 15, 24, 37));
        report.setWeather(weather);        
    }
    
    @Test
    public void testWeather() throws Exception {
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        var printer = new WeatherFormatter();
        printer.format("Minsk", report, printWriter);
        
        var classLoader = getClass().getClassLoader();
        try (var stream = classLoader.getResourceAsStream("minsk-expected.txt")) {
            var correct = IOUtils.toString(stream, StandardCharsets.UTF_8);
            assertEquals(stringWriter.toString(), correct);
        }
    }
    
    @Test
    public void testHistory() throws Exception {
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        var printer = new WeatherFormatter();
        printer.format("Minsk", List.of(report), printWriter);
        
        var classLoader = getClass().getClassLoader();
        try (var stream = classLoader.getResourceAsStream("history-expected.txt")) {
            var correct = IOUtils.toString(stream, StandardCharsets.UTF_8);
            assertEquals(stringWriter.toString(), correct);
        }
    }
}
