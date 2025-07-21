package com.mpgiannis.warehousemanagement.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpgiannis.warehousemanagement.entity.Reports;

public interface ReportsRepository  extends JpaRepository<Reports, Integer>{
	List<Reports> findByDateRep(LocalDate dateRep);
}
