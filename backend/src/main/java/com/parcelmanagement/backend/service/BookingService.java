package com.parcelmanagement.backend.service;

import com.parcelmanagement.backend.dto.BookServiceRequest;
import com.parcelmanagement.backend.dto.BookServiceResponse;
import com.parcelmanagement.backend.entities.Booking;
import com.parcelmanagement.backend.entities.Customer;
import com.parcelmanagement.backend.repository.BookingRepository;
import com.parcelmanagement.backend.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {
	@Autowired
    private BookingRepository bookingRepository;
	@Autowired
    private CustomerRepository customerRepository;

    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    public BookServiceResponse bookParcelService(BookServiceRequest req) {
         if (req.getCustomerId() == null || req.getCustomerId().trim().isEmpty()) throw new RuntimeException("CustomerId required");
        if (req.getReceiverName() == null || req.getReceiverName().trim().isEmpty()) throw new RuntimeException("Receiver name required");
        if (req.getReceiverAddress() == null || req.getReceiverAddress().trim().isEmpty()) throw new RuntimeException("Receiver address required");
        if (req.getReceiverPin() == null || !req.getReceiverPin().matches("\\d{6}")) throw new RuntimeException("Receiver pin must be 6 digits");
        if (req.getReceiverMobile() == null || !req.getReceiverMobile().matches("\\d{10,15}")) throw new RuntimeException("Receiver mobile invalid");
        if (req.getParcelWeightInGram() == null || req.getParcelWeightInGram() < 1) throw new RuntimeException("Weight must be at least 1g");
        if (req.getParcelContentsDescription() == null || req.getParcelContentsDescription().trim().isEmpty()) throw new RuntimeException("Contents required");
        if (req.getParcelDeliveryType() == null || (!req.getParcelDeliveryType().equalsIgnoreCase("STANDARD")
                && !req.getParcelDeliveryType().equalsIgnoreCase("EXPRESS")
                && !req.getParcelDeliveryType().equalsIgnoreCase("SAMEDAY"))) throw new RuntimeException("DeliveryType invalid");
        if (req.getParcelPackingPreference() == null || (!req.getParcelPackingPreference().equalsIgnoreCase("BASIC")
                && !req.getParcelPackingPreference().equalsIgnoreCase("PREMIUM"))) throw new RuntimeException("PackingPreference invalid");
        if (req.getParcelPickupTime() == null) throw new RuntimeException("Pickup time required");
        if (req.getParcelDropoffTime() == null) throw new RuntimeException("Dropoff time required");
        if (req.getParcelDropoffTime().isBefore(req.getParcelPickupTime())) throw new RuntimeException("Dropoff must be after pickup");

        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        double baseRate = 50.0;
        double costPerGram = 0.02;
        double weightCharge = costPerGram * req.getParcelWeightInGram();
        double deliveryCharge = 0;
        switch (req.getParcelDeliveryType().toUpperCase()) {
            case "STANDARD": deliveryCharge = 30; break;
            case "EXPRESS": deliveryCharge = 80; break;
            case "SAMEDAY": deliveryCharge = 150; break;
        }
        double packingCharge = req.getParcelPackingPreference().equalsIgnoreCase("BASIC") ? 10 : 30;
        double taxRate = 0.05;
        double serviceCost = (baseRate + weightCharge + deliveryCharge + packingCharge) * (1 + taxRate);
        serviceCost = Math.round(serviceCost * 100.0) / 100.0;

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
        booking.setStatus("NEW");

        booking = bookingRepository.save(booking);

        BookServiceResponse resp = new BookServiceResponse();
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

        return resp;
    }
}
