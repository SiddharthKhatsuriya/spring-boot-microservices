package com.upgrad.bookingService.dao;

import com.upgrad.bookingService.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer> {

}
