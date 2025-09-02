package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class PickupDropUpdateRequest {
    private Long bookingId;
    private LocalDateTime parcelPickupTime;
    private LocalDateTime parcelDropoffTime;

    public PickupDropUpdateRequest() {}

    public PickupDropUpdateRequest(Long bookingId, LocalDateTime parcelPickupTime, LocalDateTime parcelDropoffTime) {
		super();
		this.bookingId = bookingId;
		this.parcelPickupTime = parcelPickupTime;
		this.parcelDropoffTime = parcelDropoffTime;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public LocalDateTime getParcelPickupTime() { return parcelPickupTime; }
    public void setParcelPickupTime(LocalDateTime parcelPickupTime) { this.parcelPickupTime = parcelPickupTime; }
    public LocalDateTime getParcelDropoffTime() { return parcelDropoffTime; }
    public void setParcelDropoffTime(LocalDateTime parcelDropoffTime) { this.parcelDropoffTime = parcelDropoffTime; }
}
