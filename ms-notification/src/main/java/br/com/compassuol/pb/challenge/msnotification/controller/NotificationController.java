package br.com.compassuol.pb.challenge.msnotification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @GetMapping("/message")
    public String getMessage() {
        return "notifications";
    }

}
