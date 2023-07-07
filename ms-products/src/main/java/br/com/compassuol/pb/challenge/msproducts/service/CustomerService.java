package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.dto.CustomerDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.Customer;
import br.com.compassuol.pb.challenge.msproducts.repository.CustomerRepository;
import br.com.compassuol.pb.challenge.msproducts.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private RabbitTemplate rabbitTemplate;

    public CustomerDTO findCustomer(int id) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public CustomerDTO addCustomer(Customer customer) {
        if(customerRepository.count() == 0)
            customer.setRoles(List.of(roleRepository.findByNameIgnoreCase("admin")));
        else
            customer.setRoles(List.of(roleRepository.findByNameIgnoreCase("operator")));
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(savedCustomer, customerDTO);
        rabbitTemplate.convertAndSend("notification", List.of(customer.getEmail(), "created"));
        return customerDTO;
    }

    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Customer oldCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        customerDTO.setId(oldCustomer.getId());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setRoles(oldCustomer.getRoles());
        customer.setPassword(oldCustomer.getPassword());
        customerRepository.save(customer);
        rabbitTemplate.convertAndSend("notification", List.of(customer.getEmail(), "updated"));
        return customerDTO;
    }

}
