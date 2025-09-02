package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class BookServiceRequest {
    private String customerId;
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

    public BookServiceRequest() {}
    
    public BookServiceRequest(String customerId, String receiverName, String receiverAddress, String receiverPin,
			String receiverMobile, Integer parcelWeightInGram, String parcelContentsDescription,
			String parcelDeliveryType, String parcelPackingPreference, LocalDateTime parcelPickupTime,
			LocalDateTime parcelDropoffTime) {
		super();
		this.customerId = customerId;
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
	}

	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
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

	@Override
	public String toString() {
		return "BookServiceRequest [customerId=" + customerId + ", receiverName=" + receiverName + ", receiverAddress="
				+ receiverAddress + ", receiverPin=" + receiverPin + ", receiverMobile=" + receiverMobile
				+ ", parcelWeightInGram=" + parcelWeightInGram + ", parcelContentsDescription="
				+ parcelContentsDescription + ", parcelDeliveryType=" + parcelDeliveryType
				+ ", parcelPackingPreference=" + parcelPackingPreference + ", parcelPickupTime=" + parcelPickupTime
				+ ", parcelDropoffTime=" + parcelDropoffTime + "]";
	}
    
}
