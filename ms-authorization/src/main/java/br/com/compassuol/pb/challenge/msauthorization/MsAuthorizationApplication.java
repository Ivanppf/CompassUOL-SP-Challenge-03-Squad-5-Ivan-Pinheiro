package br.com.compassuol.pb.challenge.msauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MsAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthorizationApplication.class, args);
	}

	@GetMapping
	public String hello() {
		return "hello";
	}

}
