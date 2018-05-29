package ru.sfedu.logistics.entities;

import javax.persistence.Embeddable;

/**
 *
 * @author max
 */
@Embeddable
public class MaintenanceInfo {
    String description;
    private int mileage;

    public MaintenanceInfo() {
    }

    public MaintenanceInfo(String description, int mileage) {
        this.description = description;
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "MaintenanceInfo{" + "description=" + description + ", mileage=" + mileage + '}';
    }
    
}
