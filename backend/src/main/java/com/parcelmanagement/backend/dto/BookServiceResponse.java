package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class BookServiceResponse {
    private Long bookingId;
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobile;
    private String customerEmail;
    private String receiverName;
    private String receiverAddress;
    private String receiverPin;
    private String receiverMobile;
    private Integer parcelWeightInGram;
    private String parcelContentsDescription;
    private String parcelDeliveryType;
    private String parcelPackingPreference;
    private LocalDateTime parcelPickupTime;
    private LocalDateTime parcelDropoffTime;
    private Double parcelServiceCost;
    private LocalDateTime parcelPaymentTime;
    private String status;

    public BookServiceResponse() {}

    public BookServiceResponse(Long bookingId, String customerId, String customerName, String customerAddress,
			String customerMobile, String customerEmail, String receiverName, String receiverAddress,
			String receiverPin, String receiverMobile, Integer parcelWeightInGram, String parcelContentsDescription,
			String parcelDeliveryType, String parcelPackingPreference, LocalDateTime parcelPickupTime,
			LocalDateTime parcelDropoffTime, Double parcelServiceCost, LocalDateTime parcelPaymentTime, String status) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.receiverPin = receiverPin;
		this.receiverMobile = receiverMobile;
		this.parcelWeightInGram = parcelWeightInGram;
		this.parcelContentsDescription = parcelContentsDescription;
		this.parcelDeliveryType = parcelDeliveryType;
		this.parcelPackingPreference = parcelPackingPreference;
		this.parcelPickupTime = parcelPickupTime;
		this.parcelDropoffTime = parcelDropoffTime;
		this.parcelServiceCost = parcelServiceCost;
		this.parcelPaymentTime = parcelPaymentTime;
		this.status = status;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    public String getCustomerMobile() { return customerMobile; }
    public void setCustomerMobile(String customerMobile) { this.customerMobile = customerMobile; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }
    public String getReceiverPin() { return receiverPin; }
    public void setReceiverPin(String receiverPin) { this.receiverPin = receiverPin; }
    public String getReceiverMobile() { return receiverMobile; }
    public void setReceiverMobile(String receiverMobile) { this.receiverMobile = receiverMobile; }
    public Integer getParcelWeightInGram() { return parcelWeightInGram; }
    public void setParcelWeightInGram(Integer parcelWeightInGram) { this.parcelWeightInGram = parcelWeightInGram; }
    public String getParcelContentsDescription() { return parcelContentsDescription; }
    public void setParcelContentsDescription(String parcelContentsDescription) { this.parcelContentsDescription = parcelContentsDescription; }
    public String getParcelDeliveryType() { return parcelDeliveryType; }
    public void setParcelDeliveryType(String parcelDeliveryType) { this.parcelDeliveryType = parcelDeliveryType; }
    public String getParcelPackingPreference() { return parcelPackingPreference; }
    public void setParcelPackingPreference(String parcelPackingPreference) { this.parcelPackingPreference = parcelPackingPreference; }
    public LocalDateTime getParcelPickupTime() { return parcelPickupTime; }
    public void setParcelPickupTime(LocalDateTime parcelPickupTime) { this.parcelPickupTime = parcelPickupTime; }
    public LocalDateTime getParcelDropoffTime() { return parcelDropoffTime; }
    public void setParcelDropoffTime(LocalDateTime parcelDropoffTime) { this.parcelDropoffTime = parcelDropoffTime; }
    public Double getParcelServiceCost() { return parcelServiceCost; }
    public void setParcelServiceCost(Double parcelServiceCost) { this.parcelServiceCost = parcelServiceCost; }
    public LocalDateTime getParcelPaymentTime() { return parcelPaymentTime; }
    public void setParcelPaymentTime(LocalDateTime parcelPaymentTime) { this.parcelPaymentTime = parcelPaymentTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

	@Override
	public String toString() {
		return "BookServiceResponse [bookingId=" + bookingId + ", customerId=" + customerId + ", customerName="
				+ customerName + ", customerAddress=" + customerAddress + ", customerMobile=" + customerMobile
				+ ", customerEmail=" + customerEmail + ", receiverName=" + receiverName + ", receiverAddress="
				+ receiverAddress + ", receiverPin=" + receiverPin + ", receiverMobile=" + receiverMobile
				+ ", parcelWeightInGram=" + parcelWeightInGram + ", parcelContentsDescription="
				+ parcelContentsDescription + ", parcelDeliveryType=" + parcelDeliveryType
				+ ", parcelPackingPreference=" + parcelPackingPreference + ", parcelPickupTime=" + parcelPickupTime
				+ ", parcelDropoffTime=" + parcelDropoffTime + ", parcelServiceCost=" + parcelServiceCost
				+ ", parcelPaymentTime=" + parcelPaymentTime + ", status=" + status + "]";
	}
}
