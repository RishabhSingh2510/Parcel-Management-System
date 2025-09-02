package com.parcelmanagement.backend.entities;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    private String customerId;

    @Column(nullable = false, length = 50)
    private String customerName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String countryCode;

    @Column(nullable = false, length = 10)
    private String mobileNumber;

    @Column(nullable = false, length = 100)
    private String addressLine1;

    @Column(length = 100)
    private String addressLine2;

    @Column(nullable = false, length = 50)
    private String district;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 6)
    private String pinCode;

    @Column(nullable = false)
    private String password;

    @Column
    private String preferences;

    @Column(nullable = false, length = 20)
    private String role;

    public Customer() {
        this.role = "CUSTOMER"; // Default role
    }

    public Customer(String customerId, String customerName, String email, String countryCode, String mobileNumber,
                    String addressLine1, String addressLine2, String district, String state,
                    String country, String pinCode, String password, String preferences, String role) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.countryCode = countryCode;
        this.mobileNumber = mobileNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.password = password;
        this.preferences = preferences;
        this.role = role != null ? role : "CUSTOMER";
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}