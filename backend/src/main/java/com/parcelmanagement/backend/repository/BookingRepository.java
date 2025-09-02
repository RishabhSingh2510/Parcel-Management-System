package com.parcelmanagement.backend.repository;

import com.parcelmanagement.backend.entities.Booking;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingIdAndCustomer_CustomerId(Long bookingId, String customerId);

    Booking findByBookingId(Long bookingId);

    Page<Booking> findByCustomer_CustomerIdAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCase(
            String customerId, Long bookingId, LocalDateTime from, LocalDateTime to, String status, Pageable pageable);

    Page<Booking> findByCustomer_CustomerIdOrderByParcelPaymentTimeDesc(String customerId, Pageable pageable);

    Page<Booking> findByCustomer_CustomerIdAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
            String customerId, String status, Pageable pageable);

    Page<Booking> findByCustomer_CustomerIdContainingAndBookingIdAndParcelPaymentTimeBetweenAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
            String customerId, Long bookingId, LocalDateTime from, LocalDateTime to, String status, Pageable pageable);

    Page<Booking> findByParcelPaymentTimeBetweenOrderByParcelPaymentTimeDesc(LocalDateTime from, LocalDateTime to, Pageable pageable);
    
    Page<Booking> findByCustomer_CustomerIdContainingAndStatusContainingIgnoreCaseOrderByParcelPaymentTimeDesc(
    	    String customerId, String status, Pageable pageable);
    
    Optional<Booking> findByBookingIdAndStatus(Long bookingId, String status);

	List<Booking> findByBookingIdOrCustomer_CustomerIdOrCustomer_CustomerNameAndStatusIn(Long bookingId,
			String customerId, String customerName, List<String> allowedStatuses);
}
