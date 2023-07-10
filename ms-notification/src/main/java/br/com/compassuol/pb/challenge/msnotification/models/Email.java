package br.com.compassuol.pb.challenge.msnotification.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "email")
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String destination;
    private String subject;
    private String body;
    private String contentType;

    public Email(String fromEmail, String fromName, String replyTo, String destination, String subject, String body, String contentType) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.replyTo = replyTo;
        this.destination = destination;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }
}
