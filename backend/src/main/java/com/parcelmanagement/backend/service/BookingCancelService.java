package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.BookingCancelResponse;
import com.parcelmanagement.backend.dto.BookingCancelOfficerResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingCancelService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingCancelResponse cancelBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findByBookingIdAndStatus(bookingId, "BOOKED");
        if (bookingOpt.isEmpty()) {
            return new BookingCancelResponse(false, "Booking cancel failed, incorrect Booking ID or status not 'BOOKED'.", bookingId);
        }

        Booking booking = bookingOpt.get();
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        return new BookingCancelResponse(true, "Booking cancelled successfully", bookingId);
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