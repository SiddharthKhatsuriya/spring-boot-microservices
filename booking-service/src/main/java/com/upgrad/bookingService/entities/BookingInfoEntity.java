package com.upgrad.bookingService.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int bookingId;

    @Column(name = "from_date", nullable = true)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = true)
    private LocalDate toDate;

    @Column(name = "booked_on", nullable = true)
    private LocalDate bookedOn;

    @Column(name = "aadhar_number", nullable = true)
    private String aadharNumber;

    @Column(name = "no_of_rooms")
    private int noOfRooms;

    @Column(name = "room_numbers")
    private String roomNumbers;

    @Column(name = "room_price", columnDefinition = "integer default 1000")
    private int roomPrice;

    @Column(name = "transaction_id", columnDefinition = "integer default 0")
    private int transactionId;

    public int getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", bookedOn=" + bookedOn +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", roomNumbers='" + roomNumbers + '\'' +
                ", roomPrice=" + roomPrice +
                ", transactionId=" + transactionId +
                '}';
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDate bookedOn) {
        this.bookedOn = bookedOn;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}