package ru.sfedu.logistics.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author max
 */
@Entity
public class LightTruck extends Car implements Serializable {

    public LightTruck(String nameCar, int fuelConsumption, int mileage) {
        super(nameCar, fuelConsumption, mileage);
    }

    public LightTruck() {
    }
    
    

}
