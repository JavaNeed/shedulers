package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Orderdetail;

public interface OrderdetailRepository extends JpaRepository<Orderdetail, Long>{

}
