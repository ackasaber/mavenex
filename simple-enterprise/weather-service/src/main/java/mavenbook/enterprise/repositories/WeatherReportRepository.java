package mavenbook.enterprise.repositories;

import java.util.List;
import mavenbook.enterprise.entities.Location;
import mavenbook.enterprise.entities.WeatherReport;
import mavenbook.enterprise.entities.WeatherReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, WeatherReportId> {
    List<WeatherReport> findByLocation(Location loc);
}
