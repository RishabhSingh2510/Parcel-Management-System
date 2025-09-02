package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.PreviousBookingResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreviousBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    private static final LocalDateTime MIN_DATE = LocalDateTime.of(1970, 1, 1, 0, 0);

    public Page<PreviousBookingResponse> getPreviousBookings(
            String customerId, Long bookingId,
            LocalDateTime from, LocalDateTime to,
            String status, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("parcelPaymentTime").descending());
        Page<Booking> bookingsPage;

        boolean filterByBookingId = bookingId != null;
        boolean filterByDate = from != null && to != null;
        boolean filterByStatus = status != null && !status.isEmpty();

        LocalDateTime fromDate = filterByDate ? from : MIN_DATE;
        LocalDateTime toDate = filterByDate ? to : LocalDateTime.now();

        if (filterByBookingId && filterByDate && filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCase(
                    customerId, bookingId, fromDate, toDate, status, pageable);
        } else if (filterByBookingId && filterByDate) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCase(
                    customerId, bookingId, fromDate, toDate, "", pageable);
        } else if (filterByBookingId && filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCase(
                    customerId, bookingId, MIN_DATE, LocalDateTime.now(), status, pageable);
        } else if (filterByBookingId) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCase(
                    customerId, bookingId, MIN_DATE, LocalDateTime.now(), "", pageable);
        } else if (filterByStatus) {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
                    customerId, status, pageable);
        } else {
            bookingsPage = bookingRepository.findByCustomer_CustomerIdOrderByParcelPaymentTimeDesc(customerId, pageable);
        }

        return bookingsPage.map(booking -> {
            PreviousBookingResponse resp = new PreviousBookingResponse();
            resp.setCustomerId(booking.getCustomer().getCustomerId());
            resp.setBookingId(booking.getBookingId());
            resp.setBookingDate(booking.getParcelPaymentTime());
            resp.setReceiverName(booking.getReceiverName());
            resp.setDeliveredAddress(booking.getReceiverAddress());
            resp.setAmount(booking.getParcelServiceCost());
            resp.setStatus(booking.getStatus());
            return resp;
        });
    }

    public List<PreviousBookingResponse> getAllBookingsForDownload(String customerId) {
        List<Booking> bookings = bookingRepository.findByCustomer_CustomerIdOrderByParcelPaymentTimeDesc(customerId, Pageable.unpaged()).getContent();
        return bookings.stream().map(booking -> {
            PreviousBookingResponse resp = new PreviousBookingResponse();
            resp.setCustomerId(booking.getCustomer().getCustomerId());
            resp.setBookingId(booking.getBookingId());
            resp.setBookingDate(booking.getParcelPaymentTime());
            resp.setReceiverName(booking.getReceiverName());
            resp.setDeliveredAddress(booking.getReceiverAddress());
            resp.setAmount(booking.getParcelServiceCost());
            resp.setStatus(booking.getStatus());
            return resp;
        }).collect(Collectors.toList());
    }
}
