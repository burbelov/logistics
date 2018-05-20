package ru.sfedu.logistics.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author max
 */
@Entity
public class Driver extends User implements Serializable {

    @OneToOne(
            optional = false,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Car car;
    
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    public Driver() {
    }

    public Driver(Car car, String login, String password, String name) {
        super(login, password, name);
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
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
        final Driver other = (Driver) obj;
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Driver{" + "id=" + getId() + ", login=" + getLogin() 
                + ", password=" + getPassword() + ", name=" + getName() + "car=" + car + '}';
    }

    

}
