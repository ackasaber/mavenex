package mavenbook.enterprise.repositories;

import mavenbook.enterprise.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT loc FROM Location loc " +
           "WHERE loc.city = :location OR " +
                  "CONCAT(loc.city, ',', loc.countryCode) = :location")
    Location find(@Param("location") String location);
}
