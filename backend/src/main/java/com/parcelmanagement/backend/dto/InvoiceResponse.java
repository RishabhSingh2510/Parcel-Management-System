package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class InvoiceResponse {
    private Long bookingId;
    private Long paymentId;
    private String transactionId;
    private String invoiceNumber;
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

    public InvoiceResponse() {}
    
    public InvoiceResponse(Long bookingId, Long paymentId, String transactionId, String invoiceNumber,
			String receiverName, String receiverAddress, String receiverPin, String receiverMobile,
			Integer parcelWeightInGram, String parcelContentsDescription, String parcelDeliveryType,
			String parcelPackingPreference, LocalDateTime parcelPickupTime, LocalDateTime parcelDropoffTime,
			Double parcelServiceCost, LocalDateTime parcelPaymentTime) {
		super();
		this.bookingId = bookingId;
		this.paymentId = paymentId;
		this.transactionId = transactionId;
		this.invoiceNumber = invoiceNumber;
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
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
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
}
