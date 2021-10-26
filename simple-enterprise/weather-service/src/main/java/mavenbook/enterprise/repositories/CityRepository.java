package mavenbook.enterprise.repositories;

import mavenbook.enterprise.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * City Spring Data JPA repository.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    /**
     * Searches for the city by location string.
     * 
     * The location string is either the city name or the city name followed
     * by a comma and the country code.
     * 
     * @param location the location string
     * @return the city entity
     */
    @Query("SELECT c FROM City c " +
           "WHERE c.city = :location OR " +
                  "CONCAT(c.city, ',', c.countryCode) = :location")
    City find(@Param("location") String location);
}
