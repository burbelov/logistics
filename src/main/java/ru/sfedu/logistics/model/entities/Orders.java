package ru.sfedu.logistics.model.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author max
 */
@Entity
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DRIVER_ID", nullable = true)
    private Driver driver;
    
    @OneToOne(
            optional = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Route route;
    private String cargo;
    private long dateOfExecution;
    private TypesOfCars typeOfCar;
    private OrderStatuses orderStatus;

    public Orders(Customer customer, Driver driver, Route route, String cargo, long dateOfExecution, TypesOfCars typeOfCar) {
        this.customer = customer;
        this.driver = driver;
        this.route = route;
        this.cargo = cargo;
        this.dateOfExecution = dateOfExecution;
        this.typeOfCar = typeOfCar;
        this.orderStatus = OrderStatuses.NOT_DONE;
    }

    public Orders() {
        this.orderStatus = OrderStatuses.NOT_DONE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public long getDate() {
        return dateOfExecution;
    }

    public void setDate(long date) {
        this.dateOfExecution = date;
    }    

    public TypesOfCars getTypeOfCar() {
        return typeOfCar;
    }

    public void setTypeOfCar(TypesOfCars typeOfCar) {
        this.typeOfCar = typeOfCar;
    }

    public long getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(long dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public OrderStatuses getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatuses orderStatus) {
        this.orderStatus = orderStatus;
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
        final Orders other = (Orders) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", customer=" + customer + ", driver=" + driver + ", route=" + route + ", cargo=" + cargo + ", dateOfExecution=" + dateOfExecution + ", typeOfCar=" + typeOfCar + ", orderStatus=" + orderStatus + '}';
    }
    
    
}
