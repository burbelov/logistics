package ru.sfedu.logistics.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author max
 */
@Entity
public class Customer extends User implements Serializable {
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String login, String password, String name) {
        super(login, password, name);
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
