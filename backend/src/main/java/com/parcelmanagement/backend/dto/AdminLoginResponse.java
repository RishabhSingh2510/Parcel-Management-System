package com.parcelmanagement.backend.dto;

public class AdminLoginResponse {
    private String officerId;
    private String officerName;
    private String role;
    private String message;

    public AdminLoginResponse() {}

    public AdminLoginResponse(String officerId, String officerName, String role, String message) {
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