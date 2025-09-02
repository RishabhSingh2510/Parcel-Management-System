package com.parcelmanagement.backend.dto;

public class TrackingStatusRequest {
    private String customerId;
    private Long bookingId;

    public TrackingStatusRequest() {}
    
    public TrackingStatusRequest(String customerId, Long bookingId) {
		super();
		this.customerId = customerId;
		this.bookingId = bookingId;
	}

	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
}
