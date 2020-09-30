package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order create(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
