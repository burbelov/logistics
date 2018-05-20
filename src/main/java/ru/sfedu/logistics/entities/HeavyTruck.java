package ru.sfedu.logistics.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author max
 */
@Entity
public class HeavyTruck extends Car implements Serializable {

    public HeavyTruck(String nameCar, int fuelConsumption, int mileage) {
        super(nameCar, fuelConsumption, mileage);
    }

    public HeavyTruck() {
    }
    
    

}
