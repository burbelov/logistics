package ru.sfedu.logistics.api;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.logistics.model.DataProvider;
import ru.sfedu.logistics.model.DataProviderResult;
import ru.sfedu.logistics.model.DataProviderStatuses;
import ru.sfedu.logistics.model.entities.BigCar;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Driver;
import ru.sfedu.logistics.model.entities.LittleCar;
import ru.sfedu.logistics.model.entities.OrderStatuses;
import ru.sfedu.logistics.model.entities.Orders;

/**
 *
 * @author max
 */
public class API {
    
    private DataProvider dataPriveder;
    private static final Logger logger = LogManager.getLogger(API.class);

    public DataProviderResult saveOrUpdateCustomer(Customer customer) {
        return dataPriveder.saveOrUpdate(customer);
    }
    
    public DataProviderResult deleteCustomer(Customer customer) {
        return dataPriveder.delete(customer);
    }
    
    public DataProviderResult saveOrUpdateDriver(Driver driver) {
        return dataPriveder.saveOrUpdate(driver);
    }
    
    public DataProviderResult deleteDriver(long id) {
        DataProviderResult result = dataPriveder.getById(Driver.class, id);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                return result;
            case ERROR:
                break;
        }
        return dataPriveder.delete(result.getData().get(0));
    }
    
    public DataProviderResult saveOrUpdateOrder(Orders order) {
        return dataPriveder.saveOrUpdate(order);
    }
    
    public DataProviderResult deleteOrder(Orders order) {
        return dataPriveder.delete(order);
    }
    
    public DataProviderResult takeOrder(long orderId, long driverId) {
        DataProviderResult result = dataPriveder.getById(Orders.class, orderId);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                return result;
            case ERROR:
                break;
        }
        Orders order = (Orders) result.getData().get(0);
        
        if(order.getDriver() != null) {
            return new DataProviderResult(
                    DataProviderStatuses.ERROR, 
                    null, 
                    Arrays.asList("Type of car does not fit")
            );
        }
        
        if(order.getOrderStatus() == OrderStatuses.DONE) {
            return new DataProviderResult(
                    DataProviderStatuses.ERROR, 
                    null, 
                    Arrays.asList("Type of car does not fit")
            );
        }
        
        result = dataPriveder.getById(Driver.class, driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                return result;
            case ERROR:
                break;
        }
        Driver driver = (Driver) result.getData().get(0);
        
        boolean flagTakeOrder = false;
        switch(order.getTypeOfCar()) {
            case LITTLE_CAR:
                if(driver.getCar() instanceof LittleCar) {
                    flagTakeOrder = true;
                }
                break;
            case BIG_CAR:
                if(driver.getCar() instanceof BigCar) {
                    flagTakeOrder = true;
                }
                break;
        }
        
        if(flagTakeOrder) {
            order.setDriver(driver);
            dataPriveder.saveOrUpdate(order);
        } else {
            return new DataProviderResult(
                    DataProviderStatuses.ERROR, 
                    null, 
                    Arrays.asList("Type of car does not fit")
            );
        }
        return new DataProviderResult(DataProviderStatuses.SUCCESS, null, null);
    }

}
