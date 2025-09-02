package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class PreviousBookingResponse {
    private String customerId;
    private Long bookingId;
    private LocalDateTime bookingDate;
    private String receiverName;
    private String deliveredAddress;
    private Double amount;
    private String status;
    
    // Feedback fields
    private boolean hasFeedback;
    private Integer feedbackRating;
    private String feedbackDescription;
    private LocalDateTime feedbackDateTime;

    public PreviousBookingResponse() {}
    
    public PreviousBookingResponse(String customerId, Long bookingId, LocalDateTime bookingDate, String receiverName,
			String deliveredAddress, Double amount, String status) {
		super();
		this.customerId = customerId;
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.receiverName = receiverName;
		this.deliveredAddress = deliveredAddress;
		this.amount = amount;
		this.status = status;
		this.hasFeedback = false;
	}

	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getDeliveredAddress() { return deliveredAddress; }
    public void setDeliveredAddress(String deliveredAddress) { this.deliveredAddress = deliveredAddress; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
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