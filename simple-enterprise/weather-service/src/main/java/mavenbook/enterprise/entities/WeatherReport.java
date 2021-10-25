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

@Entity
@IdClass(WeatherReportId.class)
@Table(name = "weather")
public class WeatherReport {
    @Id
    @ManyToOne
    @JoinColumn(name = "city_id")
    private Location location;
    
    @Id
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Embedded
    private Weather weather;
/*
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
*/
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    
}
