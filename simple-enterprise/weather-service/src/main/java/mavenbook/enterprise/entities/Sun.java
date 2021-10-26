package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * Astronomic information about the Sun.
 */
@Embeddable
public class Sun {
    private LocalDateTime sunrise;
     
    private LocalDateTime sunset;

    /**
     * Creates an uninitialized solar astronomic information object.
     */
    public Sun() {
    }

    /**
     * Returns the sunrise time.
     * 
     * @return the UTC time of sunrise
     */
    public LocalDateTime getSunrise() {
        return sunrise;
    }

    /**
     * Sets the sunrise time.
     * 
     * @param sunrise the UTC time of sunrise
     */
    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Returns the sunset time.
     * 
     * @return the UTC time of sunset
     */
    public LocalDateTime getSunset() {
        return sunset;
    }

    /**
     * Sets the sunset time.
     * 
     * @param sunset the UTC time of sunset
     */
    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }
    
}
