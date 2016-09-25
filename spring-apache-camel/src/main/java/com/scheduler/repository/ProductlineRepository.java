package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Productline;

public interface ProductlineRepository extends JpaRepository<Productline, Long>{

}
