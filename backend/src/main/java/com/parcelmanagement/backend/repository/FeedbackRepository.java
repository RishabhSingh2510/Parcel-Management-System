package com.parcelmanagement.backend.repository;

import com.parcelmanagement.backend.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    Feedback findByBooking_BookingId(Long bookingId);
    
    List<Feedback> findByBooking_Customer_CustomerId(String customerId);
    
    List<Feedback> findAllByOrderByCreatedDateDesc();
    
    List<Feedback> findByRating(Integer rating);
    
    boolean existsByBooking_BookingId(Long bookingId);
}