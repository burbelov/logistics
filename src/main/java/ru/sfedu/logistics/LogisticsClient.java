package ru.sfedu.logistics;

import ru.sfedu.logistics.model.DataProvider;
import ru.sfedu.logistics.model.DataProviderResult;
import ru.sfedu.logistics.model.entities.Address;
import ru.sfedu.logistics.model.entities.BigCar;
import ru.sfedu.logistics.model.entities.Car;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Driver;
import ru.sfedu.logistics.model.entities.LittleCar;
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
        Driver driver = new Driver(new BigCar("asdf", 1, 2), "login", "pas", "name");
        dataPrivider.saveOrUpdate(driver);
        System.out.println(dataPrivider.getDriverByLogin(driver.getLogin()).getData().get(0));
        
        
        //dataPrivider.closeSessionFactory();
    }

}
