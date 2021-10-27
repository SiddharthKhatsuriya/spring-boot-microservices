package com.upgrad.transactionService.service;

import com.upgrad.transactionService.dao.TransactionDao;
import com.upgrad.transactionService.dto.BookingResponseDTO;
import com.upgrad.transactionService.entities.TransactionDetailsEntity;
import com.upgrad.transactionService.feign.BookingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private BookingClient bookingClient;

    @Override
    public TransactionDetailsEntity createTransaction(TransactionDetailsEntity transactionDetails) {

        BookingResponseDTO bookingInfo = bookingClient.getBooking(transactionDetails.getBookingId());
        TransactionDetailsEntity transaction = transactionDao.save(transactionDetails);
        String message = "Booking confirmed for user with aadhaar number: "
                + bookingInfo.getAadharNumber()
                +    "    |    "
                + "Here are the booking details:    " + bookingInfo.toString();
        System.out.println(message);
        return transaction;
    }

    @Override
    public TransactionDetailsEntity getTransaction(int id) {
        return transactionDao.findById(id).get();
    }

    public TransactionDao getTransactionDao() {
        return transactionDao;
    }

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
}
