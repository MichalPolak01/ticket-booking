package com.atar.ticketBooking.worker;


import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class FetchMoviesWorker {

    @JobWorker(type = "fetch-movies") // Typ musi pasować do "Job Type" w BPMN
    public void handleFetchMoviesJob(JobClient client, ActivatedJob job) {
        // Logika biznesowa
        System.out.println("Fetching movies...");
        System.out.println("Job variables: " + job.getVariables());

        // Zakończenie zadania
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
