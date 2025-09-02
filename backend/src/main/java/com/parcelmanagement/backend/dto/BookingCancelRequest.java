package com.parcelmanagement.backend.dto;

public class BookingCancelRequest {
    private Long bookingId;

    public BookingCancelRequest() {}

    public BookingCancelRequest(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
}
