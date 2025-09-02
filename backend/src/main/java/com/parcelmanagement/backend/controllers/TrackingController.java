package com.parcelmanagement.backend.controllers;

import com.parcelmanagement.backend.dto.TrackingStatusResponse;
import com.parcelmanagement.backend.dto.DeliveryStatusUpdateRequest;
import com.parcelmanagement.backend.dto.DeliveryStatusUpdateResponse;
import com.parcelmanagement.backend.dto.PickupDropUpdateRequest;
import com.parcelmanagement.backend.dto.PickupDropUpdateResponse;
import com.parcelmanagement.backend.dto.TrackingStatusOfficerResponse;
import com.parcelmanagement.backend.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {
    @Autowired
    private TrackingService trackingService;

    @GetMapping("/customer-status")
    public ResponseEntity<TrackingStatusResponse> trackForCustomer(
            @RequestParam("customerId") String customerId,
            @RequestParam("bookingId") Long bookingId) {
        TrackingStatusResponse response = trackingService.trackForCustomer(customerId, bookingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/officer-status")
    public ResponseEntity<TrackingStatusOfficerResponse> trackForOfficer(
            @RequestParam("bookingId") Long bookingId) {
        TrackingStatusOfficerResponse response = trackingService.trackForOfficer(bookingId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/officer/pickup-drop")
    public ResponseEntity<PickupDropUpdateResponse> updatePickupDrop(
            @RequestBody PickupDropUpdateRequest request) {
        PickupDropUpdateResponse response = trackingService.updatePickupDrop(
                request.getBookingId(), request.getParcelPickupTime(), request.getParcelDropoffTime());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/officer/delivery-status")
    public ResponseEntity<DeliveryStatusUpdateResponse> updateDeliveryStatus(
            @RequestBody DeliveryStatusUpdateRequest request) {
        DeliveryStatusUpdateResponse response = trackingService.updateDeliveryStatus(
                request.getBookingId(), request.getStatus());
        return ResponseEntity.ok(response);
    }
}
