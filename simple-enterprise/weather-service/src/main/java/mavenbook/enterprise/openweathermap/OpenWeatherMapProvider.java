package mavenbook.enterprise.openweathermap;

import mavenbook.enterprise.entities.WeatherReport;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenWeatherMap weather provider.
 */
public class OpenWeatherMapProvider {
    private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapProvider.class);
    private final String appID;
    private final HttpClient httpClient;

    public OpenWeatherMapProvider(String appID, HttpClient httpClient) {
        this.appID = appID;
        this.httpClient = httpClient;
    }

    public WeatherReport getWeatherAt(String city) throws Exception {
        log.info("Retrieving weather data");
        var uriBuilder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");
        uriBuilder.addParameter("q", city)
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
    }    
}
