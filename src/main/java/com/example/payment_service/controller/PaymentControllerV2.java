package com.example.payment_service.controller;

import com.example.payment_service.PaymentServiceApplication;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment/v2")
public class PaymentControllerV2 {
    private final Map<String, PaymentService> paymentServiceMap;

    public PaymentControllerV2(Map<String, PaymentService> paymentServiceMap) {
        this.paymentServiceMap = paymentServiceMap;
    }

    @PostMapping("/pay/v2")
    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest) {
        String paymentType = paymentRequest.paymentType().toLowerCase();
        PaymentService paymentService = paymentServiceMap.get(paymentType);
        if (paymentService == null) {
            throw new IllegalArgumentException("Unsupported payment mode: " + paymentType);
        }

        return ResponseEntity.ok(paymentService.pay(paymentRequest.amount(), paymentRequest.paymentType(), paymentRequest.sender(), paymentRequest.receiver()));
    }
}
