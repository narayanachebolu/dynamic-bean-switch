package com.example.payment_service.service.impl;

import com.example.payment_service.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(RazorPayPaymentService.class);

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        logger.info("Processing payment with RazorPay: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "Paid with RazorPay " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}
