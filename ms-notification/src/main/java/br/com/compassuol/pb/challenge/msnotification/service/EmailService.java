package br.com.compassuol.pb.challenge.msnotification.service;

import br.com.compassuol.pb.challenge.msnotification.models.Email;
import br.com.compassuol.pb.challenge.msnotification.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender emailSender;
    private EmailRepository emailRepository;

    @RabbitListener(queues = "notification")
    public void getMessage(List<String> to) {
        Email email = new Email("fromEmail", "fromName", "replyTo", to.get(0),
                "Notification API",
                "Account " + to.get(1) + " successfully!\nThanks for using our service.", "contentType");
        sendEmail(email);
        emailRepository.save(email);
    }


    public void sendEmail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFromEmail());
            message.setTo(email.getDestination());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }

    }

}
