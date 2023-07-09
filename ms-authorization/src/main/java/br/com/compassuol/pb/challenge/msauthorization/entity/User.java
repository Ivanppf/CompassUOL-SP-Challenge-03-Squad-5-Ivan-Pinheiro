package br.com.compassuol.pb.challenge.msauthorization.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
//@Entity
//@Table(name = "customer")
public class User {

//    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany
    private Role roles;

}
