package mavenbook.enterprise.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import mavenbook.enterprise.entities.City;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.test.WeatherTestUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test for Spring Data JPA repositories.
 */
@SpringJUnitConfig(locations = {"/context.xml", "/test-context.xml"})
@Transactional
public class RepositoryTest {
    @Autowired
    private CityRepository locationRepo;
    
    @Autowired
    private WeatherReportRepository weatherReportRepo;
    
    @Resource(name = "example-weather-report")
    private WeatherReport correct;
    
    @Resource(name = "new-city")
    private City newCity;
    
    @Resource(name = "new-weather-report")
    private WeatherReport newReport;
    
    /**
     * Initializes the database.
     *
     * @param dataSource the data source
     * @throws SQLException normally shouldn't
     */
    @BeforeAll
    public static void setup(@Autowired DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("schema.sql"));
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("test-data.sql"));
        }
    }

    /**
     * Tests searching for a city by the location string.
     */
    @Test
    public void locationFindTest() {
        var city = locationRepo.find("Minsk");
        WeatherTestUtils.assertEqual(city, correct.getCity());
        var city2 = locationRepo.find("Minsk,BY");
        WeatherTestUtils.assertEqual(city2, correct.getCity());
    }    
    
    /**
     * Tests persisting a city.
     */
    @Test
    public void locationPersistTest() {
        /*var city = new City();
        city.setCityId(593116);
        city.setCity("Vilnius");
        city.setCountryCode("LT");
        locationRepo.save(city);
        
        var city2 = locationRepo.findById(593116);
        assertTrue(city2.isPresent());
        assertEquals(city2.get().getCity(), "Vilnius");
        assertEquals(city2.get().getCountryCode(), "LT");*/
        locationRepo.save(newCity);
        var city = locationRepo.findById(newCity.getCityId());
        assertTrue(city.isPresent());
        WeatherTestUtils.assertEqual(city.get(), newCity);
    }
    
    /**
     * Tests searching for weather reports in a given city.
     */
    @Test
    public void weatherFindTest() {
        var city = locationRepo.find("Minsk");
        var recentReports = weatherReportRepo.findByCity(city);
        assertEquals(recentReports.size(), 1);
        var weatherReport = recentReports.get(0);
        WeatherTestUtils.assertEqual(weatherReport, correct);
    }
    
    /**
     * Tests persisting a new weather report.
     */
    @Test
    public void weatherPersistTest() {
        locationRepo.save(newReport.getCity());
        weatherReportRepo.save(newReport);
        var reports = weatherReportRepo.findByCity(newReport.getCity());
        assertEquals(reports.size(), 1);
        WeatherTestUtils.assertEqual(reports.get(0), newReport);
    }
}
