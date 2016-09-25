package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
