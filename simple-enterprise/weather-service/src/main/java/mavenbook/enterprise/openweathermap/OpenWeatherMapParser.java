package mavenbook.enterprise.openweathermap;

import mavenbook.enterprise.entities.WeatherReport;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import mavenbook.enterprise.entities.Atmosphere;
import mavenbook.enterprise.entities.Location;
import mavenbook.enterprise.entities.Sun;
import mavenbook.enterprise.entities.Temperature;
import mavenbook.enterprise.entities.Weather;
import mavenbook.enterprise.entities.Wind;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parser of the OpenWeatherMap's XML response for the current weather request.
 */
class OpenWeatherMapParser {
    private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapParser.class);
    
    public OpenWeatherMapParser() {
    }
    
    public WeatherReport parse(InputStream input) throws DocumentException {
        log.info("Creating XML reader");
        SAXReader xmlReader = new SAXReader();
        Document doc = xmlReader.read(input);
        // lazy
        log.debug("Received: {}", new Object() {
            @Override
            public String toString() {
                return doc.asXML();
            }
        });
        
        log.info("Parsing XML response");
        var report = new WeatherReport();
        var updatedAt = doc.valueOf("/current/lastupdate/@value");
        report.setUpdatedAt(LocalDateTime.parse(updatedAt));
        
        var city = new Location();
        city.setCityId(Integer.parseInt(doc.valueOf("/current/city/@id")));
        city.setCity(doc.valueOf("/current/city/@name"));
        city.setCountryCode(doc.valueOf("/current/city/country"));
        report.setLocation(city);
        
        var weather = new Weather();
        weather.setSummary(doc.valueOf("/current/weather/@value"));

        var temperature = new Temperature();
        assertUnit(doc.valueOf("/current/temperature/@unit"), "celsius");
        var temp = doc.valueOf("/current/temperature/@value");
        temperature.setCurrent(new BigDecimal(temp));
        var cityMin = doc.valueOf("/current/temperature/@min");
        temperature.setCityMin(new BigDecimal(cityMin));
        var cityMax = doc.valueOf("/current/temperature/@max");
        temperature.setCityMax(new BigDecimal(cityMax));
        assertUnit(doc.valueOf("/current/feels_like/@unit"), "celsius");
        var feelsLike = doc.valueOf("/current/feels_like/@value");
        temperature.setFeelsLike(new BigDecimal(feelsLike));
        weather.setTemperature(temperature);
        
        weather.setPrecipitation(doc.valueOf("/current/precipitation/@mode"));

        var wind = new Wind();
        assertUnit(doc.valueOf("/current/wind/speed/@unit"), "m/s");
        var speed = doc.valueOf("/current/wind/speed/@value");
        wind.setSpeed(new BigDecimal(speed));
        wind.setDirection(doc.valueOf("/current/wind/direction/@name"));
        weather.setWind(wind);

        var atmosphere = new Atmosphere();
        assertUnit(doc.valueOf("/current/humidity/@unit"), "%");
        var humidity = doc.valueOf("/current/humidity/@value");
        atmosphere.setHumidity(Integer.parseInt(humidity));
        assertUnit(doc.valueOf("/current/pressure/@unit"), "hPa");
        var pressure = doc.valueOf("/current/pressure/@value");
        atmosphere.setPressure(Integer.parseInt(pressure));
        atmosphere.setClouds(doc.valueOf("/current/clouds/@name"));
        var visibility = doc.valueOf("/current/visibility/@value");
        atmosphere.setVisibility(Integer.parseInt(visibility));
        weather.setAtmosphere(atmosphere);
        
        var sun = new Sun();
        var sunrise = doc.valueOf("/current/city/sun/@rise");
        sun.setSunrise(LocalDateTime.parse(sunrise));
        var sunset = doc.valueOf("/current/city/sun/@set");
        sun.setSunset(LocalDateTime.parse(sunset));
        weather.setSun(sun);
        report.setWeather(weather);
        
        return report;
    }
    
    private static void assertUnit(String unit, String correctUnit) {
        if (!unit.equals(correctUnit)) {
            log.error("Expected unit " +  correctUnit + ", got " + unit);
            throw new IllegalArgumentException(correctUnit + " units were expected");
        }
    }   

}
