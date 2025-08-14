package com.example.payment_service.dto;

public record PaymentRequest(String amount, String paymentType, String sender, String receiver) {
}
