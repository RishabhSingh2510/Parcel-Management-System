package com.parcelmanagement.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    private String officerId;

    @Column(nullable = false, length = 50)
    private String officerName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String role;

    public Admin() {}

    public Admin(String officerId, String officerName, String password, String role) {
        this.officerId = officerId;
        this.officerName = officerName;
        this.password = password;
        this.role = role;
    }

    public String getOfficerId() { return officerId; }
    public void setOfficerId(String officerId) { this.officerId = officerId; }

    public String getOfficerName() { return officerName; }
    public void setOfficerName(String officerName) { this.officerName = officerName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}