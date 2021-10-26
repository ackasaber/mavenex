package mavenbook.enterprise.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;

/**
 * Weather report entity.
 * 
 * Contains weather observations together with the timestamp and
 * location point.
 */
@Entity
@IdClass(WeatherReportId.class)
@Table(name = "weather")
public class WeatherReport {
    @Id
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    
    @Id
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Embedded
    private Weather weather;

    /**
     * Creates an uninitialized weather report object.
     */
    public WeatherReport() {
    }

    /**
     * Returns the city where the weather observations took place.
     * 
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the city where the weather observations took place.
     * 
     * @param city the city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Returns the timestamp of weather observations.
     * 
     * @return the timestamp in UTC
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of weather observations.
     * 
     * @param updatedAt the timestamp in UTC
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the weather observations data.
     * 
     * @return weather observations data
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * Sets the weather observations data.
     * 
     * @param weather weather observations data
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    
}
