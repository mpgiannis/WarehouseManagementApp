package com.mpgiannis.warehousemanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.ReportsRepository;
import com.mpgiannis.warehousemanagement.dto.ImportsExportsDto;
import com.mpgiannis.warehousemanagement.dto.ReportsDto;
import com.mpgiannis.warehousemanagement.entity.ImportsExports;
import com.mpgiannis.warehousemanagement.entity.Reports;
@Service
public class ReportsServiceImpl implements ReportsService {

	private ReportsRepository reportsRepository;

	@Autowired
	@Lazy
	private ImportsExportsServiceImpl imex;
	@Autowired
	public ReportsServiceImpl(ReportsRepository theReportsRepository) {
		reportsRepository = theReportsRepository;
	}
	
	
	
	
	@Override
	public List<Reports> findAll() {
		// TODO Auto-generated method stub
		return reportsRepository.findAll();
	}

	@Override
	public Reports findById(int theId) {
		
       Optional<Reports> result = reportsRepository.findById(theId);
		
       Reports theReports = null;
		
		if (result.isPresent()) {
			theReports = result.get();
		}
		else {
			// we didn't find the reports
			throw new RuntimeException("Did not find reports id - " + theId);
		}
		
		return theReports;
	}

	@Override
	public ReportsDto save(ReportsDto theReportsDto) {
		 
		return new ReportsDto(reportsRepository.save(dtoToEntity(theReportsDto)));
	}
	

	@Override
	public ReportsDto update(ReportsDto newReportsDto) {
        Optional<Reports> oldReports = reportsRepository.findById(newReportsDto.getId());
		
		
        Reports updateReports = null;
		
		if(oldReports.isPresent()) {
			
			
			updateReports=reportsRepository.save(dtoToEntity(newReportsDto));
		}
		else {
			throw new RuntimeException("Did not found Reports id :"+ newReportsDto.getId());
			
		}
	
		
		return new ReportsDto(updateReports);	
	}


	
	
	@Override
	public void deleteById(int theId) {
		reportsRepository.deleteById(theId);
		
	}

	
	
	/**
	 * 
	@Override
	public List<ReportsDto> findbyDateRep(LocalDate dateRep) {
   
		   List<Reports> result = reportsRepository.findByDateRep(dateRep);
			
			if (result!=null) {
				return ReportsListToDtoList(result);
					
			}
			else {
				
				throw new RuntimeException("Did not find Date_rep - " + dateRep);
			}

	}
	 */
	
	

	public Reports dtoToEntity(ReportsDto reportsDto) {
		
		Reports reports = new Reports();
		if(reportsDto.getId()!=null)
			reports.setId(reportsDto.getId());
		if(reportsDto.getDateRep()!=null)
			reports.setDateRep(reportsDto.getDateRep());
		if(reportsDto.getType()!=null)
			reports.setType(reportsDto.getType());
		if(reportsDto.getDescriptionReason()!=null)
			reports.setDescriptionReason(reportsDto.getDescriptionReason());
		if(reportsDto.getReceivedDeliveredBy()!=null)
			reports.setReceivedDeliveredBy(reportsDto.getReceivedDeliveredBy());
		if(reportsDto.getInfos()!=null)
			reports.setInfos(reportsDto.getInfos());
		if(reportsDto.getImportsExports()!=null) {
			List<ImportsExports> list = new ArrayList<>();
			for(ImportsExportsDto imexDto:reportsDto.getImportsExports()) {
				ImportsExports temp = imex.dtoToEntity(imexDto);
				list.add(temp);
			}
		
			reports.setImportsExports(list);}
		
		return reports;
	}
	
	
	
	public List<ReportsDto> ReportsListToDtoList(List<Reports> entitylist)
	{
		List<ReportsDto> listDto = new ArrayList<>();
		for(Reports reports : entitylist) {
			ReportsDto temp = new ReportsDto(reports);
			listDto.add(temp);			
		   }

		return listDto;
	 }


}
