package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class OfficerFeedbackResponse {
    private Long bookingId;
    private String customerName;
    private String feedbackDescription;
    private Integer rating;
    private LocalDateTime feedbackDateTime;

    public OfficerFeedbackResponse() {}
    

    public OfficerFeedbackResponse(Long bookingId, String customerName, String feedbackDescription, Integer rating,
			LocalDateTime feedbackDateTime) {
		super();
		this.bookingId = bookingId;
		this.customerName = customerName;
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		this.feedbackDateTime = feedbackDateTime;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getFeedbackDescription() { return feedbackDescription; }
    public void setFeedbackDescription(String feedbackDescription) { this.feedbackDescription = feedbackDescription; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public LocalDateTime getFeedbackDateTime() { return feedbackDateTime; }
    public void setFeedbackDateTime(LocalDateTime feedbackDateTime) { this.feedbackDateTime = feedbackDateTime; }
}
