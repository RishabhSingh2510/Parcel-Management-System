package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.InvoiceResponse;
import com.parcelmanagement.backend.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
	@Autowired
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<InvoiceResponse> getInvoice(@PathVariable Long bookingId) {
        InvoiceResponse response = invoiceService.getInvoiceByBookingId(bookingId);
        return ResponseEntity.ok(response);
    }
}
