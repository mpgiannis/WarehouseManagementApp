package com.mpgiannis.warehousemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.mpgiannis.warehousemanagement.dto.ReportsDto;
import com.mpgiannis.warehousemanagement.entity.Reports;

public interface ReportsService {
	
	public List<Reports> findAll();
	
	public Reports findById(int theId);
	
	public ReportsDto save(ReportsDto theReportsDto);
	
	public void deleteById(int theId);
	
	public ReportsDto update(ReportsDto newReports);
	
	//public List<ReportsDto> findbyDateRep(LocalDate dateRep);
	
	public List<ReportsDto> ReportsListToDtoList(List<Reports> entitylist);

}
