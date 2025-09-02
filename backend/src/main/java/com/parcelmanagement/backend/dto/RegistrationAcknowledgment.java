package com.parcelmanagement.backend.dto;

public class RegistrationAcknowledgment {

    private String customerId;
    private String customerName;
    private String email;
    private String message;

    public RegistrationAcknowledgment(String customerId, String customerName, String email, String message) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.message = message;
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getMessage() { return message; }
}
