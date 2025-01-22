package com.atar.ticketBooking.controller;

import com.atar.ticketBooking.model.Movie;
import com.atar.ticketBooking.repository.MovieRepository;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ZeebeClient zeebeClient;

    @GetMapping("/select-movie-form")
    public String showSelectMovieForm(Model model) {
        List<Movie> movies = movieRepository.findAll();

        System.out.println("Fetched movies: "+ movies);

        model.addAttribute("movies", movies);

        return "select-movie-form";
    }

    @PostMapping("/submit-selected-movie")
    public String handleSelectedMovie(@RequestParam("selectedMovie") Long selectedMovieId, Model model) {
        // TODO Run next service task
        Map<String, Object> variables = new HashMap<>();
        variables.put("selectedMovieId", selectedMovieId);

        System.out.println("Selected Movie ID: "+ selectedMovieId);

        zeebeClient.newPublishMessageCommand()
                .messageName("selected-movie-message")
                .correlationKey("correlation-key") // Dodaj klucz korelacji, np. ID procesu
                .variables(variables)
                .send()
                .join();

        // Przekierowanie na stronę z potwierdzeniem
        model.addAttribute("message", "Movie selected successfully!");
        return "confirmation"; // Strona potwierdzenia
    }
}
