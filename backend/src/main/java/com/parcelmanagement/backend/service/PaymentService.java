package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.PayBillRequest;
import com.parcelmanagement.backend.dto.PayBillResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Payment;
import com.parcelmanagement.backend.repository.BookingRepository;
import com.parcelmanagement.backend.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public PayBillResponse payBill(PayBillRequest request) {
        PayBillResponse response = new PayBillResponse();

        try {
            // Enhanced logging
            System.out.println("=== PAYMENT PROCESSING START ===");
            System.out.println("Request: " + request.getBookingId());
            System.out.println("Card Number Length: " + (request.getCardNumber() != null ? request.getCardNumber().length() : "null"));
            System.out.println("Card Holder Name: " + request.getCardHolderName());
            System.out.println("Expiry Date: " + request.getExpiryDate());
            System.out.println("CVV Length: " + (request.getCvv() != null ? request.getCvv().length() : "null"));
            System.out.println("Transaction Type: " + request.getTransactionType());

            // Validation with detailed error messages
            if (request.getBookingId() == null) {
                System.out.println("ERROR: Booking ID is null");
                response.setMessage("Booking ID is required.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (request.getCardNumber() == null) {
                System.out.println("ERROR: Card number is null");
                response.setMessage("Card number is required.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (!request.getCardNumber().matches("\\d{16}")) {
                System.out.println("ERROR: Card number format invalid: " + request.getCardNumber());
                response.setMessage("Card number must be 16 digits.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (request.getCardHolderName() == null || request.getCardHolderName().trim().isEmpty()) {
                System.out.println("ERROR: Card holder name is empty");
                response.setMessage("Cardholder name is required.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (request.getExpiryDate() == null || !request.getExpiryDate().matches("(0[1-9]|1[0-2])/\\d{2}")) {
                System.out.println("ERROR: Expiry date format invalid: " + request.getExpiryDate());
                response.setMessage("Expiry date must be in MM/YY format.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (request.getCvv() == null || !(request.getCvv().matches("\\d{3}") || request.getCvv().matches("\\d{4}"))) {
                System.out.println("ERROR: CVV format invalid: " + request.getCvv());
                response.setMessage("CVV must be 3 or 4 digits.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            if (request.getTransactionType() == null || (!request.getTransactionType().equalsIgnoreCase("CREDIT")
                    && !request.getTransactionType().equalsIgnoreCase("DEBIT"))) {
                System.out.println("ERROR: Transaction type invalid: " + request.getTransactionType());
                response.setMessage("Transaction type must be 'Credit' or 'Debit'.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            // Check if booking exists with detailed logging
            System.out.println("Looking for booking with ID: " + request.getBookingId());
            Booking booking = bookingRepository.findById(request.getBookingId()).orElse(null);
            
            if (booking == null) {
                System.out.println("ERROR: Booking not found for ID: " + request.getBookingId());
                // Try to find any bookings to see if database is working
                long totalBookings = bookingRepository.count();
                System.out.println("Total bookings in database: " + totalBookings);
                response.setMessage("Booking not found for ID: " + request.getBookingId());
                response.setTransactionStatus("FAILED");
                return response;
            }

            System.out.println("Found booking: ID=" + booking.getBookingId() + 
                             ", Customer=" + booking.getCustomer().getCustomerId() + 
                             ", Status=" + booking.getStatus() +
                             ", Amount=" + booking.getParcelServiceCost());

            // Check if already paid
            Payment alreadyPaid = paymentRepository.findByBooking_BookingId(booking.getBookingId());
            if (alreadyPaid != null) {
                System.out.println("ERROR: Payment already exists for booking ID: " + booking.getBookingId());
                response.setMessage("Payment already completed for this booking.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            System.out.println("No existing payment found, proceeding with payment creation");

            // Validate expiry date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth exp;
            try {
                exp = YearMonth.parse(request.getExpiryDate(), formatter);
            } catch (Exception e) {
                System.out.println("ERROR: Expiry date parsing failed: " + e.getMessage());
                response.setMessage("Expiry date is invalid.");
                response.setTransactionStatus("FAILED");
                return response;
            }
            
            YearMonth now = YearMonth.now();
            if (exp.isBefore(now)) {
                System.out.println("ERROR: Card is expired. Exp: " + exp + ", Now: " + now);
                response.setMessage("Card is expired.");
                response.setTransactionStatus("FAILED");
                return response;
            }

            // Process payment
            LocalDateTime txnDate = LocalDateTime.now();
            String txnId = "TXN" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12).toUpperCase();
            String invoiceNumber = "INV" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10).toUpperCase();

            System.out.println("Generating payment record:");
            System.out.println("Transaction ID: " + txnId);
            System.out.println("Invoice Number: " + invoiceNumber);
            System.out.println("Transaction Date: " + txnDate);
            System.out.println("Transaction Amount: " + booking.getParcelServiceCost());

            // Create payment record
            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setCardNumber(maskCardNumber(request.getCardNumber()));
            payment.setCardHolderName(request.getCardHolderName());
            payment.setExpiryDate(request.getExpiryDate());
            payment.setCvv("***"); // Don't store actual CVV
            payment.setTransactionId(txnId);
            payment.setInvoiceNumber(invoiceNumber);
            payment.setTransactionType(request.getTransactionType().toUpperCase());
            payment.setTransactionAmount(booking.getParcelServiceCost());
            payment.setTransactionDate(txnDate);
            payment.setTransactionStatus("SUCCESS");

            System.out.println("Saving payment record...");
            payment = paymentRepository.save(payment);
            System.out.println("Payment saved with ID: " + payment.getPaymentId());

            // Update booking status to BOOKED
            System.out.println("Updating booking status from " + booking.getStatus() + " to BOOKED");
            booking.setStatus("BOOKED");
            booking = bookingRepository.save(booking);
            System.out.println("Booking status updated successfully");

            // Prepare response
            response.setPaymentId(payment.getPaymentId());
            response.setTransactionId(payment.getTransactionId());
            response.setTransactionDate(payment.getTransactionDate());
            response.setTransactionType(payment.getTransactionType());
            response.setBookingId(booking.getBookingId());
            response.setTransactionAmount(payment.getTransactionAmount());
            response.setTransactionStatus(payment.getTransactionStatus());
            response.setMessage("Payment Successful!");

            System.out.println("=== PAYMENT PROCESSING SUCCESS ===");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Transaction ID: " + payment.getTransactionId());

            return response;

        } catch (Exception e) {
            System.out.println("=== PAYMENT PROCESSING ERROR ===");
            System.out.println("Exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            
            response.setMessage("Payment processing failed: " + e.getMessage());
            response.setTransactionStatus("FAILED");
            return response;
        }
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 16) {
            return cardNumber;
        }
        return "**** **** **** " + cardNumber.substring(12);
    }
}