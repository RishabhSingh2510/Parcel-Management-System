package com.parcelmanagement.backend.dto;

public class BookingCancelResponse {
    private boolean success;
    private String message;
    private Long bookingId;

    public BookingCancelResponse() {}

    public BookingCancelResponse(boolean success, String message, Long bookingId) {
        this.success = success;
        this.message = message;
        this.bookingId = bookingId;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
}
