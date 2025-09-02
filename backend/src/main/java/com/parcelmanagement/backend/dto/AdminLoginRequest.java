package com.parcelmanagement.backend.dto;

public class AdminLoginRequest {
    private String officerId;
    private String password;

    // Default constructor (required for JSON deserialization)
    public AdminLoginRequest() {}

    // Parameterized constructor (used in your controller)
    public AdminLoginRequest(String officerId, String password) {
        this.officerId = officerId;
        this.password = password;
    }

    // Getters and Setters
    public String getOfficerId() { 
        return officerId; 
    }
    
    public void setOfficerId(String officerId) { 
        this.officerId = officerId; 
    }

    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }
}