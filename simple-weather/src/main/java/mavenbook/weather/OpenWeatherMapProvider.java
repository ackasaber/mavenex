package mavenbook.weather;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenWeatherMap weather provider.
 */
public class OpenWeatherMapProvider {

    private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapProvider.class);
    private final String appID;

    public OpenWeatherMapProvider(String appID) {
        this.appID = appID;
    }

    public Weather getWeatherAt(String city) throws Exception {
        log.info("Retrieving weather data");
        var uriBuilder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");
        uriBuilder.addParameter("q", city)
                  .addParameter("appid", appID)
                  .addParameter("units", "metric")
                  // We only stay true to the original example, since the
                  // default JSON would be a more logical choice nowadays.
                  .addParameter("mode", "xml");
        var uri = uriBuilder.build();
        log.debug("URI = {}", uri);
        var request = new HttpGet(uri);
        var httpClient = HttpClientBuilder.create().build();
        var response = httpClient.execute(request);
        var responseStream = response.getEntity().getContent();
        var weather = parse(responseStream);
        return weather;
    }
    
    private static Weather parse(InputStream input) throws DocumentException {
        var weather = new Weather();
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
        // city
        weather.setCity(doc.valueOf("/current/city/@name"));
        weather.setCountry(doc.valueOf("/current/city/country"));
        var sunrise = doc.valueOf("/current/city/sun/@rise");
        weather.setSunrise(LocalDateTime.parse(sunrise));
        var sunset = doc.valueOf("/current/city/sun/@set");
        weather.setSunset(LocalDateTime.parse(sunset));
        // temparature
        assertUnit(doc.valueOf("/current/temperature/@unit"), "celsius");
        var temp = doc.valueOf("/current/temperature/@value");
        weather.setTemperature(new BigDecimal(temp));
        var tempMin = doc.valueOf("/current/temperature/@min");
        weather.setTemperatureMin(new BigDecimal(tempMin));
        var tempMax = doc.valueOf("/current/temperature/@max");
        weather.setTemperatureMax(new BigDecimal(tempMax));
        // feels_like
        assertUnit(doc.valueOf("/current/feels_like/@unit"), "celsius");
        var feelsLike = doc.valueOf("/current/feels_like/@value");
        weather.setFeelsLike(new BigDecimal(feelsLike));
        // humidity
        assertUnit(doc.valueOf("/current/humidity/@unit"), "%");
        var humidity = doc.valueOf("/current/humidity/@value");
        weather.setHumidity(Integer.parseInt(humidity));
        // pressure
        assertUnit(doc.valueOf("/current/pressure/@unit"), "hPa");
        var pressure = doc.valueOf("/current/pressure/@value");
        weather.setPressure(Integer.parseInt(pressure));
        // wind
        assertUnit(doc.valueOf("/current/wind/speed/@unit"), "m/s");
        var speed = doc.valueOf("/current/wind/speed/@value");
        weather.setWindSpeed(new BigDecimal(speed));
        weather.setWindDirection(doc.valueOf("/current/wind/direction/@name"));
        // clouds
        weather.setClouds(doc.valueOf("/current/clouds/@name"));
        // precipitation
        weather.setPrecipitation(doc.valueOf("/current/precipitation/@mode"));
        // weather
        weather.setWeather(doc.valueOf("/current/weather/@value"));
        // lastupdate
        var lastUpdate = doc.valueOf("/current/lastupdate/@value");
        weather.setLastUpdated(LocalDateTime.parse(lastUpdate));
        return weather;
    }
    
    private static void assertUnit(String unit, String correctUnit) {
        if (!unit.equals(correctUnit)) {
            log.error("Expected unit " +  correctUnit + ", got " + unit);
            throw new IllegalArgumentException(correctUnit + " units were expected");
        }
    }   
}
