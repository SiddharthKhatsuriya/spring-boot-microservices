package com.upgrad.transactionService.dao;

import com.upgrad.transactionService.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionDao extends JpaRepository<TransactionDetailsEntity, Integer> {

}
