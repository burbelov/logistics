package ru.sfedu.logistics.model;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.logistics.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.logistics.model.entities.Customer;
import ru.sfedu.logistics.model.entities.Driver;
import ru.sfedu.logistics.model.entities.OrderStatuses;
import ru.sfedu.logistics.model.entities.Orders;

/**
 *
 * @author max
 */
public class DataProvider {
    
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LogManager.getLogger(DataProvider.class);
    
    public <T> DataProviderResult saveOrUpdate(T bean) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.saveOrUpdate(bean);
                session.getTransaction().commit();
                return new DataProviderResult(DataProviderStatuses.SUCCESS, null, null);
            } catch (Exception e) {
                logger.error(e);
                return new DataProviderResult(
                        DataProviderStatuses.ERROR, null, Arrays.asList(e));
            }
        }
    }
    
    public <T> DataProviderResult getById(Class classInfo, long id) {
        try (Session session = sessionFactory.openSession()) {
            T bean = (T) session.get(classInfo, id);
            return bean != null
                    ? new DataProviderResult(DataProviderStatuses.SUCCESS, Arrays.asList(bean), null)
                    : new DataProviderResult(DataProviderStatuses.NOT_FOUND, null, null);
        }
    }
    
    public DataProviderResult getCustomerByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            List<Customer> customer = session.createQuery("from Customer where login = :login")
                    .setParameter("login", login)
                    .list();
            return customer.isEmpty()
                    ? new DataProviderResult(DataProviderStatuses.NOT_FOUND, null, null)
                    : new DataProviderResult(DataProviderStatuses.SUCCESS, customer, null);
        }
    }
    
    public DataProviderResult getDriverByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            List<Driver> driver = session.createQuery("from Driver where login = :login")
                    .setParameter("login", login)
                    .list();
            return driver.isEmpty()
                    ? new DataProviderResult(DataProviderStatuses.NOT_FOUND, null, null)
                    : new DataProviderResult(DataProviderStatuses.SUCCESS, driver, null);
        }
    }
    
    public DataProviderResult getOrders() {
        try (Session session = sessionFactory.openSession()) {
            List<Orders> orders = session.createQuery("from Orders "
                    + "where orderStatus = :orderStatus and driver = null")
                    .setParameter("orderStatus", OrderStatuses.NOT_DONE)
                    .list();
            return orders.isEmpty()
                    ? new DataProviderResult(DataProviderStatuses.NOT_FOUND, null, null)
                    : new DataProviderResult(DataProviderStatuses.SUCCESS, orders, null);
        }
    }
    
    public <T> DataProviderResult delete(T bean) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(bean);
                session.getTransaction().commit();
                return new DataProviderResult(DataProviderStatuses.SUCCESS, null, null);
            } catch (Exception e) {
                logger.error(e);
                return new DataProviderResult(
                        DataProviderStatuses.ERROR, null, Arrays.asList(e));
            }
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}