package com.upgrad.bookingService.service;

import com.upgrad.bookingService.dao.BookingDao;
import com.upgrad.bookingService.dto.PaymentRequestDTO;
import com.upgrad.bookingService.entities.BookingInfoEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

import com.upgrad.bookingService.feign.PaymentClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private PaymentClient paymentClient;


    static int findDifference(LocalDate start_date, LocalDate end_date) {

        // find the period between
        // the start and end date
        Period diff;
        diff = Period.between(start_date, end_date);

        // Print the date difference
        // in years, months, and days
        System.out.print(
                "Difference "
                        + "between two dates is: ");

        // Print the result
        System.out.printf(
                "%d years, %d months"
                        + " and %d days ",
                diff.getYears(),
                diff.getMonths(),
                diff.getDays());
        return diff.getDays();
    }

    static String getRoomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return String.join(", ", numberList);
    }

    @Override
    public BookingInfoEntity createBooking(BookingInfoEntity booking) {
        System.out.println(booking);
        booking.setBookedOn(LocalDate.now());
        booking.setRoomPrice((booking.getNoOfRooms() * findDifference(booking.getFromDate(), booking.getToDate())) * 1000);
        booking.setRoomNumbers(getRoomNumbers(booking.getNoOfRooms()));
        return bookingDao.save(booking);
    }

    @Override
    public BookingInfoEntity getBooking(int id) {
        return bookingDao.findById(id).get();
    }

    @Override
    public BookingInfoEntity updateTransaction(int bookingId, PaymentRequestDTO paymentRequestDTO){
        BookingInfoEntity savedBooking = bookingDao.findById(bookingId).get();
        int transaction = paymentClient.createTransaction(paymentRequestDTO);
        savedBooking.setTransactionId(transaction);
        return bookingDao.save(savedBooking);
    }

    public BookingDao getBookingDao() {
        return bookingDao;
    }

    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
}
