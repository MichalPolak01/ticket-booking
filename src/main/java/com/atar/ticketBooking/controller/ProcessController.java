package com.atar.ticketBooking.controller;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ZeebeClient zeebeClient;

    @PostMapping("/start")
    public String startProcess(@RequestParam String variable) {
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("Process_ID") // Podaj ID procesu zdefiniowanego w BPMN
                .latestVersion()
                .variables(Map.of("variableName", variable)) // Przekazanie zmiennych do procesu
                .send()
                .join();

        return "Process started successfully!";
    }
}
