package br.com.compassuol.pb.challenge.msauthorization.repository;

import br.com.compassuol.pb.challenge.msauthorization.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByEmail(String email);
}
