package com.mpgiannis.warehousemanagement.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mpgiannis.warehousemanagement.entity.ImportsExports;
import com.mpgiannis.warehousemanagement.entity.Reports;

public class ReportsDto {
	
private Integer id;
	
	private LocalDate dateRep;
	
	private String type;
	
	private String descriptionReason;
	
	private String receivedDeliveredBy;
	
	private String infos;
	
	 List<ImportsExportsDto> importsExports;
	
	public ReportsDto() {}
	
	public ReportsDto(Reports reports) {
		this.id=reports.getId();
		this.dateRep=reports.getDateRep();
		this.type=reports.getType();
		this.descriptionReason=reports.getDescriptionReason();
		this.receivedDeliveredBy=reports.getReceivedDeliveredBy();
		this.infos=reports.getInfos();
		if(reports.getImportsExports()!=null) {
			List<ImportsExportsDto> importsExportsDto =new ArrayList<ImportsExportsDto>();
			for(ImportsExports imex : reports.getImportsExports()) {
				importsExportsDto.add(new ImportsExportsDto(imex));			
		    }
			this.importsExports=importsExportsDto;
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateRep() {
		return dateRep;
	}

	public void setDateRep(LocalDate dateRep) {
		this.dateRep = dateRep;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescriptionReason() {
		return descriptionReason;
	}

	public void setDescriptionReason(String descriptionReason) {
		this.descriptionReason = descriptionReason;
	}

	public String getReceivedDeliveredBy() {
		return receivedDeliveredBy;
	}

	public void setReceivedDeliveredBy(String receivedDeliveredBy) {
		this.receivedDeliveredBy = receivedDeliveredBy;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public List<ImportsExportsDto> getImportsExports() {
		return importsExports;
	}

	public void setImportsExports(List<ImportsExportsDto> importsExportsDto) {
		this.importsExports = importsExportsDto;
	}

}
