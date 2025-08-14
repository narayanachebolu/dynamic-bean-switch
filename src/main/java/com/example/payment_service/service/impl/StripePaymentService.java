package com.example.payment_service.service.impl;

import com.example.payment_service.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(StripePaymentService.class);

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        logger.info("Processing payment with Stripe: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "Paid with stripe " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}
