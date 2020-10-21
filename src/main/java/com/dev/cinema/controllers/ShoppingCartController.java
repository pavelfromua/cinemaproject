package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.mapper.ShoppingCartMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper cartMapper;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartMapper cartMapper,
                                  MovieSessionService movieSessionService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.cartMapper = cartMapper;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/movie-session", method = RequestMethod.POST)
    public String addMovieSession(@RequestBody ShoppingCartRequestDto requestDto) {
        MovieSession movieSession = movieSessionService.getById(requestDto.getMovieSessionId());
        User user = userService.findById(requestDto.getUserId());
        shoppingCartService.addSession(movieSession, user);

        return "Movie session added to shopping cart ";
    }

    @RequestMapping(value = "/by-user", method = RequestMethod.GET)
    public ShoppingCartResponseDto getByUserId(@RequestBody ShoppingCartRequestDto requestDto) {
        return cartMapper
                .toDto(shoppingCartService
                        .getByUser(userService
                                .findById(requestDto.getUserId())));
    }
}
