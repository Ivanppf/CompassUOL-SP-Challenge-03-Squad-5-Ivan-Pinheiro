package br.com.compassuol.pb.challenge.msauthorization.controller;

import br.com.compassuol.pb.challenge.msauthorization.entity.Role;
import br.com.compassuol.pb.challenge.msauthorization.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
@AllArgsConstructor
public class RoleController {

    private RoleRepository roleRepository;

    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.CREATED);
    }

}
