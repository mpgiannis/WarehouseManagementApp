package com.mpgiannis.warehousemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.RacksRepository;
import com.mpgiannis.warehousemanagement.dto.RacksDto;
import com.mpgiannis.warehousemanagement.entity.Racks;
@Service
public class RacksServiceImpl implements RacksService {
	
	private RacksRepository racksRepository;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	public RacksServiceImpl(RacksRepository theRacksRepository) {
		racksRepository = theRacksRepository;
	}
	

	@Override
	public List<Racks> findAll() {
		return racksRepository.findAll();	
     }

	
	@Override
	public Racks findById(int theId) {
		Optional<Racks> result = racksRepository.findById(theId);

		Racks theRack=null;
		if (result.isPresent()) {
			
			theRack=result.get();
		}
		else {
			
			throw new RuntimeException("Did not find racks id - " + theId);
		}
		
		return theRack;
	}

	
	@Override
	public RacksDto save(RacksDto theRacksDto) {
		if( warehouseService.findById(theRacksDto.getWarehouseId())!=null){
			
			 return new RacksDto(racksRepository.save(dtoToEntity(theRacksDto)));
			
	
			 
		
		}
			
			
		
			
		
		else {
			throw new RuntimeException("Did not found warehouse with this id :"+ theRacksDto.getWarehouseId());
			}
	}

	
	@Override
	public RacksDto update(RacksDto newRackDto) {
		
		Optional<Racks> oldRack = racksRepository.findById(newRackDto.getId());
		
		Racks updateRack = null;
		
		if(oldRack.isPresent()) {
			
			
			updateRack=racksRepository.save(dtoToEntity(newRackDto));
		}
		else {
			throw new RuntimeException("Did not found rack id :"+ newRackDto.getId());
			
		}
	
		
		return new RacksDto(updateRack);

	}
	

	
	@Override
	public void deleteById(int theId) {
		racksRepository.deleteById(theId);
	}

	

	public Racks dtoToEntity(RacksDto rackDto) {
		
		Racks rack = new Racks();
		if(rackDto.getId()!=null)
			rack.setId(rackDto.getId());
		if(rackDto.getDescription()!=null)
			rack.setDescription(rackDto.getDescription());
		if(warehouseService.findById(rackDto.getWarehouseId())!=null) 
			rack.setWarehouse(warehouseService.findById(rackDto.getWarehouseId()));
		
		return rack;
	}
	
	
	public List<RacksDto> RacksListToDtoList(List<Racks> entitylist)
	{
		List<RacksDto> listDto = new ArrayList<>();
		for(Racks rack : entitylist) {
			RacksDto temp = new RacksDto(rack);
			listDto.add(temp);			
		   }

		return listDto;
	 }

}
