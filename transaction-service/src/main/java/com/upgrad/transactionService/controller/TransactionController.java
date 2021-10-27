package com.upgrad.transactionService.controller;

import com.upgrad.transactionService.dto.ErrorResponseDTO;
import com.upgrad.transactionService.dto.TransactionDTO;
import com.upgrad.transactionService.entities.TransactionDetailsEntity;
import com.upgrad.transactionService.service.TransactionService;
import com.upgrad.transactionService.utils.POJOConvertor;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "payment")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  /**
   * Create a POST API
   */

  @PostMapping(value="/transaction" , consumes = MediaType.APPLICATION_JSON_VALUE ,
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createTransaction(@RequestBody TransactionDTO transactionDTO){
    try {
      if ( ! ("upi".equals(transactionDTO.getPaymentMode().toLowerCase()) || "card".equals(transactionDTO.getPaymentMode().toLowerCase()))){
        return new ResponseEntity(new ErrorResponseDTO( HttpStatus.BAD_REQUEST.value(),"Invalid mode of payment"), HttpStatus.BAD_REQUEST);
      }
      TransactionDetailsEntity transactionDetails = POJOConvertor.coverTransactionDTOToEntity(transactionDTO);

      TransactionDetailsEntity savedTransaction = transactionService.createTransaction(transactionDetails);

      TransactionDTO savedTransactionDTO = POJOConvertor.covertTransactionEntityToDTO(transactionDetails);

      return new ResponseEntity(savedTransactionDTO.getTransactionId(), HttpStatus.CREATED);
    } catch (FeignException e) {
      if (e.getMessage().contains("Invalid Booking Id")) {
        return new ResponseEntity(new ErrorResponseDTO(e.status(), "Invalid Booking Id"), HttpStatus.BAD_REQUEST);
      } else {
        return new ResponseEntity(new ErrorResponseDTO(e.status(), e.getMessage()), HttpStatus.BAD_REQUEST);
      }
    }
  }

  @GetMapping(value="/transaction/{id}")
  public ResponseEntity getTransaction(@PathVariable("id") int id){
    TransactionDetailsEntity savedTransaction = transactionService.getTransaction(id);
    TransactionDTO transactionDTO = POJOConvertor.covertTransactionEntityToDTO(savedTransaction);
    return new ResponseEntity( transactionDTO, HttpStatus.OK);
  }


}