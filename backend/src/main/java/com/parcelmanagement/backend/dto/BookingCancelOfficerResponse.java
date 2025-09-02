package com.parcelmanagement.backend.dto;

public class BookingCancelOfficerResponse {
    private boolean success;
    private String message;
    private Long bookingId;
    private Double refundedAmount;

    public BookingCancelOfficerResponse() {}

    public BookingCancelOfficerResponse(boolean success, String message, Long bookingId, Double refundedAmount) {
        this.success = success;
        this.message = message;
        this.bookingId = bookingId;
        this.refundedAmount = refundedAmount;
    }
    // getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Double getRefundedAmount() { return refundedAmount; }
    public void setRefundedAmount(Double refundedAmount) { this.refundedAmount = refundedAmount; }
}