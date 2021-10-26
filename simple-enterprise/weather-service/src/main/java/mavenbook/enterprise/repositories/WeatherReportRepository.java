package mavenbook.enterprise.repositories;

import java.util.List;
import mavenbook.enterprise.entities.City;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.entities.WeatherReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Weather report Spring Data JPA repository.
 */
@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, WeatherReportId> {
    /**
     * Searches for all weather reports for a given city.
     * 
     * @param city the city
     * @return list of found weather reports
     */
    List<WeatherReport> findByCity(City city);
}
