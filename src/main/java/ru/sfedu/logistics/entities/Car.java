package ru.sfedu.logistics.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OrderColumn;

/**
 *
 * @author max
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Car implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameCar;
    private int fuelConsumption;
    private int mileage;
    private TypesOfCars typeOfCar;
    @ElementCollection
    @OrderColumn
    private List<MaintenanceInfo> maintenanceHistory = new ArrayList<>();

    public Car(String nameCar, int fuelConsumption, int mileage, TypesOfCars typeOfCar) {
        this.nameCar = nameCar;
        this.fuelConsumption = fuelConsumption;
        this.mileage = mileage;
        this.typeOfCar = typeOfCar;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public TypesOfCars getTypeOfCar() {
        return typeOfCar;
    }

    public void setTypeOfCar(TypesOfCars typeOfCar) {
        this.typeOfCar = typeOfCar;
    }

    public List<MaintenanceInfo> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMaintenanceHistory(List<MaintenanceInfo> maintenanceHistory) {
        this.maintenanceHistory = maintenanceHistory;
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
        final Car other = (Car) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", nameCar=" + nameCar + ", fuelConsumption=" + fuelConsumption + ", mileage=" + mileage + ", typeOfCar=" + typeOfCar + ", maintenanceHistory=" + maintenanceHistory + '}';
    }
    
}
