package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.List;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(List<Ticket> tickets, User user) {
        return null;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return null;
    }
}
