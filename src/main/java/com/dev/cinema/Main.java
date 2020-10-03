package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.RegistrationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    //private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws RegistrationException, AuthenticationException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setEmail("pavelfromua@gmail.com");
        user.setPassword("password");
        userService.add(user);
        /*
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie = movieService.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Matrix");
        movie2.setDiscription("Cool movie");
        movie2 = movieService.add(movie2);

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

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 30)));

        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession2);

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

        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);

        orderService.completeOrder(userBucket.getTickets(), user);

        List<MovieSession> availableSessions2 = movieSessionService
                .findAvailableSessions(movie2.getId(), LocalDate.now());
        bucketService.addSession(availableSessions2.get(0), user);
        User user2 = authenticationService.register("test@in.ua", "passw");*/
    }
}
