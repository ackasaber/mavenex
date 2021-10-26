package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;

/**
 * The atmosphere information.
 */
@Embeddable
public class Atmosphere {
    private int humidity;
    
    private int pressure;
    
    private String clouds;

    private int visibility;

    /**
     * Creates an uninitialized atmospheric data object.
     */
    public Atmosphere() {
    }

    /**
     * Returns air humidity.
     * 
     * @return air humidity, in %
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Sets air humidity data.
     * 
     * @param humidity air humidity in %
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * Returns atmospheric pressure.
     * 
     * @return atmospheric pressure in hPa
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * Sets atmospheric pressure.
     * 
     * @param pressure atmospheric pressure in hPa
     */
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    /**
     * Returns the cloud information.
     * 
     * @return a human-readable description of the cloud quality
     */
    public String getClouds() {
        return clouds;
    }

    /**
     * Sets the cloud information.
     * 
     * @param clouds a human-readable description of the clouds quality
     */
    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    /**
     * Returns the meteorological visibility.
     * 
     * @return meteorological visibility in meters
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Sets the meteorological visibility.
     * 
     * @param visibility meteorological visibility in meters
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    
}
