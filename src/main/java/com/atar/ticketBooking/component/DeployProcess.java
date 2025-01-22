package com.atar.ticketBooking.component;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DeployProcess implements CommandLineRunner {

    @Autowired
    private ZeebeClient zeebeClient;

    @Override
    public void run(String... args) throws Exception {
        zeebeClient.newDeployCommand()
                .addResourceFromClasspath("process.bpmn")
                .send()
                .join();

        System.out.println("Process deployed successfully!");
    }
}
