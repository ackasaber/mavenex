package mavenbook.enterprise.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Information about temperature measurements.
 */
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

    /**
     * Creates an unitialized current temperature object.
     */
    public Temperature() {
    }

    /**
     * Returns the current temperature.
     * 
     * @return the current temperature in °C
     */
    public BigDecimal getCurrent() {
        return current;
    }

    /**
     * Sets the current temperature.
     * 
     * @param current the current temperature in °C
     */
    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

    /**
     * Returns the current percieved temperature.
     * 
     * @return the current percieved temperature in °C
     */
    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    /**
     * Sets the current percieved temperature.
     * 
     * @param feelsLike the current percieved temperature in °C
     */
    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Returns the minimum current temperature across the city.
     * 
     * @return the current temperature in °C
     */
    public BigDecimal getCityMin() {
        return cityMin;
    }

    /**
     * Sets the minimum current temperature across the city.
     * 
     * @param cityMin the current temperature in °C
     */
    public void setCityMin(BigDecimal cityMin) {
        this.cityMin = cityMin;
    }

    /**
     * Returns the maximum current temperature across the city.
     * 
     * @return the current temperature in °C
     */
    public BigDecimal getCityMax() {
        return cityMax;
    }

    /**
     * Sets the maximum current temperature across the city.
     * 
     * @param cityMax the current temperature in °C
     */
    public void setCityMax(BigDecimal cityMax) {
        this.cityMax = cityMax;
    }
    
}
