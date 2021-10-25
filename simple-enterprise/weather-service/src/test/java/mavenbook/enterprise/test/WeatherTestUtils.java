package mavenbook.enterprise.test;

import mavenbook.enterprise.entities.Atmosphere;
import mavenbook.enterprise.entities.Location;
import mavenbook.enterprise.entities.Sun;
import mavenbook.enterprise.entities.Temperature;
import mavenbook.enterprise.entities.Weather;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.entities.Wind;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class WeatherTestUtils {
    private WeatherTestUtils() { }

    public static void assertEqual(Location tested, Location correct) {
        assertEquals(tested.getCityId(), correct.getCityId());
        assertEquals(tested.getCity(), correct.getCity());
        assertEquals(tested.getCountryCode(), correct.getCountryCode());
    }
    
    public static void assertEqual(Temperature tested, Temperature correct) {
        assertThat(tested.getCurrent(), comparesEqualTo(correct.getCurrent()));
        assertThat(tested.getFeelsLike(), comparesEqualTo(correct.getFeelsLike()));
        assertThat(tested.getCityMin(), comparesEqualTo(correct.getCityMin()));
        assertThat(tested.getCityMax(), comparesEqualTo(correct.getCityMax()));
    }
    
    public static void assertEqual(Wind tested, Wind correct) {
        assertThat(tested.getSpeed(), comparesEqualTo(correct.getSpeed()));
        assertEquals(tested.getDirection(), correct.getDirection());
    }
    
    public static void assertEqual(Atmosphere tested, Atmosphere correct) {
        assertEquals(tested.getHumidity(), correct.getHumidity());
        assertEquals(tested.getPressure(), correct.getPressure());
        assertEquals(tested.getVisibility(), correct.getVisibility());
        assertEquals(tested.getClouds(), correct.getClouds());
    }
    
    public static void assertEqual(Sun tested, Sun correct) {
        assertEquals(tested.getSunrise(), correct.getSunrise());
        assertEquals(tested.getSunset(), correct.getSunset());
    }
    
    public static void assertEqual(Weather tested, Weather correct) {
        assertEquals(tested.getSummary(), correct.getSummary());
        assertEquals(tested.getPrecipitation(), correct.getPrecipitation());
        assertEqual(tested.getTemperature(), correct.getTemperature());
        assertEqual(tested.getWind(), correct.getWind());
        assertEqual(tested.getAtmosphere(), correct.getAtmosphere());
        assertEqual(tested.getSun(), correct.getSun());
    }

    public static void assertEqual(WeatherReport tested, WeatherReport correct) {
        assertEquals(tested.getUpdatedAt(), correct.getUpdatedAt());
        assertEqual(tested.getLocation(), correct.getLocation());
        assertEqual(tested.getWeather(), correct.getWeather());
    }
}
