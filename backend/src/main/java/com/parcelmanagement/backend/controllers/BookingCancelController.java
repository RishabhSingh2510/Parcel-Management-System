package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.BookingCancelRequest;
import com.parcelmanagement.backend.dto.BookingCancelResponse;
import com.parcelmanagement.backend.service.BookingCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/booking")
public class BookingCancelController {

    @Autowired
    private BookingCancelService bookingCancelService;

    @PostMapping("/cancel")
    public ResponseEntity<BookingCancelResponse> cancelBooking(@RequestBody BookingCancelRequest request) {
        if (request.getBookingId() == null) {
            return ResponseEntity.badRequest().body(
                new BookingCancelResponse(false, "Booking ID must be provided", null));
        }

        BookingCancelResponse response = bookingCancelService.cancelBooking(request.getBookingId());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }
}