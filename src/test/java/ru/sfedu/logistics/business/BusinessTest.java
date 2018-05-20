package ru.sfedu.logistics.business;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.logistics.data_provider.Result;
import ru.sfedu.logistics.entities.Address;
import ru.sfedu.logistics.entities.Car;
import ru.sfedu.logistics.entities.Customer;
import ru.sfedu.logistics.entities.Driver;
import ru.sfedu.logistics.entities.LightTruck;
import ru.sfedu.logistics.entities.OrderStatuses;
import ru.sfedu.logistics.entities.Orders;
import ru.sfedu.logistics.entities.Route;
import ru.sfedu.logistics.entities.TypesOfCars;
import static ru.sfedu.logistics.model.DataProviderTest.getRandomInt;
import static ru.sfedu.logistics.model.DataProviderTest.getRandomLong;
import static ru.sfedu.logistics.model.DataProviderTest.getRandomString;

/**
 *
 * @author max
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusinessTest {
    
    private static Customer customer;
    private static Driver driver;
    private static Orders order;
    private static Car car;
    private static Route route;
    private static Business business = new Business();
    
    public BusinessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        customer = new Customer(getRandomString(), getRandomString(), getRandomString());
        car = new LightTruck(getRandomString(), getRandomInt(), getRandomInt());
        driver = new Driver(car, getRandomString(), getRandomString(), getRandomString());
        route = new Route(new Address(getRandomString(), getRandomString(), getRandomString()), 
                        new Address(getRandomString(), getRandomString(), getRandomString()), 
                        getRandomInt());
        order = new Orders(customer, driver, route, getRandomString(), getRandomLong(), TypesOfCars.HEAVY_TRUCK);
        business.saveOrUpdateCustomer(customer);
        business.saveOrUpdateDriver(driver);
        business.saveOrUpdateOrder(order);
    }
    
    @AfterClass
    public static void tearDownClass() {
        business.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of takeOrder method, of class Business.
     */
    @Test
    public void testATakeOrder() {
        System.out.println("takeOrder");
        
        /*заказ уже выполняется*/
        Result result = business.takeOrder(order.getId(), driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("takeOrder - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        order.setDriver(null);
        business.saveOrUpdateOrder(order);
        
        /*не подходит тип автомобиля*/
        result = business.takeOrder(order.getId(), driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("takeOrder - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                break;
        }

        order.setOrderStatus(OrderStatuses.DONE);
        business.saveOrUpdateOrder(order);
        
        /*заказ уже выполнен*/
        result = business.takeOrder(order.getId(), driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("takeOrder - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        order.setOrderStatus(OrderStatuses.NOT_DONE);
        order.setTypeOfCar(TypesOfCars.LIGHT_TRUCK);
        business.saveOrUpdateOrder(order);
        
        result = business.takeOrder(order.getId(), driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                fail("takeOrder - тест не пройден!!!");
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("takeOrder - тест не пройден!!!");
                break;
        }
        
    }
    
    /**
     * Test of cancelOrder method, of class Business.
     */
    @Test
    public void testBCancelOrder() {
        System.out.println("cancelOrder");
        
        Result result = business.cancelOrder(order.getId(), getRandomLong());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("cancelOrder - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        result = business.cancelOrder(order.getId(), driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                fail("cancelOrder - тест не пройден!!!");
                break;
            case ERROR:
                fail("cancelOrder - тест не пройден!!!");
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        result = business.getOrderById(order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                if(((Orders)(result.getData().get(0))).getDriver() != null) {
                    fail("cancelOrder - тест не пройден!!!");
                }
                break;
            case NOT_FOUND:
                fail("cancelOrder - тест не пройден!!!");
                break;
            case ERROR:
                fail("cancelOrder - тест не пройден!!!");
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
    }
    
    /**
     * Test of completeOrder method, of class Business.
     */
    @Test
    public void testBCompleteOrder() {
        System.out.println("completeOrder");
        
        Result result = business.completeOrder(order.getId(), getRandomLong());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("completeOrder - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        result = business.completeOrder(order.getId(), customer.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                fail("completeOrder - тест не пройден!!!");
                break;
            case ERROR:
                fail("completeOrder - тест не пройден!!!");
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
        
        result = business.getOrderById(order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                if(((Orders)(result.getData().get(0))).getOrderStatus() != OrderStatuses.DONE) {
                    fail("completeOrder - тест не пройден!!!");
                }
                break;
            case NOT_FOUND:
                fail("completeOrder - тест не пройден!!!");
                break;
            case ERROR:
                fail("completeOrder - тест не пройден!!!");
                result.getMessages().stream().forEach(System.out::println);
                break;
        }
    }
}
