package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.RegistrationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws RegistrationException, AuthenticationException {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie = movieService.add(movie);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));

        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDate.now());

        availableSessions.forEach(System.out::println);

        AuthenticationService authenticationService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        authenticationService.register("pavelfromua@gmail.com", "password");

        User user = authenticationService.login("pavelfromua@gmail.com", "password");

        ShoppingCartService bucketService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        MovieSession selectedMovieSession = availableSessions.get(0);
        bucketService.addSession(selectedMovieSession, user);
        ShoppingCart userBucket = bucketService.getByUser(user);

        User user2 = authenticationService.register("test@in.ua", "passw");
        bucketService.registerNewShoppingCart(user2);
    }
}
