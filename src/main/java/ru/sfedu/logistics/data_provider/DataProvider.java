package ru.sfedu.logistics.data_provider;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.logistics.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.logistics.entities.Customer;
import ru.sfedu.logistics.entities.Driver;
import ru.sfedu.logistics.entities.OrderStatuses;
import ru.sfedu.logistics.entities.Orders;

/**
 *
 * @author max
 */
public class DataProvider {
    
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LogManager.getLogger(DataProvider.class);
    
    public <T> Result saveOrUpdate(T bean) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.saveOrUpdate(bean);
                session.getTransaction().commit();
                return new Result(ResultStatuses.SUCCESS, null, null);
            } catch (Exception e) {
                logger.error(e);
                return new Result(
                        ResultStatuses.ERROR, null, Arrays.asList(e));
            }
        }
    }
    
    public <T> Result getById(Class classInfo, long id) {
        try (Session session = sessionFactory.openSession()) {
            T bean = (T) session.get(classInfo, id);
            return bean != null
                    ? new Result(ResultStatuses.SUCCESS, Arrays.asList(bean), null)
                    : new Result(ResultStatuses.NOT_FOUND, null, null);
        }
    }
    
    public Result getCustomerByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            List<Customer> customer = session.createQuery("from Customer where login = :login")
                    .setParameter("login", login)
                    .list();
            return customer.isEmpty()
                    ? new Result(ResultStatuses.NOT_FOUND, null, null)
                    : new Result(ResultStatuses.SUCCESS, customer, null);
        }
    }
    
    public Result getDriverByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            List<Driver> driver = session.createQuery("from Driver where login = :login")
                    .setParameter("login", login)
                    .list();
            return driver.isEmpty()
                    ? new Result(ResultStatuses.NOT_FOUND, null, null)
                    : new Result(ResultStatuses.SUCCESS, driver, null);
        }
    }
    
    public Result getOrders() {
        try (Session session = sessionFactory.openSession()) {
            List<Orders> orders = session.createQuery("from Orders "
                    + "where orderStatus = :orderStatus and driver = null")
                    .setParameter("orderStatus", OrderStatuses.NOT_DONE)
                    .list();
            return orders.isEmpty()
                    ? new Result(ResultStatuses.NOT_FOUND, null, null)
                    : new Result(ResultStatuses.SUCCESS, orders, null);
        }
    }
    
    public <T> Result delete(T bean) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(bean);
                session.getTransaction().commit();
                return new Result(ResultStatuses.SUCCESS, null, null);
            } catch (Exception e) {
                logger.error(e);
                return new Result(
                        ResultStatuses.ERROR, null, Arrays.asList(e));
            }
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}