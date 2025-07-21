package com.mpgiannis.warehousemanagement.controllers;

//import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpgiannis.warehousemanagement.dto.ReportsDto;
import com.mpgiannis.warehousemanagement.entity.Reports;
import com.mpgiannis.warehousemanagement.service.ReportsService;
@RestController
@RequestMapping("/reports")
public class ReportsRestController {
	
private ReportsService reportsService;
	
	@Autowired
	public ReportsRestController(ReportsService theReportsService) {
		reportsService = theReportsService;
	}
	
	@GetMapping("")
	public List<ReportsDto> findAll() {
		return reportsService.ReportsListToDtoList(reportsService.findAll());
	}

	
	
	@GetMapping("/{reportsId}")
	public ReportsDto getReports(@PathVariable int reportsId) {		
		Reports theReports = reportsService.findById(reportsId);
		
		if (theReports == null) {
			throw new NoFoundException("Reports id not found - " + reportsId);
		}
		
		return new ReportsDto(theReports);
	}
	

	
		@PostMapping("")
		public ReportsDto addReports(@RequestBody ReportsDto theReportsDto) {
			theReportsDto.setId(0);
			return reportsService.save(theReportsDto);}
		
	
		
		@PutMapping("")
		public ReportsDto updateReports(@RequestBody ReportsDto theReports) {
			
			return reportsService.update(theReports);
		}

		
		@DeleteMapping("/{reportsId}")
		public String deleteReports(@PathVariable int reportsId) {
			Reports tempReports = reportsService.findById(reportsId);
			
			if (tempReports == null) {
				throw new NoFoundException("Reports id not found - " + reportsId);
			}
			
			reportsService.deleteById(reportsId);
			return "Deleted Reports id - " + reportsId;
		}
		
		/**
		 * 
		@GetMapping("/reportsdate/{dateRep}")
		public List<ReportsDto> findDateRep(@PathVariable String dateRep) {
			  
			   LocalDate localdate =LocalDate.parse(dateRep).plusDays(1);
			   List<ReportsDto> tempReports = reportsService.findbyDateRep(localdate);
				
				if (tempReports == null) {
					throw new NoFoundException("Reports not found - " + dateRep);
				}
			
			
				return tempReports;
		
		}
		 */
		
		

}
