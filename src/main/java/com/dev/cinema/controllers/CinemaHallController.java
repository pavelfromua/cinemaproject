package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.model.dto.mapper.CinemaHallMapper;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaHallController {
    private final CinemaHallMapper cinemaHallMapper;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallMapper cinemaHallMapper,
                                CinemaHallService cinemaHallService) {
        this.cinemaHallMapper = cinemaHallMapper;
        this.cinemaHallService = cinemaHallService;
    }

    @RequestMapping(value = "/cinema-halls", method = RequestMethod.POST)
    public CinemaHallResponseDto addCinemaHall(@RequestBody CinemaHallRequestDto requestDto) {
        return cinemaHallMapper.toDto(cinemaHallService.add(cinemaHallMapper
                .toEntity(requestDto)));
    }

    @RequestMapping(value = "/cinema-halls", method = RequestMethod.GET)
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream().map(cinemaHallMapper::toDto)
                .collect(Collectors.toList());
    }
}
