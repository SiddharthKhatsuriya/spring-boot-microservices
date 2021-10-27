package com.upgrad.transactionService.dto;

public class TransactionDTO {

  private int transactionId;

  private String paymentMode;

  private int bookingId;

  private String upiId;

  private String cardNumber;

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public String getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public String getUpiId() {
    return upiId;
  }

  public void setUpiId(String upiId) {
    this.upiId = upiId;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public String toString() {
    return "TransactionDTO{" +
            "transactionId=" + transactionId +
            ", paymentMode='" + paymentMode + '\'' +
            ", bookingId=" + bookingId +
            ", upiId=" + upiId +
            ", cardNumber='" + cardNumber + '\'' +
            '}';
  }
}
