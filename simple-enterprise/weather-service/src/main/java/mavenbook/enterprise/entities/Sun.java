package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Sun {
    private LocalDateTime sunrise;
     
    private LocalDateTime sunset;

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }
    
}
