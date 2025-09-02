package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.InvoiceResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Payment;
import com.parcelmanagement.backend.repository.BookingRepository;
import com.parcelmanagement.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public InvoiceService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public InvoiceResponse getInvoiceByBookingId(Long bookingId) {
        try {
            System.out.println("=== INVOICE PROCESSING START ===");
            System.out.println("Requested Booking ID: " + bookingId);

            if (bookingId == null) {
                System.out.println("ERROR: Booking ID is null");
                throw new RuntimeException("Booking ID required");
            }

            // First check if booking exists
            System.out.println("Checking if booking exists...");
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            if (booking == null) {
                System.out.println("ERROR: Booking not found for ID: " + bookingId);
                long totalBookings = bookingRepository.count();
                System.out.println("Total bookings in database: " + totalBookings);
                throw new RuntimeException("Booking not found for ID: " + bookingId);
            }

            System.out.println("Found booking: ID=" + booking.getBookingId() + 
                             ", Customer=" + booking.getCustomer().getCustomerId() + 
                             ", Status=" + booking.getStatus());

            // Then check for payment
            System.out.println("Looking for payment record...");
            Payment payment = paymentRepository.findByBooking_BookingId(bookingId);
            
            if (payment == null) {
                System.out.println("ERROR: No payment found for booking ID: " + bookingId);
                
                // Debug: Check all payments
                long totalPayments = paymentRepository.count();
                System.out.println("Total payments in database: " + totalPayments);
                
                // List recent payments for debugging
                System.out.println("Recent payments:");
                paymentRepository.findAll().stream()
                    .limit(5)
                    .forEach(p -> System.out.println("Payment ID: " + p.getPaymentId() + 
                                                   ", Booking ID: " + p.getBooking().getBookingId() + 
                                                   ", Status: " + p.getTransactionStatus()));
                
                throw new RuntimeException("No payment found for booking ID: " + bookingId + 
                                         ". This booking may not have been paid for yet.");
            }

            System.out.println("Found payment: ID=" + payment.getPaymentId() + 
                             ", Transaction ID=" + payment.getTransactionId() + 
                             ", Status=" + payment.getTransactionStatus());

            // Create response
            InvoiceResponse resp = new InvoiceResponse();
            resp.setBookingId(booking.getBookingId());
            resp.setPaymentId(payment.getPaymentId());
            resp.setTransactionId(payment.getTransactionId());
            resp.setInvoiceNumber(payment.getInvoiceNumber());
            resp.setReceiverName(booking.getReceiverName());
            resp.setReceiverAddress(booking.getReceiverAddress());
            resp.setReceiverPin(booking.getReceiverPin());
            resp.setReceiverMobile(booking.getReceiverMobile());
            resp.setParcelWeightInGram(booking.getParcelWeightInGram());
            resp.setParcelContentsDescription(booking.getParcelContentsDescription());
            resp.setParcelDeliveryType(booking.getParcelDeliveryType());
            resp.setParcelPackingPreference(booking.getParcelPackingPreference());
            resp.setParcelPickupTime(booking.getParcelPickupTime());
            resp.setParcelDropoffTime(booking.getParcelDropoffTime());
            resp.setParcelServiceCost(booking.getParcelServiceCost());
            resp.setParcelPaymentTime(booking.getParcelPaymentTime());

            System.out.println("=== INVOICE PROCESSING SUCCESS ===");
            System.out.println("Invoice Number: " + payment.getInvoiceNumber());
            
            return resp;

        } catch (Exception e) {
            System.out.println("=== INVOICE PROCESSING ERROR ===");
            System.out.println("Exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}