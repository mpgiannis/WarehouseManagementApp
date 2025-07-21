package com.mpgiannis.warehousemanagement.service;

import java.util.List;

import com.mpgiannis.warehousemanagement.dto.RacksDto;
import com.mpgiannis.warehousemanagement.entity.Racks;

public interface RacksService {
	
	public List<Racks> findAll();
	
	public Racks findById(int theId);
	
	public RacksDto save(RacksDto theRacksDto);
	
	public void deleteById(int theId);
	
	public RacksDto update(RacksDto newRackDto);
	
	public List<RacksDto> RacksListToDtoList(List<Racks> entitylist);
	
	public Racks dtoToEntity(RacksDto rackDto);

}
