package br.com.compassuol.pb.challenge.msnotification.controller;

import br.com.compassuol.pb.challenge.msnotification.models.Email;
import br.com.compassuol.pb.challenge.msnotification.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationControlelr {

    private EmailService emailService;
    @GetMapping
    public String getMessage() {
        Email email = new Email("fromEmail", "fromName", "replyTo", "partyhelper.repost@gmail.com", "message subject", "message body", "contentType");
        emailService.sendEmail(email);
        return "email enviado";
    }

}
