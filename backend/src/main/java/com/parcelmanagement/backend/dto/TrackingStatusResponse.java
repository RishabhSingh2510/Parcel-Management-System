package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class TrackingStatusResponse {
    private Long bookingId;
    private String receiverName;
    private String receiverAddress;
    private LocalDateTime dateOfBooking;
    private String status;
    
    // Feedback fields
    private boolean hasFeedback;
    private Integer feedbackRating;
    private String feedbackDescription;
    private LocalDateTime feedbackDateTime;

    public TrackingStatusResponse() {}
    
    public TrackingStatusResponse(Long bookingId, String receiverName, String receiverAddress,
			LocalDateTime dateOfBooking, String status) {
		super();
		this.bookingId = bookingId;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.dateOfBooking = dateOfBooking;
		this.status = status;
		this.hasFeedback = false;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }
    public LocalDateTime getDateOfBooking() { return dateOfBooking; }
    public void setDateOfBooking(LocalDateTime dateOfBooking) { this.dateOfBooking = dateOfBooking; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Feedback getters and setters
    public boolean isHasFeedback() { return hasFeedback; }
    public void setHasFeedback(boolean hasFeedback) { this.hasFeedback = hasFeedback; }
    public Integer getFeedbackRating() { return feedbackRating; }
    public void setFeedbackRating(Integer feedbackRating) { this.feedbackRating = feedbackRating; }
    public String getFeedbackDescription() { return feedbackDescription; }
    public void setFeedbackDescription(String feedbackDescription) { this.feedbackDescription = feedbackDescription; }
    public LocalDateTime getFeedbackDateTime() { return feedbackDateTime; }
    public void setFeedbackDateTime(LocalDateTime feedbackDateTime) { this.feedbackDateTime = feedbackDateTime; }
}