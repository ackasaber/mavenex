package mavenbook.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mavenbook.weather.OpenWeatherMapProvider;
import mavenbook.weather.WeatherService;
import org.apache.http.impl.client.HttpClientBuilder;

public class WeatherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private WeatherService service;
    
    @Override
    public void init() {
        var appID = getServletConfig().getInitParameter("openweathermap.appid");
        var httpClient = HttpClientBuilder.create().build();
        var weatherProvider = new OpenWeatherMapProvider(appID, httpClient);
        service = new WeatherService(weatherProvider);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String city = request.getParameter("city");
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                service.retrieveForecast(city, out);
            } catch (Exception e) {
                out.println("Exception when retrieving the weather data:");
                e.printStackTrace(out);
            }
        }
    }
}
