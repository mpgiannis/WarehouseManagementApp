package com.mpgiannis.warehousemanagement.dto;

import com.mpgiannis.warehousemanagement.entity.ImportsExports;

public class ImportsExportsDto {
	
Integer id;
	
	Integer reportId;
	
	Integer productId;
	
	String productName;
	
	Integer rackId;
	
	String rackDescription;
	
	Integer amount;
	
	public ImportsExportsDto() {}

	public ImportsExportsDto(ImportsExports importexport) {
		
		this.id =importexport.getId();
		this.reportId = importexport.getReport().getId();
		this.productId = importexport.getProduct().getId();
		this.productName=importexport.getProduct().getName();
		this.rackId =importexport.getRack().getId();
		this.rackDescription=importexport.getRack().getDescription();
		this.amount =importexport.getAmount();
	}

	public String getRackDescription() {
		return rackDescription;
	}

	public void setRackDescription(String rackDescription) {
		this.rackDescription = rackDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getRackId() {
		return rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

}
