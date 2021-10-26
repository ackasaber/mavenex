package mavenbook.enterprise.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City entity.
 *
 * Since cities change names as well as countries change codes, the weather
 * service provides us with an artificial city identifier.
 */
@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "city_id")
    private int cityId;

    private String city;

    @Column(name = "country")
    private String countryCode;

    /**
     * Creates an uninitialized city object.
     */
    public City() {
    }

    /**
     * Returns the unique city identifier.
     *
     * @return the city identifier
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Sets the unique city identifier.
     *
     * @param cityId the city identifier
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     * Returns the city name.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city name.
     *
     * @param city the city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the code of the city's country.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the code of the city's country.
     *
     * @param countryCode the country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
