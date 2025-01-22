//package com.atar.ticketBooking.component;
//
//import io.camunda.zeebe.client.ZeebeClient;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class CamundaController {
//
//    private final ZeebeClient zeebeClient;
//
//    // Konstruktor do wstrzykiwania ZeebeClient
//    public CamundaController(ZeebeClient zeebeClient) {
//        this.zeebeClient = zeebeClient;
//    }
//
//    @PostMapping("/start")
//    public Map<String, Object> startProcessInstance(@RequestBody Map<String, Object> variables) {
//        try {
//            var event = zeebeClient
//                    .newCreateInstanceCommand()
//                    .bpmnProcessId("reservation_process")
//                    .latestVersion()
//                    .variables(variables)
//                    .send()
//                    .join();
//
//            variables.put("processInstanceKey", event.getProcessInstanceKey());
//            return variables;
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to start process instance", e);
//        }
//    }
//}
