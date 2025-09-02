package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.AdminLoginRequest;
import com.parcelmanagement.backend.dto.AdminLoginResponse;
import com.parcelmanagement.backend.entities.Admin;
import com.parcelmanagement.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
        try {
            AdminLoginResponse response = adminService.loginAdmin(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorResponse("An error occurred during login"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        try {
            Admin createdAdmin = adminService.createAdmin(admin);
            return ResponseEntity.ok(new AdminCreationResponse(
                createdAdmin.getOfficerId(),
                createdAdmin.getOfficerName(),
                createdAdmin.getRole(),
                "Admin created successfully"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Failed to create admin: " + e.getMessage()));
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

    public static class AdminCreationResponse {
        private String officerId;
        private String officerName;
        private String role;
        private String message;

        public AdminCreationResponse(String officerId, String officerName, String role, String message) {
            this.officerId = officerId;
            this.officerName = officerName;
            this.role = role;
            this.message = message;
        }

        public String getOfficerId() { return officerId; }
        public void setOfficerId(String officerId) { this.officerId = officerId; }

        public String getOfficerName() { return officerName; }
        public void setOfficerName(String officerName) { this.officerName = officerName; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}