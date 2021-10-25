package mavenbook.enterprise.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import mavenbook.enterprise.entities.Location;
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

@SpringJUnitConfig(locations = {"/context.xml", "/example-weather-report.xml"})
@Transactional
public class RepositoryTest {
    @Autowired
    private LocationRepository locationRepo;
    
    @Autowired
    private WeatherReportRepository weatherReportRepo;
    
    @Resource(name = "example-weather-report")
    private WeatherReport correct;
    
    @BeforeAll
    static void setup(@Autowired DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("schema.sql"));
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("test-data.sql"));
        }
    }

    @Test
    public void locationFindTest() {
        var city = locationRepo.find("Minsk");
        WeatherTestUtils.assertEqual(city, correct.getLocation());
        var city2 = locationRepo.find("Minsk,BY");
        WeatherTestUtils.assertEqual(city2, correct.getLocation());
    }    
    
    @Test
    public void locationSaveTest() {
        var city = new Location();
        city.setCityId(593116);
        city.setCity("Vilnius");
        city.setCountryCode("LT");
        locationRepo.save(city);
        
        var city2 = locationRepo.findById(593116);
        assertTrue(city2.isPresent());
        assertEquals(city2.get().getCity(), "Vilnius");
        assertEquals(city2.get().getCountryCode(), "LT");
    }
    
    @Test
    public void weatherFindTest() {
        var city = locationRepo.find("Minsk");
        var recentReports = weatherReportRepo.findByLocation(city);
        assertEquals(recentReports.size(), 1);
        var weatherReport = recentReports.get(0);
        WeatherTestUtils.assertEqual(weatherReport, correct);
    }

    // TODO add a persist weather report test and thoughroughly comment everything
}
