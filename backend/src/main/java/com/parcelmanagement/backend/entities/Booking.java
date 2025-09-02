package com.parcelmanagement.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @Column(nullable = false, length = 50)
    private String receiverName;

    @Column(nullable = false, length = 100)
    private String receiverAddress;

    @Column(nullable = false, length = 6)
    private String receiverPin;

    @Column(nullable = false, length = 15)
    private String receiverMobile;

    @Column(nullable = false)
    private Integer parcelWeightInGram;

    @Column(nullable = false, length = 100)
    private String parcelContentsDescription;

    @Column(nullable = false, length = 20)
    private String parcelDeliveryType;

    @Column(nullable = false, length = 20)
    private String parcelPackingPreference;

    @Column(nullable = false)
    private LocalDateTime parcelPickupTime;

    @Column(nullable = false)
    private LocalDateTime parcelDropoffTime;

    @Column(nullable = false)
    private Double parcelServiceCost;

    @Column(nullable = false)
    private LocalDateTime parcelPaymentTime;

    @Column(nullable = false, length = 20)
    private String status;

    public Booking() {}

    
    
    public Booking(Long bookingId, Customer customer, String receiverName, String receiverAddress, String receiverPin,
			String receiverMobile, Integer parcelWeightInGram, String parcelContentsDescription,
			String parcelDeliveryType, String parcelPackingPreference, LocalDateTime parcelPickupTime,
			LocalDateTime parcelDropoffTime, Double parcelServiceCost, LocalDateTime parcelPaymentTime, String status) {
		super();
		this.bookingId = bookingId;
		this.customer = customer;
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
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
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
		return "Booking [bookingId=" + bookingId + ", customer=" + customer + ", receiverName=" + receiverName
				+ ", receiverAddress=" + receiverAddress + ", receiverPin=" + receiverPin + ", receiverMobile="
				+ receiverMobile + ", parcelWeightInGram=" + parcelWeightInGram + ", parcelContentsDescription="
				+ parcelContentsDescription + ", parcelDeliveryType=" + parcelDeliveryType
				+ ", parcelPackingPreference=" + parcelPackingPreference + ", parcelPickupTime=" + parcelPickupTime
				+ ", parcelDropoffTime=" + parcelDropoffTime + ", parcelServiceCost=" + parcelServiceCost
				+ ", parcelPaymentTime=" + parcelPaymentTime + ", status=" + status + "]";
	}
    
    
}
