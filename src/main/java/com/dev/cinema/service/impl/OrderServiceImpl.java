package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Inject
    ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.create(tickets, user);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
