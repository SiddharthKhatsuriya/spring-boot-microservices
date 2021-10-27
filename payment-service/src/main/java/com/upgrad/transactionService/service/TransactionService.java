package com.upgrad.transactionService.service;

import com.upgrad.transactionService.entities.TransactionDetailsEntity;


public interface TransactionService {

  public TransactionDetailsEntity createTransaction(TransactionDetailsEntity transactionDetails);

  public TransactionDetailsEntity getTransaction(int id);

}
