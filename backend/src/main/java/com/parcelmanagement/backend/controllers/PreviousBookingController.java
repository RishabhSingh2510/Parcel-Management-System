package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.PreviousBookingResponse;
import com.parcelmanagement.backend.service.PreviousBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings/previous")
public class PreviousBookingController {
    @Autowired
    private PreviousBookingService previousBookingService;

    @GetMapping
    public ResponseEntity<?> getPreviousBookings(
            @RequestParam("customerId") String customerId,
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
            if (from != null && !from.isEmpty()) {
                fromDate = LocalDateTime.parse(from);
            }
            if (to != null && !to.isEmpty()) {
                toDate = LocalDateTime.parse(to);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format for 'from' or 'to'. Expected format: YYYY-MM-DDTHH:mm:ss");
        }

        Page<PreviousBookingResponse> bookings = previousBookingService.getPreviousBookings(
                customerId, bookingId, fromDate, toDate, status, page, size
        );
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/download")
    public ResponseEntity<List<PreviousBookingResponse>> downloadBookings(
            @RequestParam("customerId") String customerId) {
        List<PreviousBookingResponse> bookings = previousBookingService.getAllBookingsForDownload(customerId);
        return ResponseEntity.ok(bookings);
    }
}
