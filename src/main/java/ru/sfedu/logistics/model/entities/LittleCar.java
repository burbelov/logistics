package ru.sfedu.logistics.model.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author max
 */
@Entity
public class LittleCar extends Car implements Serializable {

    public LittleCar(String nameCar, int fuelConsumption, int mileage) {
        super(nameCar, fuelConsumption, mileage);
    }

    public LittleCar() {
    }
    
    

}
