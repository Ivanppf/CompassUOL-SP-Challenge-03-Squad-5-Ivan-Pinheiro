package br.com.compassuol.pb.challenge.msproducts.auxAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MsConfig {

    @Bean
    public PasswordEncoder passwordEncoder()  {
        return new BCryptPasswordEncoder();
    }

}
