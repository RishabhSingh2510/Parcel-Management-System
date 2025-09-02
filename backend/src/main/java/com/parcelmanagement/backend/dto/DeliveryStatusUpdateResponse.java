package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class DeliveryStatusUpdateResponse {
    private Long bookingId;
    private String fullName;
    private String address;
    private String receiverName;
    private String receiverAddress;
    private LocalDateTime dateOfBooking;
    private String status;
    private String message;

    public DeliveryStatusUpdateResponse() {}

    public DeliveryStatusUpdateResponse(Long bookingId, String fullName, String address, String receiverName,
			String receiverAddress, LocalDateTime dateOfBooking, String status, String message) {
		super();
		this.bookingId = bookingId;
		this.fullName = fullName;
		this.address = address;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.dateOfBooking = dateOfBooking;
		this.status = status;
		this.message = message;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }
    public LocalDateTime getDateOfBooking() { return dateOfBooking; }
    public void setDateOfBooking(LocalDateTime dateOfBooking) { this.dateOfBooking = dateOfBooking; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
