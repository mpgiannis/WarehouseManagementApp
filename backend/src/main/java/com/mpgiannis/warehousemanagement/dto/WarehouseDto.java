package com.mpgiannis.warehousemanagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.mpgiannis.warehousemanagement.entity.Racks;
import com.mpgiannis.warehousemanagement.entity.Warehouse;

public class WarehouseDto {

	

	Integer id;
	
	String name;
	
	String description;
	
	String imageUrl;
	
	List<RacksDto> racks ;
	
	public WarehouseDto() {}


	public WarehouseDto(Warehouse warehouse) {
		
		this.id =warehouse.getId();
		this.name = warehouse.getName();
		this.description = warehouse.getDescription();
		this.imageUrl=warehouse.getImageUrl();
		List<RacksDto> racksDto = new ArrayList<RacksDto>();
		for(Racks rack : warehouse.getRacks()) {
			racksDto.add(new RacksDto(rack));			
		   }
		this.racks=racksDto;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<RacksDto> getRacks() {
		return racks;
	}

	public void setRacks(List<RacksDto> racksDto) {
		this.racks = racksDto;
	}
	
}
