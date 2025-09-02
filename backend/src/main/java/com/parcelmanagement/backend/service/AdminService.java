package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.AdminLoginRequest;
import com.parcelmanagement.backend.dto.AdminLoginResponse;
import com.parcelmanagement.backend.entities.Admin;
import com.parcelmanagement.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminLoginResponse loginAdmin(AdminLoginRequest request) {
        String officerId = request.getOfficerId();
        String password = request.getPassword();

        if (officerId == null || officerId.isBlank() || officerId.length() < 5 || officerId.length() > 20) {
            throw new IllegalArgumentException("Invalid Officer ID or Password");
        }

        if (password == null || password.isBlank() || password.length() > 30) {
            throw new IllegalArgumentException("Invalid Officer ID or Password");
        }

        Optional<Admin> optionalAdmin = adminRepository.findByOfficerId(officerId);

        if (optionalAdmin.isEmpty()) {
            throw new IllegalArgumentException("Invalid Officer ID or Password");
        }

        Admin admin = optionalAdmin.get();

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new IllegalArgumentException("Invalid Officer ID or Password");
        }

        return new AdminLoginResponse(
                admin.getOfficerId(),
                admin.getOfficerName(),
                admin.getRole(),
                "Login successful"
        );
    }

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}