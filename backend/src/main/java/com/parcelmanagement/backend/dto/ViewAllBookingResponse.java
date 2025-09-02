package com.parcelmanagement.backend.dto;

import java.time.LocalDateTime;

public class ViewAllBookingResponse {
    private String customerId;
    private String customerName;
    private Long bookingId;
    private LocalDateTime bookingDate;
    private String receiverName;
    private String deliveredAddress;
    private Double amount;
    private String status;

    public ViewAllBookingResponse() {}
    
    public ViewAllBookingResponse(String customerId, String customerName, Long bookingId, LocalDateTime bookingDate,
			String receiverName, String deliveredAddress, Double amount, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.receiverName = receiverName;
		this.deliveredAddress = deliveredAddress;
		this.amount = amount;
		this.status = status;
	}

	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getDeliveredAddress() { return deliveredAddress; }
    public void setDeliveredAddress(String deliveredAddress) { this.deliveredAddress = deliveredAddress; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
