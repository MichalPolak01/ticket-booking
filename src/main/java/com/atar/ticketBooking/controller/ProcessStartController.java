package com.atar.ticketBooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessStartController {

    @GetMapping(value = "/run")
    public String runProcess() {
        return "start-process";
    }
}
