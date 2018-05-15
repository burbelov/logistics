package ru.sfedu.logistics;

import ru.sfedu.logistics.model.DataProvider;
import ru.sfedu.logistics.model.DataProviderResult;
import ru.sfedu.logistics.model.entities.Address;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Orders;
import ru.sfedu.logistics.model.entities.Route;
import ru.sfedu.logistics.model.entities.TypesOfCars;
import ru.sfedu.logistics.model.entities.User;

/**
 *
 * @author max
 */
public class LogisticsClient {
    
    public static void main(String[] args) {
        DataProvider dataPrivider = new DataProvider();
        Route route = new Route(new Address("city", "street", "number"), 
                new Address("city1", "street1", "number1"), 0);
        Customer customer = new Customer("asd", "sdfsdf", "zxcvxcv");
        dataPrivider.saveOrUpdate(customer);
        Orders order = new Orders((Customer) dataPrivider.getById(Customer.class, customer.getId()).getData().get(0), null, route, "dfsfsdf", 123L, TypesOfCars.LITTLE_CAR);
        
        dataPrivider.saveOrUpdate(order);
        System.out.println("fuck"+order);
        Orders newOrder = new Orders((Customer) dataPrivider.getById(Customer.class, customer.getId()).getData().get(0), null, route, "sdfsdf", 123L, TypesOfCars.LITTLE_CAR);
        newOrder.setId(order.getId());
        
        dataPrivider.saveOrUpdate(newOrder);
        System.out.println("fuck"+newOrder);
        Route route1 = new Route(new Address("city", "street", "number"), 
                new Address("city1", "street1", "number1"), 0);
        newOrder.setRoute(route1);
        
        dataPrivider.saveOrUpdate(newOrder);
        
        //dataPrivider.closeSessionFactory();
    }

}
