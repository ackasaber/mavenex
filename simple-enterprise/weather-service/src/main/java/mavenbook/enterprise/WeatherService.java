package mavenbook.enterprise;

import java.util.List;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.openweathermap.OpenWeatherMapProvider;
import org.springframework.transaction.annotation.Transactional;
import mavenbook.enterprise.repositories.WeatherReportRepository;
import mavenbook.enterprise.repositories.CityRepository;

/**
 * Weather service.
 * 
 * <p>Retrieves the current weather data for a given location and stores the result
 * in a database.</p>
 * <p>Weather provider and Spring Data JPA repositories will be autowired by
 * Spring. Also Spring will enforce transactionality of the methods.</p>
 */
public class WeatherService {
    
    private final OpenWeatherMapProvider weatherProvider;
    
    private final WeatherReportRepository weatherReportRepo;
    
    private final CityRepository cityRepo;

    /**
     * Creates a weather service instance.
     * 
     * @param weatherProvider the weather provider
     * @param weatherReportRepo the weather reports repository
     * @param cityRepo the city repository
     */
    public WeatherService(OpenWeatherMapProvider weatherProvider, WeatherReportRepository weatherReportRepo, CityRepository cityRepo) {
        this.weatherProvider = weatherProvider;
        this.weatherReportRepo = weatherReportRepo;
        this.cityRepo = cityRepo;
    }
 
    /**
     * Retrieves the current weather data for the given location and stores it
     * into the database.
     * 
     * @param location the location string. Can be either a city name or a city
     *        name followed by a comma and a country code.
     * @return the weather report for the given location
     */
    @Transactional
    public WeatherReport getWeatherAt(String location) {
        var weatherReport = weatherProvider.getWeatherAt(location);
        weatherReport.setCity(cityRepo.save(weatherReport.getCity()));
        weatherReportRepo.save(weatherReport);
        return weatherReport;
    }
    
    /**
     * Searches for the saves weather reports in the database.
     * 
     * @param location the location string. Can be either a city name or a city
     *        name followed by a comma and a country code.
     * @return the list of found weather reports
     */
    @Transactional
    public List<WeatherReport> getRecent(String location) {
        var loc = cityRepo.find(location);
        return weatherReportRepo.findByCity(loc);
    }
}
