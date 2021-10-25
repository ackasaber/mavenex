package mavenbook.enterprise;

import java.util.List;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.openweathermap.OpenWeatherMapProvider;
import org.springframework.transaction.annotation.Transactional;
import mavenbook.enterprise.repositories.LocationRepository;
import mavenbook.enterprise.repositories.WeatherReportRepository;

public class WeatherService {
    
    private final OpenWeatherMapProvider weatherProvider;
    
    private final WeatherReportRepository weatherReportRepo;
    
    private final LocationRepository locationRepo;

    public WeatherService(OpenWeatherMapProvider weatherProvider, WeatherReportRepository weatherReportRepo, LocationRepository locationRepo) {
        this.weatherProvider = weatherProvider;
        this.weatherReportRepo = weatherReportRepo;
        this.locationRepo = locationRepo;
    }
 
    @Transactional
    public WeatherReport getWeatherAt(String location) throws Exception {
        var weatherReport = weatherProvider.getWeatherAt(location);
        weatherReport.setLocation(locationRepo.save(weatherReport.getLocation()));
        weatherReportRepo.save(weatherReport);
        return weatherReport;
    }
    
    @Transactional
    public List<WeatherReport> getRecent(String location) throws Exception {
        var loc = locationRepo.find(location);
        return weatherReportRepo.findByLocation(loc);
    }
}
