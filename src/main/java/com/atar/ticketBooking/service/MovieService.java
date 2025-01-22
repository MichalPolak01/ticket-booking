package com.atar.ticketBooking.service;

import com.atar.ticketBooking.model.Movie;
import com.atar.ticketBooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<String> getPlayingDatesByMovieId(Long movieId) {
        // Symulacja dat grania filmu
        // W prawdziwej aplikacji dane te mogą pochodzić z zewnętrznego API lub bazy danych
        return Arrays.asList("2025-01-25", "2025-01-26", "2025-01-27");
    }
}
