package ru.sfedu.logistics.business;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.logistics.data_provider.DataProvider;
import ru.sfedu.logistics.data_provider.Result;
import ru.sfedu.logistics.data_provider.ResultStatuses;
import ru.sfedu.logistics.entities.HeavyTruck;
import ru.sfedu.logistics.entities.Customer;
import ru.sfedu.logistics.entities.Driver;
import ru.sfedu.logistics.entities.LightTruck;
import ru.sfedu.logistics.entities.OrderStatuses;
import ru.sfedu.logistics.entities.Orders;

/**
 *
 * @author max
 */
public class Business {
    
    private DataProvider dataPriveder = new DataProvider();
    private static final Logger logger = LogManager.getLogger(Business.class);

    public Result saveOrUpdateCustomer(Customer customer) {
        return dataPriveder.saveOrUpdate(customer);
    }
    
    public Result deleteCustomer(long customerId) {
        Result result = dataPriveder.getById(Customer.class, customerId);
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
    
    public Result getCustomerById(long customerId) {
        return dataPriveder.getById(Customer.class, customerId);
    }
    
    public Result getCustomerByLogin(String customerLogin) {
        return dataPriveder.getCustomerByLogin(customerLogin);
    }
    
    public Result saveOrUpdateDriver(Driver driver) {
        return dataPriveder.saveOrUpdate(driver);
    }
    
    public Result deleteDriver(long driverId) {
        Result result = dataPriveder.getById(Driver.class, driverId);
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
    
    public Result getDriverById(long driverId) {
        return dataPriveder.getById(Driver.class, driverId);
    }
    
    public Result getDriverByLogin(String driverLogin) {
        return dataPriveder.getCustomerByLogin(driverLogin);
    }
    
    public Result saveOrUpdateOrder(Orders order) {
        return dataPriveder.saveOrUpdate(order);
    }
    
    public Result deleteOrder(long orderId) {
        Result result = dataPriveder.getById(Customer.class, orderId);
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
    
    public Result getOrderById(long orderId) {
        return dataPriveder.getById(Orders.class, orderId);
    }
    
    public Result getOrders() {
        return dataPriveder.getOrders();
    }
    
    public Result takeOrder(long orderId, long driverId) {
        Result result = dataPriveder.getById(Orders.class, orderId);
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
            return new Result(
                    ResultStatuses.ERROR, 
                    null, 
                    Arrays.asList("The order is already being performed by another driver")
            );
        }
        
        if(order.getOrderStatus() == OrderStatuses.DONE) {
            return new Result(
                    ResultStatuses.ERROR, 
                    null, 
                    Arrays.asList("Order completed")
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
            case LIGHT_TRUCK:
                if(driver.getCar() instanceof LightTruck) {
                    flagTakeOrder = true;
                }
                break;
            case HEAVY_TRUCK:
                if(driver.getCar() instanceof HeavyTruck) {
                    flagTakeOrder = true;
                }
                break;
        }
        
        if(flagTakeOrder) {
            order.setDriver(driver);
            return dataPriveder.saveOrUpdate(order);
        } else {
            return new Result(
                    ResultStatuses.ERROR, 
                    null, 
                    Arrays.asList("Type of car does not fit")
            );
        }
    }
    
    public Result cancelOrder(long orderId, long driverId) {
        Result result = dataPriveder.getById(Orders.class, orderId);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                return result;
            case ERROR:
                break;
        }
        Orders order = (Orders) result.getData().get(0);
        if(order.getDriver().getId() == driverId) {
            order.setDriver(null);
            return dataPriveder.saveOrUpdate(order);
        } else {
            String msg = "You can not cancel this order, because the driver's id in the order does not match your id.";
            logger.error(msg);
            return new Result(ResultStatuses.ERROR, null, Arrays.asList(msg));
        }
    }
    
    public Result completeOrder(long orderId, long customerId) {
        Result result = dataPriveder.getById(Orders.class, orderId);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                return result;
            case ERROR:
                break;
        }
        Orders order = (Orders) result.getData().get(0);
        if(order.getCustomer().getId() == customerId) {
            order.setOrderStatus(OrderStatuses.DONE);
            dataPriveder.saveOrUpdate(order);
        } else {
            String msg = "You can not cancel this order, because the driver's id in the order does not match your id.";
            logger.error(msg);
            return new Result(ResultStatuses.ERROR, null, Arrays.asList(msg));
        }
        return new Result(ResultStatuses.SUCCESS, null, null);
    }
    
    public void close() {
        dataPriveder.closeSessionFactory();
    }

}
