package mavenbook.weather;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
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
        var parser = new OpenWeatherMapParser();
        var weather = parser.parse(responseStream);
        return weather;
    }    
}
