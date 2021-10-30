package mavenbook.enterprise.cli;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import mavenbook.enterprise.entities.WeatherReport;

/**
 * Weather report formatter.
 */
public class WeatherFormatter {
    private final Configuration freemarkerConfig;
    
    /**
     * Creates the formatter instance and initializes templates.
     */
    public WeatherFormatter() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        var classloader = WeatherFormatter.class.getClassLoader();
        freemarkerConfig.setClassLoaderForTemplateLoading(classloader, "templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freemarkerConfig.setLogTemplateExceptions(false);
        freemarkerConfig.setWrapUncheckedExceptions(true);
        freemarkerConfig.setFallbackOnNullLoopVariable(false);
    }
    
    /**
     * Formats the current weather report.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     * @param report the current weather report
     * @param writer output
     */
    public void format(String location, WeatherReport report, PrintWriter writer) {
        var context = new HashMap<String, Object>();
        context.put("location", location);
        context.put("city", report.getCity());
        context.put("updatedAt", report.getUpdatedAt());
        context.put("weather", report.getWeather());
        
        try {
            var template = freemarkerConfig.getTemplate("current-weather.ftl");
            template.process(context, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Formats the weather request history.
     * 
     * @param location the location string (city[,COUNTRY_CODE])
     * @param reports weather request history
     * @param writer output
     */
    public void format(String location, List<WeatherReport> reports, PrintWriter writer) {
        var context = new HashMap<String, Object>();
        context.put("location", location);
        context.put("reports", reports);
        
        try {
            var template = freemarkerConfig.getTemplate("weather-history.ftl");
            template.process(context, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
