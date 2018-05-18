package ru.sfedu.logistics.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.logistics.model.entities.Address;
import ru.sfedu.logistics.model.entities.Car;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Driver;
import ru.sfedu.logistics.model.entities.LittleCar;
import ru.sfedu.logistics.model.entities.OrderStatuses;
import ru.sfedu.logistics.model.entities.Orders;
import ru.sfedu.logistics.model.entities.Route;
import ru.sfedu.logistics.model.entities.TypesOfCars;

/**
 *
 * @author max
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataProviderTest {
    
    private static Customer customer;
    private static Driver driver;
    private static Orders order;
    private static Car car;
    private static Route route;
    private static DataProvider dataProvider;
    
    public DataProviderTest() {
    }
    
    public static int getRandomInt() {
        return (int)(Math.random() * 100000);
    }
    
    public static long getRandomLong() {
        return (long)(Math.random() * 100000);
    }
    
    public static String getRandomString() {
        String[] array = {"Mars", "Earth", "Jupiter", "Mercury", "Venus", 
            "Saturn", "Uranus", "Moon", "Pluto", "Neptune"};
        String str = array[(int)(Math.random() * array.length)];
        return str + (long)(Math.random() * 100000);
    }
    
    @BeforeClass
    public static void setUpClass() {
        customer = new Customer(getRandomString(), getRandomString(), getRandomString());
        car = new LittleCar(getRandomString(), getRandomInt(), getRandomInt());
        driver = new Driver(car, getRandomString(), getRandomString(), getRandomString());
        route = new Route(new Address(getRandomString(), getRandomString(), getRandomString()), 
                        new Address(getRandomString(), getRandomString(), getRandomString()), 
                        getRandomInt());
        order = new Orders(customer, driver, route, getRandomString(), getRandomLong(), TypesOfCars.BIG_CAR);
        dataProvider = new DataProvider();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        dataProvider.closeSessionFactory();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class DataProvider.
     */
    @Test
    public void testASave() {
        System.out.println("save");
        
        DataProviderResult result = dataProvider.saveOrUpdate(customer);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testASave - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.saveOrUpdate(driver);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testASave - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.saveOrUpdate(order);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testASave - тест не пройден!!!");
                break;
        }
    }

    /**
     * Test of getById method, of class DataProvider.
     */
    @Test
    public void testBGetById() {
        System.out.println("getById");
        
        DataProviderResult result = dataProvider.getById(Driver.class, driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(driver, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testBGetById - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
        result = dataProvider.getById(Customer.class, customer.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(customer, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testBGetById - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
        result = dataProvider.getById(Orders.class, order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(order, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testBGetById - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
    }
    
    /**
     * Test of getCustomerByLogin method, of class DataProvider.
     */
    @Test
    public void testCGetCustomerByLogin() {
        System.out.println("getCustomerByLogin");
        DataProviderResult result = dataProvider.getCustomerByLogin(customer.getLogin());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(customer, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testCGetCustomerByLogin - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
    }
    
    /**
     * Test of getDriverByLogin method, of class DataProvider.
     */
    @Test
    public void testDGetDriverByLogin() {
        System.out.println("getDriverByLogin");
        DataProviderResult result = dataProvider.getDriverByLogin(driver.getLogin());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(driver, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testDGetDriverByLogin - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
    }

    /**
     * Test of update method, of class DataProvider.
     */
    @Test
    public void testEUpdate() {
        System.out.println("update");
        customer.setLogin(getRandomString());
        driver.setLogin(getRandomString());
        driver.getCar().setNameCar(getRandomString());
        route.getAddressTo().setCity(getRandomString());
        order.setCargo(getRandomString());
        
        DataProviderResult result = dataProvider.saveOrUpdate(customer);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testCUpdate - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.saveOrUpdate(driver);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testCUpdate - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.saveOrUpdate(order);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testCUpdate - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Driver.class, driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(driver, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testCUpdate - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
        result = dataProvider.getById(Customer.class, customer.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(customer, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testCUpdate - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
        result = dataProvider.getById(Orders.class, order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(order, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("testCUpdate - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        
    }
    
    /**
     * Test of getOrders method, of class DataProvider.
     */
    @Test
    public void testFgetOrders() {
        System.out.println("getOrders");
        DataProviderResult result = dataProvider.getOrders();
        switch(result.getStatus()) {
            case SUCCESS:
                fail("getOrders - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                break;
        }
        order.setDriver(null);
        dataProvider.saveOrUpdate(order);
        result = dataProvider.getOrders();
        switch(result.getStatus()) {
            case SUCCESS:
                assertEquals(order, result.getData().get(0));
                System.out.println(result.getData().get(0));
                break;
            case NOT_FOUND:
                fail("getOrders - тест не пройден!!!");
                break;
            case ERROR:
                break;
        }
        order.setOrderStatus(OrderStatuses.DONE);
        dataProvider.saveOrUpdate(order);
        result = dataProvider.getOrders();
        switch(result.getStatus()) {
            case SUCCESS:
                fail("getOrders - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                break;
        }
    }
    
    /**
     * Test of delete method, of class DataProvider.
     */
    @Test
    public void testIDelete() {
        System.out.println("delete");
        
        /*
        * Проверка удаления order
        * При удалении order каскадно удаляется route
        */
        DataProviderResult result = dataProvider.delete(order);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Orders.class, order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                System.out.println(customer);
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Route.class, route.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                System.out.println(customer);
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        /*
        * Проверка удаления customer
        * При удалении customer каскадно удаляется order
        */
        route = new Route(new Address(getRandomString(), getRandomString(), getRandomString()), 
                        new Address(getRandomString(), getRandomString(), getRandomString()), 
                        getRandomInt());
        order = new Orders(customer, driver, route, getRandomString(), getRandomLong(), TypesOfCars.BIG_CAR);
        result = dataProvider.saveOrUpdate(order);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.delete(customer);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Customer.class, customer.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                System.out.println(customer);
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Orders.class, order.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                System.out.println(customer);
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        /*
        * Проверка удаления driver
        * При удалении driver каскадно удаляется car
        */
        result = dataProvider.delete(driver);
        switch(result.getStatus()) {
            case SUCCESS:
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Driver.class, driver.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
        
        result = dataProvider.getById(Car.class, car.getId());
        switch(result.getStatus()) {
            case SUCCESS:
                fail("testDDelete - тест не пройден!!!");
                break;
            case NOT_FOUND:
                break;
            case ERROR:
                result.getMessages().stream().forEach(System.out::println);
                fail("testDDelete - тест не пройден!!!");
                break;
        }
    }
    
}
