package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.*;
import com.parcelmanagement.backend.service.AdminService;
import com.parcelmanagement.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") 
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login attempt for: " + request.getCustomerId());
        try {
            try {
                System.out.println("Trying customer login...");
                LoginResponse customerResponse = customerService.loginCustomer(request);
                System.out.println("Customer login successful");
                return ResponseEntity.ok(customerResponse);
            } catch (IllegalArgumentException e) {
                System.out.println("Customer login failed, trying admin login...");
                AdminLoginRequest adminRequest = new AdminLoginRequest(request.getCustomerId(), request.getPassword());
                AdminLoginResponse adminResponse = adminService.loginAdmin(adminRequest);
                System.out.println("Admin login successful");

                LoginResponse unifiedResponse = new LoginResponse(
                    adminResponse.getOfficerId(),
                    adminResponse.getOfficerName(),
                    adminResponse.getRole(),
                    adminResponse.getMessage()
                );

                return ResponseEntity.ok(unifiedResponse);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Login error: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("An error occurred during login"));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegistrationDTO dto) {
        try {
            RegistrationAcknowledgment response = customerService.registerCustomer(dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorResponse("Registration failed. Please try again."));
        }
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}