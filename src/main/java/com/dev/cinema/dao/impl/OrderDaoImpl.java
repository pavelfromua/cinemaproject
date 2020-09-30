package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(List<Ticket> tickets, User user) {
        Order order = new Order(tickets, user);
        order.setOrderDate(LocalDateTime.now());

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(order);
            transaction.commit();
            order.setId(itemId);

            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Order entity", e);
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery(
                    "FROM Order o "
                            + "LEFT JOIN FETCH o.tickets "
                            + "WHERE o.user.id= :user_id",Order.class);
            query.setParameter("user_id", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available orders", e);
        }
    }
}
