package com.upgrad.transactionService.feign;

import com.upgrad.transactionService.dto.BookingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name="booking-client", url="${booking-service-url}")
public interface BookingClient {
    @GetMapping("/booking/{id}")
    public BookingResponseDTO getBooking(@PathVariable int id);
}
