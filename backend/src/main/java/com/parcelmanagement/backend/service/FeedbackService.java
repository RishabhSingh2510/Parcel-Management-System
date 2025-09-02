package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.AddFeedbackRequest;
import com.parcelmanagement.backend.dto.AddFeedbackResponse;
import com.parcelmanagement.backend.dto.OfficerFeedbackResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Feedback;
import com.parcelmanagement.backend.repository.BookingRepository;
import com.parcelmanagement.backend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public AddFeedbackResponse addFeedback(AddFeedbackRequest request) {
        if (request.getBookingId() == null) {
            return new AddFeedbackResponse(false, "Booking ID is required");
        }

        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            return new AddFeedbackResponse(false, "Feedback description is required");
        }

        if (request.getDescription().trim().length() < 10) {
            return new AddFeedbackResponse(false, "Feedback description must be at least 10 characters");
        }

        if (request.getDescription().trim().length() > 1000) {
            return new AddFeedbackResponse(false, "Feedback description cannot exceed 1000 characters");
        }

        if (request.getRating() == null || request.getRating() < 1 || request.getRating() > 5) {
            return new AddFeedbackResponse(false, "Rating must be between 1 and 5");
        }

        Booking booking = bookingRepository.findByBookingId(request.getBookingId());
        if (booking == null) {
            return new AddFeedbackResponse(false, "Booking not found");
        }

        if (!"DELIVERED".equalsIgnoreCase(booking.getStatus())) {
            return new AddFeedbackResponse(false, "Feedback can only be added for delivered bookings");
        }

        Feedback existingFeedback = feedbackRepository.findByBooking_BookingId(request.getBookingId());
        if (existingFeedback != null) {
            existingFeedback.setDescription(request.getDescription().trim());
            existingFeedback.setRating(request.getRating());
            feedbackRepository.save(existingFeedback);
            return new AddFeedbackResponse(true, "Feedback updated successfully");
        }

        Feedback feedback = new Feedback();
        feedback.setBooking(booking);
        feedback.setDescription(request.getDescription().trim());
        feedback.setRating(request.getRating());

        feedbackRepository.save(feedback);

        return new AddFeedbackResponse(true, "Feedback added successfully");
    }

    public List<OfficerFeedbackResponse> getAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAllByOrderByCreatedDateDesc();
        
        return feedbacks.stream().map(feedback -> {
            OfficerFeedbackResponse response = new OfficerFeedbackResponse();
            response.setBookingId(feedback.getBooking().getBookingId());
            response.setCustomerName(feedback.getBooking().getCustomer().getCustomerName());
            response.setFeedbackDescription(feedback.getDescription());
            response.setRating(feedback.getRating());
            response.setFeedbackDateTime(feedback.getCreatedDate());
            return response;
        }).collect(Collectors.toList());
    }

    public Feedback getFeedbackByBookingId(Long bookingId) {
        if (bookingId == null) {
            throw new RuntimeException("Booking ID is required");
        }
        return feedbackRepository.findByBooking_BookingId(bookingId);
    }
}