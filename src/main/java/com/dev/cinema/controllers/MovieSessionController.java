package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.mapper.MovieSessionMapper;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @RequestMapping(value = "/movie-sessions", method = RequestMethod.POST)
    public MovieSessionResponseDto addMovieSession(@RequestBody MovieSessionRequestDto requestDto) {
        return movieSessionMapper.toDto(movieSessionService
                .add(movieSessionMapper.toEntity(requestDto)));
    }

    @RequestMapping(value = "/movie-sessions/available?movieId={movieId}&date={date}",
            method = RequestMethod.GET)
    public List<MovieSessionResponseDto> getAvailable(Long movieId, LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::toDto).collect(Collectors.toList());
    }
}
