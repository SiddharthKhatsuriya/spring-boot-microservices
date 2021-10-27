package com.upgrad.bookingService.utils;

import com.upgrad.bookingService.dto.BookingDTO;
import com.upgrad.bookingService.entities.BookingInfoEntity;


public class POJOConvertor {

  public static BookingDTO covertBookingEntityToDTO(BookingInfoEntity booking) {
    BookingDTO bookingDTO = new BookingDTO();
    bookingDTO.setBookingId(booking.getBookingId());
    bookingDTO.setAadharNumber(booking.getAadharNumber());
    bookingDTO.setFromDate(booking.getFromDate());
    bookingDTO.setToDate(booking.getToDate());
    bookingDTO.setBookedOn(booking.getBookedOn());
    bookingDTO.setNoOfRooms(booking.getNoOfRooms());
    bookingDTO.setRoomNumbers(booking.getRoomNumbers());
    bookingDTO.setRoomPrice(booking.getRoomPrice());
    bookingDTO.setTransactionId(booking.getTransactionId());

    return bookingDTO;
  }

  public static BookingInfoEntity covertBookingDTOToEntity(BookingDTO bookingDTO) {
    BookingInfoEntity booking = new BookingInfoEntity();
    booking.setBookingId(bookingDTO.getBookingId());
    booking.setAadharNumber(bookingDTO.getAadharNumber());
    booking.setFromDate(bookingDTO.getFromDate());
    booking.setToDate(bookingDTO.getToDate());
    booking.setBookedOn(bookingDTO.getBookedOn());
    booking.setNoOfRooms(bookingDTO.getNoOfRooms());
    booking.setRoomNumbers(bookingDTO.getRoomNumbers());
    booking.setRoomPrice(bookingDTO.getRoomPrice());
    booking.setTransactionId(bookingDTO.getTransactionId());

    return booking;
  }
}
