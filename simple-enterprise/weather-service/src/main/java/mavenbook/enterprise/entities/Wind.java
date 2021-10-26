package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import javax.persistence.Column;

/**
 * Current wind information.
 */
@Embeddable
public class Wind {
    @Column(name = "wind_direction")
    private String direction;
    
    @Column(name = "wind_speed")
    private BigDecimal speed;

    /**
     * Returns the wind direction.
     * 
     * @return the wind direction in a human-readable form
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the wind direction.
     * 
     * @param direction the wind direction in a human-readable form
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Returns the wind speed.
     * 
     * @return wind speed in m/s
     */
    public BigDecimal getSpeed() {
        return speed;
    }

    /**
     * Sets the wind speed.
     * 
     * @param speed wind speed in m/s
     */
    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }
    
}
