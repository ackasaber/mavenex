package mavenbook.enterprise.web;

import mavenbook.enterprise.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Weather controller.
 * 
 * A simple controller that exposes the weather service via HTTP.
 */
@Controller
public class WeatherController {
    
    private final WeatherService weatherService;

    /**
     * Creates a weather controller.
     * 
     * @param weatherService the weather service (autowired)
     */
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    /**
     * Reports the current weather at the given location.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     * @param model the Spring MVC model
     * @return the Spring MVC view name
     */
    @GetMapping("/weather")
    public String reportCurrentWeather(@RequestParam("location") String location,
                                        Model model) {
        var weatherReport = weatherService.getWeatherAt(location);
        model.addAttribute("location", location);
        model.addAttribute("city", weatherReport.getCity());
        model.addAttribute("updatedAt", weatherReport.getUpdatedAt());
        model.addAttribute("weather", weatherReport.getWeather());
        return "current-weather";
    }
    
    /**
     * Reports all weather requests previously done for the given location.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     * @param model the Spring MVC model
     * @return the Spring MVC view name
     */
    @GetMapping("/history")
    public String reportRequestHistory(@RequestParam("location") String location,
                                       Model model) {
        var reports = weatherService.getRecent(location);
        model.addAttribute("location", location);
        model.addAttribute("reports", reports);
        return "weather-history";
    }
}
