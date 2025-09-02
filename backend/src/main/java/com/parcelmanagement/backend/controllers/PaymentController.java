package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.PayBillRequest;
import com.parcelmanagement.backend.dto.PayBillResponse;
import com.parcelmanagement.backend.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<PayBillResponse> payBill(@RequestBody PayBillRequest request) {
        try {
            PayBillResponse response = paymentService.payBill(request);
            if ("SUCCESS".equals(response.getTransactionStatus())) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            PayBillResponse errorResponse = new PayBillResponse();
            errorResponse.setTransactionStatus("FAILED");
            errorResponse.setMessage("Payment processing failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}