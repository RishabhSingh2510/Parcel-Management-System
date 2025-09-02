package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.BookServiceOfficerRequest;
import com.parcelmanagement.backend.dto.BookServiceOfficerResponse;
import com.parcelmanagement.backend.dto.ViewAllBookingResponse;
import com.parcelmanagement.backend.service.BookingOfficerService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/officer/bookings")
public class BookingOfficerController {
	@Autowired
    private BookingOfficerService bookingOfficerService;
    
    public BookingOfficerController(BookingOfficerService bookingOfficerService) {
        this.bookingOfficerService = bookingOfficerService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookServiceOfficerResponse> bookAsOfficer(@RequestBody BookServiceOfficerRequest request) {
        BookServiceOfficerResponse response = bookingOfficerService.bookServiceAsOfficer(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<ViewAllBookingResponse>> getAllBookings(
            @RequestParam(value = "customerId", required = false) String customerId,
            @RequestParam(value = "bookingId", required = false) Long bookingId,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;

        try {
            if (from != null && !from.isEmpty()) fromDate = LocalDateTime.parse(from);
            if (to != null && !to.isEmpty()) toDate = LocalDateTime.parse(to);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        Page<ViewAllBookingResponse> bookings = bookingOfficerService.getAllBookings(
                customerId, bookingId, fromDate, toDate, status, page, size);

        return ResponseEntity.ok(bookings);
    }
    
    @PostMapping("/cancel")
    public ResponseEntity<?> cancelBookingByOfficer(
            @RequestParam(value = "bookingId", required = false) Long bookingId,
            @RequestParam(value = "customerId", required = false) String customerId,
            @RequestParam(value = "customerName", required = false) String customerName) {

        var response = bookingOfficerService.cancelBookingByOfficer(bookingId, customerId, customerName);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }
}
