package com.parcelmanagement.backend.repository;

import com.parcelmanagement.backend.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBooking_BookingId(Long bookingId);
}
