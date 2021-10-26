package mavenbook.enterprise.openweathermap;

import java.io.IOException;
import java.net.URISyntaxException;
import mavenbook.enterprise.entities.WeatherReport;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenWeatherMap weather provider.
 */
public class OpenWeatherMapProvider {
    private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapProvider.class);
    private final String appID;
    private final HttpClient httpClient;

    /**
     * Creates the OpenWeatherMap weather provider.
     * @param appID the OpenWeatherMap registered appID
     * @param httpClient an HTTP client for performing HTTP requests to OpenWeatherMap
     */
    public OpenWeatherMapProvider(String appID, HttpClient httpClient) {
        this.appID = appID;
        this.httpClient = httpClient;
    }

    /**
     * Retrieves the current weather report from OpenWeatherMap.
     * 
     * @param location the location string: city[,country_code]
     * @return the current weather at the location
     */
    public WeatherReport getWeatherAt(String location) {
        try {
            log.info("Retrieving weather data");
            var uriBuilder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");
            uriBuilder.addParameter("q", location)
                      .addParameter("appid", appID)
                      .addParameter("units", "metric")
                      .addParameter("mode", "xml");
            var uri = uriBuilder.build();
            log.debug("URI = {}", uri);
            var request = new HttpGet(uri);
            var response = httpClient.execute(request);
            var responseStream = response.getEntity().getContent();
            var parser = new OpenWeatherMapParser();
            var weather = parser.parse(responseStream);
            return weather;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new RuntimeException("Error while requesting current weather data", e);
        } catch (DocumentException e) {
            throw new IllegalStateException("The weather service returned invalid data", e);
        }
    }    
}
