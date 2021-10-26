package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Weather information in a particular moment of time in a particular city.
 */
@Embeddable
public class Weather {
    private String summary;
    
    @Embedded
    private Temperature temperature;
    
    private String precipitation;
    
    @Embedded
    private Wind wind;
    
    @Embedded
    private Atmosphere atmosphere;
    
    @Embedded
    private Sun sun;

    /**
     * Creates an inunitialized weather object.
     */
    public Weather() {
    }

    /**
     * Returns the human-readable summary of the current weather conditions.
     * 
     * @return the weather summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the human-readable summary of the current weather conditions.
     * 
     * @param summary the weather summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Returns the current temperature info.
     * 
     * @return the current temperature info
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * Sets the current temperature info.
     * 
     * @param temperature the current temperature info
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns the current precipitation mode.
     * 
     * @return the current precipitation mode
     */
    public String getPrecipitation() {
        return precipitation;
    }

    /**
     * Sets the current precipitation mode.
     * 
     * @param precipitation the current precipitation mode
     */
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * Returns the current wind information.
     * 
     * @return the wind information
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets the current wind information.
     * 
     * @param wind the wind information
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Returns the current atmospheric measurements.
     * 
     * @return atmospheric measurements
     */
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    /**
     * Sets the current atmospheric measurements.
     * 
     * @param atmosphere atmospheric measurements
     */
    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    /**
     * Returns solar astronomic information.
     * 
     * @return solar astronomic information
     */
    public Sun getSun() {
        return sun;
    }

    /**
     * Sets solar astronomic information.
     * 
     * @param sun solar astronomic information
     */
    public void setSun(Sun sun) {
        this.sun = sun;
    }
    
}
