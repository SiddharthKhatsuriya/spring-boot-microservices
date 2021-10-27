package com.upgrad.bookingService.feign;

import com.upgrad.bookingService.dto.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient( name="payment-client", url="${payment-service-url}")
public interface PaymentClient {
    @PostMapping("/transaction")
    public int createTransaction(PaymentRequestDTO paymentRequestDTO);
}
