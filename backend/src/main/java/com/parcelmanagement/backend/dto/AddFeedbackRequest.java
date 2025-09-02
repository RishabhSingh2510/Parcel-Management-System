package com.parcelmanagement.backend.dto;

public class AddFeedbackRequest {
    private Long bookingId;
    private String description;
    private Integer rating;

    public AddFeedbackRequest() {}

    public AddFeedbackRequest(Long bookingId, String description, Integer rating) {
		super();
		this.bookingId = bookingId;
		this.description = description;
		this.rating = rating;
	}

	public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
