package com.parcelmanagement.backend.dto;

public class CustomerRegistrationDTO {

    private String customerName;
    private String email;
    private String countryCode;
    private String mobileNumber;

    private String addressLine1;
    private String addressLine2;
    private String district;
    private String state;
    private String country;
    private String pinCode;

    private String password;
    private String confirmPassword;
    private String preferences;

    public CustomerRegistrationDTO() {}

    public CustomerRegistrationDTO(String customerName, String email, String countryCode, String mobileNumber,
                                   String addressLine1, String addressLine2, String district, String state,
                                   String country, String pinCode, String password, String confirmPassword,
                                   String preferences) {
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
        this.confirmPassword = confirmPassword;
        this.preferences = preferences;
    }

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

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}
