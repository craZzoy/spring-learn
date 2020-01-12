package com.segfault.controller;

import com.segfault.entity.Customer;
import com.segfault.repository.CustomerRepository;
import com.segfault.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Writer;
import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Customer findCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }


    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return customerService.findCustomer(customer.getId());
    }

    @GetMapping("/single")
    public String testSingleService() {
        System.out.println("currendThread=" + Thread.currentThread().getName() + ",service=" + customerService);
        return "success";
    }
}
