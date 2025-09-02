package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.TrackingStatusResponse;
import com.parcelmanagement.backend.dto.DeliveryStatusUpdateResponse;
import com.parcelmanagement.backend.dto.PickupDropUpdateResponse;
import com.parcelmanagement.backend.dto.TrackingStatusOfficerResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Customer;
import com.parcelmanagement.backend.repository.BookingRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {
    @Autowired
    private BookingRepository bookingRepository;

    public TrackingStatusResponse trackForCustomer(String customerId, Long bookingId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new RuntimeException("Customer ID is required.");
        }
        if (bookingId == null) {
            throw new RuntimeException("Booking ID is required.");
        }

        Booking booking = bookingRepository.findByBookingIdAndCustomer_CustomerId(bookingId, customerId);
        if (booking == null) {
            throw new RuntimeException("No booking found for this Booking ID and Customer.");
        }

        TrackingStatusResponse resp = new TrackingStatusResponse();
        resp.setBookingId(booking.getBookingId());
        resp.setReceiverName(booking.getReceiverName());
        resp.setReceiverAddress(booking.getReceiverAddress());
        resp.setDateOfBooking(booking.getParcelPaymentTime());
        resp.setStatus(booking.getStatus());

        return resp;
    }

 // Replace the trackForOfficer method in your TrackingService.java with this enhanced version

    public TrackingStatusOfficerResponse trackForOfficer(Long bookingId) {
        if (bookingId == null) {
            throw new RuntimeException("Booking ID is required.");
        }

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if (booking == null) {
            throw new RuntimeException("No booking found for this Booking ID.");
        }

        Customer customer = booking.getCustomer();
        String fullName = customer.getCustomerName();

        String address = customer.getAddressLine1();
        if (customer.getAddressLine2() != null && !customer.getAddressLine2().trim().isEmpty()) {
            address += ", " + customer.getAddressLine2();
        }
        address += ", " + customer.getDistrict()
                + ", " + customer.getState()
                + ", " + customer.getCountry()
                + " - " + customer.getPinCode();

        TrackingStatusOfficerResponse resp = new TrackingStatusOfficerResponse();
        resp.setBookingId(booking.getBookingId());
        resp.setFullName(fullName);
        resp.setAddress(address);
        resp.setReceiverName(booking.getReceiverName());
        resp.setReceiverAddress(booking.getReceiverAddress());
        resp.setDateOfBooking(booking.getParcelPaymentTime());
        resp.setStatus(booking.getStatus());
        
        // IMPORTANT: Include the current pickup and drop times
        resp.setParcelPickupTime(booking.getParcelPickupTime());
        resp.setParcelDropoffTime(booking.getParcelDropoffTime());

        return resp;
    }
    
 // Add this enhanced method to your existing TrackingService.java

 // Replace your updatePickupDrop method in TrackingService.java with this debug version

    @Transactional
    public PickupDropUpdateResponse updatePickupDrop(Long bookingId, LocalDateTime pickup, LocalDateTime dropoff) {
        System.out.println("=== PICKUP DROP UPDATE DEBUG ===");
        System.out.println("Received booking ID: " + bookingId);
        System.out.println("Received pickup time: " + pickup);
        System.out.println("Received dropoff time: " + dropoff);
        
        if (bookingId == null) throw new RuntimeException("Booking ID is required.");
        if (pickup == null) throw new RuntimeException("Pickup date/time is required.");
        if (dropoff == null) throw new RuntimeException("Dropoff date/time is required.");
        if (dropoff.isBefore(pickup)) throw new RuntimeException("Dropoff must be after pickup.");

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if (booking == null) throw new RuntimeException("Booking not found.");

        System.out.println("Found booking with current pickup time: " + booking.getParcelPickupTime());
        System.out.println("Found booking with current dropoff time: " + booking.getParcelDropoffTime());
        System.out.println("Current booking status: " + booking.getStatus());

        // IMPORTANT: Add validation to prevent updating cancelled parcels
        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Cannot update pickup/drop times for cancelled parcels. Current status: " + booking.getStatus());
        }

        // Also prevent updates for delivered parcels
        if ("DELIVERED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Cannot update pickup/drop times for delivered parcels. Current status: " + booking.getStatus());
        }

        System.out.println("Setting new pickup time: " + pickup);
        System.out.println("Setting new dropoff time: " + dropoff);

        booking.setParcelPickupTime(pickup);
        booking.setParcelDropoffTime(dropoff);
        
        System.out.println("Before saving - booking pickup time: " + booking.getParcelPickupTime());
        System.out.println("Before saving - booking dropoff time: " + booking.getParcelDropoffTime());
        
        booking = bookingRepository.save(booking);
        
        System.out.println("After saving - booking pickup time: " + booking.getParcelPickupTime());
        System.out.println("After saving - booking dropoff time: " + booking.getParcelDropoffTime());

        Customer customer = booking.getCustomer();
        String fullName = customer.getCustomerName();

        String address = customer.getAddressLine1();
        if (customer.getAddressLine2() != null && !customer.getAddressLine2().trim().isEmpty()) {
            address += ", " + customer.getAddressLine2();
        }
        address += ", " + customer.getDistrict()
                + ", " + customer.getState()
                + ", " + customer.getCountry()
                + " - " + customer.getPinCode();

        PickupDropUpdateResponse resp = new PickupDropUpdateResponse();
        resp.setBookingId(booking.getBookingId());
        resp.setFullName(fullName);
        resp.setAddress(address);
        resp.setReceiverName(booking.getReceiverName());
        resp.setReceiverAddress(booking.getReceiverAddress());
        resp.setDateOfBooking(booking.getParcelPaymentTime());
        resp.setStatus(booking.getStatus());
        resp.setParcelPickupTime(booking.getParcelPickupTime());
        resp.setParcelDropoffTime(booking.getParcelDropoffTime());
        resp.setMessage("Pickup and Dropoff times updated successfully.");

        System.out.println("Response pickup time: " + resp.getParcelPickupTime());
        System.out.println("Response dropoff time: " + resp.getParcelDropoffTime());
        System.out.println("=== PICKUP DROP UPDATE DEBUG END ===");

        return resp;
    }
    public DeliveryStatusUpdateResponse updateDeliveryStatus(Long bookingId, String newStatus) {
        if (bookingId == null) throw new RuntimeException("Booking ID is required.");
        if (newStatus == null || newStatus.trim().isEmpty()) throw new RuntimeException("Status is required.");

        String[] allowed = {"New", "Scheduled", "PickedUp", "Assigned", "Booked", "In Transit", "Delivered", "Cancelled"};
        boolean isAllowed = false;
        for (String s : allowed) {
            if (s.equalsIgnoreCase(newStatus.trim())) {
                isAllowed = true;
                newStatus = s; 
                break;
            }
        }
        if (!isAllowed)
            throw new RuntimeException("Invalid status. Allowed: New, Scheduled, PickedUp, Assigned, Booked, In Transit, Delivered, Cancelled.");

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if (booking == null) throw new RuntimeException("Booking not found.");

         if (newStatus.equals("Booked")) {
            if (!"Booked".equals(booking.getStatus())) {
                throw new RuntimeException("Booking can only be set to 'Booked' after payment is completed.");
            }
        }

        booking.setStatus(newStatus);
        bookingRepository.save(booking);

        Customer customer = booking.getCustomer();
        String fullName = customer.getCustomerName();
        String address = customer.getAddressLine1();
        if (customer.getAddressLine2() != null && !customer.getAddressLine2().trim().isEmpty()) {
            address += ", " + customer.getAddressLine2();
        }
        address += ", " + customer.getDistrict()
                + ", " + customer.getState()
                + ", " + customer.getCountry()
                + " - " + customer.getPinCode();

        DeliveryStatusUpdateResponse resp = new DeliveryStatusUpdateResponse();
        resp.setBookingId(booking.getBookingId());
        resp.setFullName(fullName);
        resp.setAddress(address);
        resp.setReceiverName(booking.getReceiverName());
        resp.setReceiverAddress(booking.getReceiverAddress());
        resp.setDateOfBooking(booking.getParcelPaymentTime());
        resp.setStatus(booking.getStatus());
        resp.setMessage("Delivery status updated successfully.");

        return resp;
    }
}
