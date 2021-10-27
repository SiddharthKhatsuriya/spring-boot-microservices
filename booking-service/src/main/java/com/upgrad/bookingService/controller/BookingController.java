package com.upgrad.bookingService.controller;

import com.upgrad.bookingService.dto.BookingDTO;
import com.upgrad.bookingService.dto.ErrorResponseDTO;
import com.upgrad.bookingService.dto.PaymentRequestDTO;
import com.upgrad.bookingService.entities.BookingInfoEntity;
import com.upgrad.bookingService.service.BookingService;
import com.upgrad.bookingService.utils.POJOConvertor;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping(value = "hotel")
public class BookingController {

  @Autowired
  private BookingService bookingService;

  /**
   * Create a POST API
   */

  @PostMapping(value="/booking" , consumes = MediaType.APPLICATION_JSON_VALUE ,
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createBooking(@RequestBody BookingDTO bookingDTO){
    BookingInfoEntity booking = POJOConvertor.covertBookingDTOToEntity(bookingDTO);

    BookingInfoEntity savedBooking = bookingService.createBooking(booking);

    BookingDTO savedBookingDTO = POJOConvertor.covertBookingEntityToDTO(booking);

    return new ResponseEntity(savedBookingDTO , HttpStatus.CREATED);
  }

  @GetMapping(value="/booking/{id}")
  public ResponseEntity getTransaction(@PathVariable("id") int id){
    try {
      BookingInfoEntity savedBooking = bookingService.getBooking(id);
      BookingDTO bookingDTO = POJOConvertor.covertBookingEntityToDTO(savedBooking);
      return new ResponseEntity(bookingDTO, HttpStatus.OK);
    } catch (NoSuchElementException e){
      return new ResponseEntity(new ErrorResponseDTO( HttpStatus.BAD_REQUEST.value(),"Invalid Booking Id"), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value="/booking/{id}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE ,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateTransaction(@PathVariable("id") int id, @RequestBody PaymentRequestDTO paymentRequestDTO){
    try {
      BookingInfoEntity updatedBooking = bookingService.updateTransaction(id, paymentRequestDTO);
      BookingDTO savedBookingDTO = POJOConvertor.covertBookingEntityToDTO(updatedBooking);
      return new ResponseEntity(savedBookingDTO, HttpStatus.OK);
    } catch (NoSuchElementException e){
      return new ResponseEntity(new ErrorResponseDTO( HttpStatus.BAD_REQUEST.value(),"Invalid Booking Id"), HttpStatus.BAD_REQUEST);
    } catch (FeignException e){
        String message = e.getMessage();
        if (e.getMessage().contains("Invalid Booking Id")) {
          message = "Invalid Booking Id";
        } else if (e.getMessage().contains("Invalid mode of payment")) {
          message = "Invalid mode of payment";

        }
        return new ResponseEntity(new ErrorResponseDTO(e.status(), message), HttpStatus.BAD_REQUEST);
    }

  }
  


}