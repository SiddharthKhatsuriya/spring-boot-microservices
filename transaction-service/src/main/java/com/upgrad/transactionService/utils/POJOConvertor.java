package com.upgrad.transactionService.utils;

import com.upgrad.transactionService.dto.TransactionDTO;
import com.upgrad.transactionService.entities.TransactionDetailsEntity;


public class POJOConvertor {

  public static TransactionDTO covertTransactionEntityToDTO(TransactionDetailsEntity transactionDetails) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setTransactionId(transactionDetails.getTransactionId());
    transactionDTO.setPaymentMode(transactionDetails.getPaymentMode());
    transactionDTO.setCardNumber(transactionDetails.getCardNumber());
    transactionDTO.setBookingId(transactionDetails.getBookingId());
    transactionDTO.setUpiId(transactionDetails.getUpiId());

    return transactionDTO;
  }

  public static TransactionDetailsEntity coverTransactionDTOToEntity(TransactionDTO transactionDTO) {
    TransactionDetailsEntity transactionDetails = new TransactionDetailsEntity();
    transactionDetails.setTransactionId(transactionDTO.getTransactionId());
    transactionDetails.setPaymentMode(transactionDTO.getPaymentMode());
    transactionDetails.setCardNumber(transactionDTO.getCardNumber());
    transactionDetails.setBookingId(transactionDTO.getBookingId());
    transactionDetails.setUpiId(transactionDTO.getUpiId());

    return transactionDetails;
  }
}
