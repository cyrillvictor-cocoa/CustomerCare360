package org.example.customercare360.Repository;

import java.util.Optional;

import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUser(User user);
}