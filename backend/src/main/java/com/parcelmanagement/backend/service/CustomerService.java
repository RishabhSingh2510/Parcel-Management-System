package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.controllers.CustomerOfficerController.CustomerValidationResponse;
import com.parcelmanagement.backend.dto.CustomerDetailsResponse;
import com.parcelmanagement.backend.dto.CustomerRegistrationDTO;
import com.parcelmanagement.backend.dto.LoginRequest;
import com.parcelmanagement.backend.dto.LoginResponse;
import com.parcelmanagement.backend.dto.RegistrationAcknowledgment;
import com.parcelmanagement.backend.entities.Customer;
import com.parcelmanagement.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationAcknowledgment registerCustomer(CustomerRegistrationDTO dto) {
        if (dto.getCustomerName() == null || dto.getCustomerName().isBlank() || 
            !dto.getCustomerName().matches("^[a-zA-Z\\s]+$") || dto.getCustomerName().length() > 50) {
            throw new IllegalArgumentException("Customer name must contain only letters and spaces, and be max 50 characters.");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank() || 
            !dto.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Please provide a valid email address.");
        }

        if (dto.getCountryCode() == null || dto.getCountryCode().isBlank()) {
            throw new IllegalArgumentException("Country code is required.");
        }

        if (dto.getMobileNumber() == null || dto.getMobileNumber().isBlank() || 
            !dto.getMobileNumber().matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
        }

        if (dto.getPinCode() == null || dto.getPinCode().isBlank() || 
            !dto.getPinCode().matches("^\\d{6}$")) {
            throw new IllegalArgumentException("Pin code must be exactly 6 digits.");
        }

        if (dto.getPassword() == null || dto.getPassword().isBlank() ||
            !dto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,30}$")) {
            throw new IllegalArgumentException("Password must be 6-30 characters with at least one uppercase, lowercase, and number.");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        if (dto.getAddressLine1() == null || dto.getAddressLine1().isBlank() ||
            dto.getDistrict() == null || dto.getDistrict().isBlank() ||
            dto.getState() == null || dto.getState().isBlank() ||
            dto.getCountry() == null || dto.getCountry().isBlank()) {
            throw new IllegalArgumentException("Address Line 1, District, State, and Country are required.");
        }

        String customerId = generateCustomerId(dto.getCustomerName(), dto.getMobileNumber());

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName(dto.getCustomerName());
        customer.setEmail(dto.getEmail());
        customer.setCountryCode(dto.getCountryCode());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setAddressLine1(dto.getAddressLine1());
        customer.setAddressLine2(dto.getAddressLine2());
        customer.setDistrict(dto.getDistrict());
        customer.setState(dto.getState());
        customer.setCountry(dto.getCountry());
        customer.setPinCode(dto.getPinCode());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setPreferences(dto.getPreferences());
        customer.setRole("CUSTOMER"); // Always set as CUSTOMER for registration

        Customer saved = customerRepository.save(customer);

        return new RegistrationAcknowledgment(
                saved.getCustomerId(),
                saved.getCustomerName(),
                saved.getEmail(),
                "Customer Registration successful."
        );
    }
    
    public LoginResponse loginCustomer(LoginRequest request) {
        String customerId = request.getCustomerId();
        String password = request.getPassword();

        if (customerId == null || customerId.isBlank() || customerId.length() < 5 || customerId.length() > 20) {
            throw new IllegalArgumentException("Invalid Customer ID or Password");
        }

        if (password == null || password.isBlank() || password.length() > 30) {
            throw new IllegalArgumentException("Invalid Customer ID or Password");
        }

        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Invalid Customer ID or Password");
        }

        Customer customer = optionalCustomer.get();

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new IllegalArgumentException("Invalid Customer ID or Password");
        }

        return new LoginResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getRole() != null ? customer.getRole() : "CUSTOMER", // Include role
                "Login successful.",
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getAddressLine1(),
                customer.getAddressLine2(),
                customer.getDistrict(),
                customer.getState(),
                customer.getCountry(),
                customer.getPinCode()
        );
    }

    private String generateCustomerId(String customerName, String mobileNumber) {
        String namePrefix = customerName.replaceAll("\\s+", "").substring(0, Math.min(3, customerName.replaceAll("\\s+", "").length())).toUpperCase();
        String mobileSuffix = mobileNumber.substring(mobileNumber.length() - 3);
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(7);
        return namePrefix + mobileSuffix + timestamp;
    }

    public Customer findByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElse(null);
    }
    
    public CustomerDetailsResponse getCustomerDetailsForOfficer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new RuntimeException("Customer ID is required");
        }

        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Customer customer = optionalCustomer.get();

        return new CustomerDetailsResponse(
            customer.getCustomerId(),
            customer.getCustomerName(),
            customer.getEmail(),
            customer.getMobileNumber(),
            customer.getAddressLine1(),
            customer.getAddressLine2(),
            customer.getDistrict(),
            customer.getState(),
            customer.getCountry(),
            customer.getPinCode()
        );
    }

    public CustomerValidationResponse validateCustomerExists(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return new CustomerValidationResponse(false, "Customer ID is required");
        }

        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        
        if (optionalCustomer.isEmpty()) {
            return new CustomerValidationResponse(false, "Customer not found with ID: " + customerId);
        }

        return new CustomerValidationResponse(true, "Customer found: " + optionalCustomer.get().getCustomerName());
    }
    
}