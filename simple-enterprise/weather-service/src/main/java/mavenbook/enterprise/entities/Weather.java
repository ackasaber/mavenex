package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }
    
}
