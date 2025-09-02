package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.AddFeedbackRequest;
import com.parcelmanagement.backend.dto.AddFeedbackResponse;
import com.parcelmanagement.backend.dto.OfficerFeedbackResponse;
import com.parcelmanagement.backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<AddFeedbackResponse> addFeedback(@RequestBody AddFeedbackRequest request) {
        try {
            AddFeedbackResponse response = feedbackService.addFeedback(request);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new AddFeedbackResponse(false, "Failed to add feedback: " + e.getMessage())
            );
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getFeedbackByBookingId(@PathVariable Long bookingId) {
        try {
            var feedback = feedbackService.getFeedbackByBookingId(bookingId);
            if (feedback != null) {
                return ResponseEntity.ok(feedback);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new AddFeedbackResponse(false, "Failed to get feedback: " + e.getMessage())
            );
        }
    }
}

@RestController
@RequestMapping("/api/officer/feedback")
class OfficerFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/all")
    public ResponseEntity<List<OfficerFeedbackResponse>> getAllFeedback() {
        try {
            List<OfficerFeedbackResponse> feedbacks = feedbackService.getAllFeedback();
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}