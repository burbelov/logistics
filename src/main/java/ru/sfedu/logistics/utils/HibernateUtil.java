package ru.sfedu.logistics.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.logistics.model.entities.Address;
import ru.sfedu.logistics.model.entities.BigCar;
import ru.sfedu.logistics.model.entities.Car;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Driver;
import ru.sfedu.logistics.model.entities.LittleCar;
import ru.sfedu.logistics.model.entities.Orders;
import ru.sfedu.logistics.model.entities.Route;
import ru.sfedu.logistics.model.entities.User;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry 
                    = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources = 
                    new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(User.class);
            metadataSources.addAnnotatedClass(Car.class);
            metadataSources.addAnnotatedClass(Driver.class);
            metadataSources.addAnnotatedClass(Customer.class);
            metadataSources.addAnnotatedClass(LittleCar.class);
            metadataSources.addAnnotatedClass(BigCar.class);
            metadataSources.addAnnotatedClass(Route.class);
            metadataSources.addAnnotatedClass(Address.class);
            metadataSources.addAnnotatedClass(Orders.class);
            metadataSources.addResource("hibernate.hbm.xml");
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
}
