package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.CustomerDetailsResponse;
import com.parcelmanagement.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/officer/customers")
public class CustomerOfficerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsResponse> getCustomerDetails(@PathVariable String customerId) {
        try {
            CustomerDetailsResponse response = customerService.getCustomerDetailsForOfficer(customerId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/validate/{customerId}")
    public ResponseEntity<CustomerValidationResponse> validateCustomer(@PathVariable String customerId) {
        CustomerValidationResponse response = customerService.validateCustomerExists(customerId);
        return ResponseEntity.ok(response);
    }

    public static class CustomerValidationResponse {
        private boolean exists;
        private String message;

        public CustomerValidationResponse() {}

        public CustomerValidationResponse(boolean exists, String message) {
            this.exists = exists;
            this.message = message;
        }

        public boolean isExists() {
            return exists;
        }

        public void setExists(boolean exists) {
            this.exists = exists;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}