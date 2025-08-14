package com.example.payment_service.controller;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.service.impl.PaypalPaymentService;
import com.example.payment_service.service.impl.RazorPayPaymentService;
import com.example.payment_service.service.impl.StripePaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    public static final String PAYPAL = "paypal";
    public static final String RAZORPAY = "razorpay";
    public static final String STRIPE = "stripe";

    private final PaypalPaymentService paypalPaymentService;
    private final RazorPayPaymentService razorPayPaymentService;
    private final StripePaymentService stripePaymentService;

    public PaymentController(PaypalPaymentService paypalPaymentService, RazorPayPaymentService razorPayPaymentService, StripePaymentService stripePaymentService) {
        this.paypalPaymentService = paypalPaymentService;
        this.razorPayPaymentService = razorPayPaymentService;
        this.stripePaymentService = stripePaymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest) {
        String amount = paymentRequest.amount();
        String paymentType = paymentRequest.paymentType();
        String sender = paymentRequest.sender();
        String receiver = paymentRequest.receiver();

        return ResponseEntity.ok(switch (paymentType.toLowerCase()) {
            case PAYPAL -> paypalPaymentService.pay(amount, paymentType,sender, receiver);
            case RAZORPAY -> razorPayPaymentService.pay(amount, paymentType,sender, receiver);
            case STRIPE -> stripePaymentService.pay(amount, paymentType,sender, receiver);
            default -> throw new IllegalArgumentException("Unsupported payment mode: " + paymentType);
        });
    }
}
