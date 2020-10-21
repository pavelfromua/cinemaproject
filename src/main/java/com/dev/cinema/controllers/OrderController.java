package com.dev.cinema.controllers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.mapper.OrderMapper;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderMapper orderMapper, OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/orders/complete", method = RequestMethod.POST)
    public OrderResponseDto completeOrder(@RequestBody OrderRequestDto requestDto) {
        User user = userService.findById(requestDto.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);

        return orderMapper.toDto(orderService.completeOrder(shoppingCart.getTickets(), user));
    }

    @RequestMapping(value = "/orders?{userId}", method = RequestMethod.GET)
    public List<OrderResponseDto> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrderHistory(userService.findById(userId))
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}


