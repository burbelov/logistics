package ru.sfedu.logistics.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author max
 */
@Entity
public class Route implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name="city",
            column = @Column(name="ADDRESS_FROM_CITY")),
    @AttributeOverride(name="street",
                        column = @Column(name="ADDRESS_FROM_STREET")),
    @AttributeOverride(name="number",
                        column = @Column(name="ADDRESS_FROM_NUMBER"))
    })
    private Address addressFrom;
    
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name="city",
            column = @Column(name="ADDRESS_TO_CITY")),
    @AttributeOverride(name="street",
                        column = @Column(name="ADDRESS_TO_STREET")),
    @AttributeOverride(name="number",
                        column = @Column(name="ADDRESS_TO_NUMBER"))
    })
    private Address addressTo;
    private int distance;

    public Route(Address addressFrom, Address addressTo, int distance) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.distance = distance;
    }

    public Route() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(Address addressFrom) {
        this.addressFrom = addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Address addressTo) {
        this.addressTo = addressTo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
        final Route other = (Route) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", addressFrom=" + addressFrom + ", addressTo=" + addressTo + ", distance=" + distance + '}';
    }

}
