package mavenbook.enterprise.openweathermap;

import javax.annotation.Resource;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.test.WeatherTestUtils;
import org.dom4j.DocumentException;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Unit test for parsing OpenWeatherMap current weather report.
 */
@SpringJUnitConfig(locations = "/test-context.xml")
public class OpenWeatherMapParserTest 
{
    @Resource(name = "example-weather-report")
    private WeatherReport correct;
    
    /**
     * Test parsing pre-defined OpenWeatherMap XML document.
     * 
     * @throws DocumentException normally shouldn't
     */
    @Test
    public void testParsing() throws DocumentException
    {
        var classLoader = getClass().getClassLoader();
        var minskXml = classLoader.getResourceAsStream("minsk-weather.xml");
        var parser = new OpenWeatherMapParser();
        var weatherReport = parser.parse(minskXml);
        WeatherTestUtils.assertEqual(weatherReport, correct);
    }
}
