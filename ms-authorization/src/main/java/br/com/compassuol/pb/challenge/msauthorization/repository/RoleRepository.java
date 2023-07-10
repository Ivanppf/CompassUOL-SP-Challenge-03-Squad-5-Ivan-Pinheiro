package br.com.compassuol.pb.challenge.msauthorization.repository;

import br.com.compassuol.pb.challenge.msauthorization.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
