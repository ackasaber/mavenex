package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import javax.persistence.Column;

@Embeddable
public class Wind {
    @Column(name = "wind_direction")
    private String direction;
    
    @Column(name = "wind_speed")
    private BigDecimal speed;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }
    
}
