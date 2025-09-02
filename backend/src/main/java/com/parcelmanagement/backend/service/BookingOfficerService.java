package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.BookServiceOfficerRequest;
import com.parcelmanagement.backend.dto.BookServiceOfficerResponse;
import com.parcelmanagement.backend.dto.BookingCancelOfficerResponse;
import com.parcelmanagement.backend.dto.ViewAllBookingResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Customer;
import com.parcelmanagement.backend.repository.BookingRepository;
import com.parcelmanagement.backend.repository.CustomerRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingOfficerService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public BookingOfficerService(BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

 // Add this enhanced method to your existing BookingOfficerService.java

    @Transactional
    public BookServiceOfficerResponse bookServiceAsOfficer(BookServiceOfficerRequest req) {
        try {
            System.out.println("=== OFFICER BOOKING START ===");
            System.out.println("Customer ID: " + req.getCustomerId());
            System.out.println("Receiver Name: " + req.getReceiverName());
            System.out.println("Parcel Weight: " + req.getParcelWeightInGram());
            System.out.println("Delivery Type: " + req.getParcelDeliveryType());

            // All existing validations (unchanged)
            if (req.getCustomerId() == null || req.getCustomerId().trim().isEmpty())
                throw new RuntimeException("CustomerId required");
            if (req.getReceiverName() == null || req.getReceiverName().trim().isEmpty())
                throw new RuntimeException("Receiver name required");
            if (req.getReceiverAddress() == null || req.getReceiverAddress().trim().isEmpty())
                throw new RuntimeException("Receiver address required");
            if (req.getReceiverPin() == null || !req.getReceiverPin().matches("\\d{6}"))
                throw new RuntimeException("Receiver pin must be 6 digits");
            if (req.getReceiverMobile() == null || !req.getReceiverMobile().matches("\\d{10,15}"))
                throw new RuntimeException("Receiver mobile invalid");
            if (req.getParcelWeightInGram() == null || req.getParcelWeightInGram() < 1)
                throw new RuntimeException("Weight must be at least 1g");
            if (req.getParcelContentsDescription() == null || req.getParcelContentsDescription().trim().isEmpty())
                throw new RuntimeException("Contents required");
            if (req.getParcelDeliveryType() == null || (!req.getParcelDeliveryType().equalsIgnoreCase("STANDARD")
                    && !req.getParcelDeliveryType().equalsIgnoreCase("EXPRESS")
                    && !req.getParcelDeliveryType().equalsIgnoreCase("SAMEDAY")))
                throw new RuntimeException("DeliveryType invalid");
            if (req.getParcelPackingPreference() == null || (!req.getParcelPackingPreference().equalsIgnoreCase("BASIC")
                    && !req.getParcelPackingPreference().equalsIgnoreCase("PREMIUM")))
                throw new RuntimeException("PackingPreference invalid");
            if (req.getParcelPickupTime() == null)
                throw new RuntimeException("PickupTime required");
            if (req.getParcelDropoffTime() == null)
                throw new RuntimeException("DropoffTime required");

            // Find customer
            System.out.println("Looking for customer: " + req.getCustomerId());
            Customer customer = customerRepository.findByCustomerId(req.getCustomerId()).orElse(null);
            if (customer == null) {
                System.out.println("ERROR: Customer not found: " + req.getCustomerId());
                throw new RuntimeException("Customer not found: " + req.getCustomerId());
            }
            
            System.out.println("Found customer: " + customer.getCustomerName());

            // Calculate cost (existing logic)
            double baseRate = 50.0;
            double weightCharge = req.getParcelWeightInGram() * 0.02;
            double deliveryCharge = req.getParcelDeliveryType().equalsIgnoreCase("SAMEDAY") ? 150 :
                                   req.getParcelDeliveryType().equalsIgnoreCase("EXPRESS") ? 80 : 30;
            double packingCharge = req.getParcelPackingPreference().equalsIgnoreCase("PREMIUM") ? 30 : 10;
            double adminFee = 50;
            double taxRate = 0.05;
            double serviceCost = (baseRate + weightCharge + deliveryCharge + packingCharge + adminFee) * (1 + taxRate);
            serviceCost = Math.round(serviceCost * 100.0) / 100.0;

            System.out.println("Calculated cost: " + serviceCost);

            // Create booking
            LocalDateTime now = LocalDateTime.now();
            Booking booking = new Booking();
            booking.setCustomer(customer);
            booking.setReceiverName(req.getReceiverName());
            booking.setReceiverAddress(req.getReceiverAddress());
            booking.setReceiverPin(req.getReceiverPin());
            booking.setReceiverMobile(req.getReceiverMobile());
            booking.setParcelWeightInGram(req.getParcelWeightInGram());
            booking.setParcelContentsDescription(req.getParcelContentsDescription());
            booking.setParcelDeliveryType(req.getParcelDeliveryType().toUpperCase());
            booking.setParcelPackingPreference(req.getParcelPackingPreference().toUpperCase());
            booking.setParcelPickupTime(req.getParcelPickupTime());
            booking.setParcelDropoffTime(req.getParcelDropoffTime());
            booking.setParcelServiceCost(serviceCost);
            booking.setParcelPaymentTime(now);
            booking.setStatus("ASSIGNED"); // Officer booking starts as ASSIGNED

            System.out.println("Saving booking...");
            booking = bookingRepository.save(booking);
            
            // Force flush to ensure booking is persisted immediately
            bookingRepository.flush();
            
            System.out.println("Booking saved with ID: " + booking.getBookingId());
            System.out.println("Booking status: " + booking.getStatus());

            // Create response
            BookServiceOfficerResponse resp = new BookServiceOfficerResponse();
            resp.setBookingId(booking.getBookingId());
            resp.setCustomerId(customer.getCustomerId());
            resp.setCustomerName(customer.getCustomerName());
            resp.setCustomerAddress(customer.getAddressLine1());
            resp.setCustomerMobile(customer.getMobileNumber());
            resp.setCustomerEmail(customer.getEmail());
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
            resp.setStatus(booking.getStatus());

            System.out.println("=== OFFICER BOOKING SUCCESS ===");
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Customer: " + customer.getCustomerName());
            System.out.println("Amount: " + serviceCost);

            return resp;

        } catch (Exception e) {
            System.out.println("=== OFFICER BOOKING ERROR ===");
            System.out.println("Exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Page<ViewAllBookingResponse> getAllBookings(
            String customerId, Long bookingId, LocalDateTime from, LocalDateTime to,
            String status, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("parcelPaymentTime").descending());

        if (from == null) from = LocalDateTime.of(1970, 1, 1, 0, 0);
        if (to == null) to = LocalDateTime.now();

        boolean filterByCustomerId = customerId != null && !customerId.trim().isEmpty();
        boolean filterByBookingId = bookingId != null;
        boolean filterByStatus = status != null && !status.trim().isEmpty();

        Page<Booking> bookingsPage;

        if (!filterByCustomerId && !filterByBookingId && !filterByStatus) {
            bookingsPage = bookingRepository.findByParcelPaymentTimeBetweenOrderByParcelPaymentTimeDesc(from, to, pageable);

        } else if (filterByCustomerId && filterByBookingId && filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdContainingAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    customerId, bookingId, from, to, status, pageable);

        } else if (filterByCustomerId && filterByBookingId) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdContainingAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    customerId, bookingId, from, to, "", pageable);

        } else if (filterByCustomerId && filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdContainingAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    customerId, status, pageable);

        } else if (filterByCustomerId) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdOrderByParcelPaymentTimeDesc(
                    customerId, pageable);

        } else if (filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdContainingAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    "", status, pageable);

        } else if (filterByBookingId) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdContainingAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    "", bookingId, from, to, "", pageable);

        } else {
            bookingsPage = bookingRepository.findByParcelPaymentTimeBetweenOrderByParcelPaymentTimeDesc(from, to, pageable);
        }

        return bookingsPage.map(booking -> {
            ViewAllBookingResponse resp = new ViewAllBookingResponse();
            resp.setCustomerId(booking.getCustomer().getCustomerId());
            resp.setCustomerName(booking.getCustomer().getCustomerName());
            resp.setBookingId(booking.getBookingId());
            resp.setBookingDate(booking.getParcelPaymentTime());
            resp.setReceiverName(booking.getReceiverName());
            resp.setDeliveredAddress(booking.getReceiverAddress());
            resp.setAmount(booking.getParcelServiceCost());
            resp.setStatus(booking.getStatus());
            return resp;
        });
    }
    
    public BookingCancelOfficerResponse cancelBookingByOfficer(Long bookingId, String customerId, String customerName) {
        if (bookingId == null && (customerId == null || customerId.trim().isEmpty()) && (customerName == null || customerName.trim().isEmpty())) {
            return new BookingCancelOfficerResponse(false, "Booking cancel failed: At least one identifier must be provided", null, null);
        }

        List<String> allowedStatuses = Arrays.asList("BOOKED");
        List<String> disallowedStatuses = Arrays.asList("DELIVERED", "IN TRANSIT");

        List<Booking> bookings = bookingRepository.findByBookingIdOrCustomer_CustomerIdOrCustomer_CustomerNameAndStatusIn(
                bookingId, customerId, customerName, allowedStatuses);

        if (bookings.isEmpty()) {
            return new BookingCancelOfficerResponse(false, "Booking cancel failed: No matching booking found or booking cannot be cancelled", null, null);
        }

        Booking booking = bookings.get(0);

        // Double-check that status is not DELIVERED or IN TRANSIT
        if (disallowedStatuses.contains(booking.getStatus().toUpperCase())) {
            return new BookingCancelOfficerResponse(false, "Booking cancel failed: Booking status '" + booking.getStatus() + "' cannot be cancelled", booking.getBookingId(), null);
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        return new BookingCancelOfficerResponse(true,
                "Booking cancelled successfully and Booking Amount will be refunded to the customer account within 5 working days",
                booking.getBookingId(),
                booking.getParcelServiceCost());
    }
    
    
}
