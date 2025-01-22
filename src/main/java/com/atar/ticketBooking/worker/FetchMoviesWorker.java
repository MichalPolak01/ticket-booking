package com.atar.ticketBooking.worker;

import com.atar.ticketBooking.model.Movie;
import com.atar.ticketBooking.repository.MovieRepository;
import com.atar.ticketBooking.service.MovieService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class FetchMoviesWorker {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;


    @JobWorker(type = "fetch-movies")
    public void handleFetchMovies(JobClient client, ActivatedJob job) {
        System.out.println("Fetching movies...");

        // Fetching movies
        List<Movie> movies = movieService.getAllMovies();
        movieRepository.saveAll(movies);

        System.out.println("Movies fetched: " + movies);

        // Create process variable
        Map<String, Object> jobResultVariables = new HashMap<>();
        jobResultVariables.put("movies", movies);

        // End service task
        client.newCompleteCommand(job.getKey())
                .variables(jobResultVariables)
                .send()
                .join();
    }
}
