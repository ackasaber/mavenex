package mavenbook.enterprise.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class WeatherReportId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private LocalDateTime updatedAt;
    
    // cityId, the name should match the member of WeatherReport class
    private int location;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.updatedAt);
        hash = 23 * hash + this.location;
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
        if (this.location != other.location) {
            return false;
        }
        if (!Objects.equals(this.updatedAt, other.updatedAt)) {
            return false;
        }
        return true;
    }
    
}
