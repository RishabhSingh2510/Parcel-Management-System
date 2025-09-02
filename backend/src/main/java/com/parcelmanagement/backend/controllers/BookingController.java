package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.BookServiceRequest;
import com.parcelmanagement.backend.dto.BookServiceResponse;
import com.parcelmanagement.backend.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	@Autowired
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookServiceResponse> bookService(@RequestBody BookServiceRequest request) {
        BookServiceResponse response = bookingService.bookParcelService(request);
        return ResponseEntity.ok(response);
    }
}
