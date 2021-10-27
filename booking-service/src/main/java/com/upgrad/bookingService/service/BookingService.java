package com.upgrad.bookingService.service;

import com.upgrad.bookingService.dto.PaymentRequestDTO;
import com.upgrad.bookingService.entities.BookingInfoEntity;


public interface BookingService {

  public BookingInfoEntity createBooking(BookingInfoEntity booking);

  public BookingInfoEntity getBooking(int id);

  public BookingInfoEntity updateTransaction(int bookingId, PaymentRequestDTO paymentRequestDTO);

  }
