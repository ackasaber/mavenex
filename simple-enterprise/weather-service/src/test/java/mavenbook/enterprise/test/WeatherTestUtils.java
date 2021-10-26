package mavenbook.enterprise.test;

import mavenbook.enterprise.entities.Atmosphere;
import mavenbook.enterprise.entities.City;
import mavenbook.enterprise.entities.Sun;
import mavenbook.enterprise.entities.Temperature;
import mavenbook.enterprise.entities.Weather;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.entities.Wind;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

/**
 * Helper static methods for testing.
 */
public class WeatherTestUtils {
    private WeatherTestUtils() { }

    /**
     * Asserts city entity equality.
     * 
     * @param tested the city entity
     * @param correct the correct city entity
     */
    public static void assertEqual(City tested, City correct) {
        assertEquals(tested.getCityId(), correct.getCityId());
        assertEquals(tested.getCity(), correct.getCity());
        assertEquals(tested.getCountryCode(), correct.getCountryCode());
    }
    
    /**
     * Asserts temperature data equality.
     * 
     * @param tested temperature data
     * @param correct correct temperature data
     */
    public static void assertEqual(Temperature tested, Temperature correct) {
        assertThat(tested.getCurrent(), comparesEqualTo(correct.getCurrent()));
        assertThat(tested.getFeelsLike(), comparesEqualTo(correct.getFeelsLike()));
        assertThat(tested.getCityMin(), comparesEqualTo(correct.getCityMin()));
        assertThat(tested.getCityMax(), comparesEqualTo(correct.getCityMax()));
    }
    
    /**
     * Asserts wind data equality.
     * 
     * @param tested wind data
     * @param correct correct wind data
     */
    public static void assertEqual(Wind tested, Wind correct) {
        assertThat(tested.getSpeed(), comparesEqualTo(correct.getSpeed()));
        assertEquals(tested.getDirection(), correct.getDirection());
    }
    
    /**
     * Asserts atmospheric data equality.
     * 
     * @param tested atmospheric data
     * @param correct correct atmospheric data
     */
    public static void assertEqual(Atmosphere tested, Atmosphere correct) {
        assertEquals(tested.getHumidity(), correct.getHumidity());
        assertEquals(tested.getPressure(), correct.getPressure());
        assertEquals(tested.getVisibility(), correct.getVisibility());
        assertEquals(tested.getClouds(), correct.getClouds());
    }
    
    /**
     * Asserts solar astronomic data equality.
     * 
     * @param tested solar astronomic data
     * @param correct correct solar astronomic data
     */
    public static void assertEqual(Sun tested, Sun correct) {
        assertEquals(tested.getSunrise(), correct.getSunrise());
        assertEquals(tested.getSunset(), correct.getSunset());
    }
    
    /**
     * Asserts weather observations equality.
     * 
     * @param tested weather observations
     * @param correct correct weather observations
     */
    public static void assertEqual(Weather tested, Weather correct) {
        assertEquals(tested.getSummary(), correct.getSummary());
        assertEquals(tested.getPrecipitation(), correct.getPrecipitation());
        assertEqual(tested.getTemperature(), correct.getTemperature());
        assertEqual(tested.getWind(), correct.getWind());
        assertEqual(tested.getAtmosphere(), correct.getAtmosphere());
        assertEqual(tested.getSun(), correct.getSun());
    }

    /**
     * Asserts weather report equality.
     * 
     * @param tested weather report
     * @param correct correct weather report
     */
    public static void assertEqual(WeatherReport tested, WeatherReport correct) {
        assertEquals(tested.getUpdatedAt(), correct.getUpdatedAt());
        assertEqual(tested.getCity(), correct.getCity());
        assertEqual(tested.getWeather(), correct.getWeather());
    }
}
