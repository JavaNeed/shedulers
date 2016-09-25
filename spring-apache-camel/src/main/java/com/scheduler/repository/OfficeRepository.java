package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Office;

public interface OfficeRepository extends JpaRepository<Office, Long>{

}
