package mavenbook.enterprise.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Atmosphere {
    private int humidity; // in %
    
    private int pressure; // in hPa
    
    private String clouds; // human-readable description

    private int visibility; // in m

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    
}
