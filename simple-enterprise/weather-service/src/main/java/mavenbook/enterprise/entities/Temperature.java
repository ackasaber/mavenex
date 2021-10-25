package mavenbook.enterprise.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Temperature {
    @Column(name = "temperature")
    private BigDecimal current;
    
    @Column(name = "feels_like")
    private BigDecimal feelsLike;
    
    @Column(name = "city_min")
    private BigDecimal cityMin;
    
    @Column(name = "city_max")
    private BigDecimal cityMax;

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    public BigDecimal getCityMin() {
        return cityMin;
    }

    public void setCityMin(BigDecimal cityMin) {
        this.cityMin = cityMin;
    }

    public BigDecimal getCityMax() {
        return cityMax;
    }

    public void setCityMax(BigDecimal cityMax) {
        this.cityMax = cityMax;
    }
    
}
