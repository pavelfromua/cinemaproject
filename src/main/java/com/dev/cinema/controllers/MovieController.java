package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.model.dto.mapper.MovieMapper;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    public MovieController(MovieMapper movieMapper, MovieService movieService) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public MovieResponseDto addMovie(@RequestBody MovieRequestDto requestDto) {
        return movieMapper.toDto(movieService.add(movieMapper.toEntity(requestDto)));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream().map(movieMapper::toDto).collect(Collectors.toList());
    }
}
