package com.parcelmanagement.backend.repository;

import com.parcelmanagement.backend.entities.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByCustomerId(String customerId);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);
    
}
