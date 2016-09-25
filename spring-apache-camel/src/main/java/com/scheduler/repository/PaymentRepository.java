package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
