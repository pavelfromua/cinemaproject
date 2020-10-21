package com.dev.cinema.model.dto.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketId(shoppingCart
                        .getTickets()
                        .stream()
                        .map(Ticket::getId)
                        .collect(Collectors.toList()));
        dto.setUserId(shoppingCart.getUser().getId());

        return dto;
    }
}
