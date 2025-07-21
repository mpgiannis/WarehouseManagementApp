package com.mpgiannis.warehousemanagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.mpgiannis.warehousemanagement.entity.ImportsExports;
import com.mpgiannis.warehousemanagement.entity.Racks;

public class RacksDto {
	
	Integer id;
	
	String description;
	
	Integer warehouseId;
	
	List<ImportsExportsDto> importsExports;
	
	public RacksDto() {}
	
	public RacksDto(Racks rack) {
		
		this.id=rack.getId();
		this.description=rack.getDescription();
		this.warehouseId=rack.getWarehouse().getId();
		if(rack.getImportsExports()!=null) {
			List<ImportsExportsDto> importsExportsDto =new ArrayList<ImportsExportsDto>();
			for(ImportsExports imex : rack.getImportsExports()) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer storeId) {
		this.warehouseId = storeId;
	}

	public List<ImportsExportsDto> getImportsExports() {
		return importsExports;
	}

	public void setImportsExports(List<ImportsExportsDto> importsExportsDto) {
		this.importsExports = importsExportsDto;
	}

}
