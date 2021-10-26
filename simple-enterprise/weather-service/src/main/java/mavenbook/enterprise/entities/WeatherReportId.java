package mavenbook.enterprise.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A compound key for the weather report entity.
 * 
 * This class is not used directly, but is required by JPA.
 * Overrides and serializability are also JPA requirements.
 */
public class WeatherReportId implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @Serial A UTC timestamp.
     */
    private LocalDateTime updatedAt;
    
    /**
     * @Serial City identifier, integer.
     */
    // The field name matches the corresponding @Id member
    // of WeatherReport class so that JPA could find it.
    private int city;
    
    /**
     * Creates an uninitialized weather report identifier.
     */
    public WeatherReportId() {
    }

    /**
     * Returns the weather report timestamp.
     * 
     * @return the weather report timestamp in UTC
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the weather report timestamp.
     * 
     * @param updatedAt the weather report timestamp in UTC
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the city identifier.
     * 
     * @return the city identifier
     */
    public int getCity() {
        return city;
    }

    /**
     * Sets the city identifier.
     * 
     * @param cityId the city identifier
     */
    public void setCity(int cityId) {
        this.city = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.updatedAt);
        hash = 23 * hash + this.city;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeatherReportId other = (WeatherReportId) obj;
        if (this.city != other.city) {
            return false;
        }
        if (!Objects.equals(this.updatedAt, other.updatedAt)) {
            return false;
        }
        return true;
    }
    
}
