package com.atar.ticketBooking.worker;

import com.atar.ticketBooking.service.MovieService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Component
public class FetchPlayingDatesWorker {

    @Autowired
    private MovieService movieService;

    @JobWorker(type = "fetch-playing-dates")
    public void fetchPlayingDates(final JobClient client, final ActivatedJob job) {
//        TODO NOT WORKING
        System.out.println("Worker fetch-playing-dates running!");

        // Get process variables
        Map<String, Object> variables = job.getVariablesAsMap();
        Long selectedMovieId = (Long) variables.get("selectedMovieId");

        // Fetch dates for movieId
        List<String> playingDates = movieService.getPlayingDatesByMovieId(selectedMovieId);

        // Set new process variable
        Map<String, Object> resultVariables = new HashMap<>();
        resultVariables.put("playingDates", playingDates);

        // End service task
        client.newCompleteCommand(job.getKey())
                .variables(resultVariables)
                .send()
                .join();
    }
}
