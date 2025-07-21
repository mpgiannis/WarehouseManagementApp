package com.mpgiannis.warehousemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.WarehouseRepository;
import com.mpgiannis.warehousemanagement.dto.RacksDto;
import com.mpgiannis.warehousemanagement.dto.WarehouseDto;
import com.mpgiannis.warehousemanagement.entity.Racks;
import com.mpgiannis.warehousemanagement.entity.Warehouse;
@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	private WarehouseRepository warehouseRepository;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	public WarehouseServiceImpl(WarehouseRepository theWarehouseRepository) {
		warehouseRepository = theWarehouseRepository;
	}

	@Override
	public List<Warehouse> findAll() {
		// TODO Auto-generated method stub
		return warehouseRepository.findAll();
	}

	@Override
	public Warehouse findById(int theId) {
		Optional<Warehouse> result = warehouseRepository.findById(theId);
		
		Warehouse theWarehouse = null;
		
		if (result.isPresent()) {
			theWarehouse = result.get();
		}
		else {
			// we didn't find the stores
			throw new RuntimeException("Did not find Warehouse id - " + theId);
		}
		
		return theWarehouse;
	}

	@Override
	public WarehouseDto save(WarehouseDto theWarehouseDto) {
		return new WarehouseDto(warehouseRepository.save(dtoToEntity(theWarehouseDto)));
	}

	@Override
	public void deleteById(int theId) {
		warehouseRepository.deleteById(theId);
		
	}

	@Override
	public WarehouseDto update(WarehouseDto newWarehouseDto) {
		Optional<Warehouse> oldWarehouse = warehouseRepository.findById(newWarehouseDto.getId());
		
		
		Warehouse updateWarehouse = null;
		
		if(oldWarehouse.isPresent()) {
			
			
			updateWarehouse=warehouseRepository.save(dtoToEntity(newWarehouseDto));
		}
		else {
			throw new RuntimeException("Did not found Warehouse id :"+ newWarehouseDto.getId());
			
		}
	
		
		return new WarehouseDto(updateWarehouse);
	}
	
	

	public Warehouse dtoToEntity(WarehouseDto warehouseDto) {
		
		Warehouse warehouse = new Warehouse();
		if(warehouseDto.getId()!=null)
			warehouse.setId(warehouseDto.getId());
		if(warehouseDto.getName()!=null)
			warehouse.setName(warehouseDto.getName());
		if(warehouseDto.getDescription()!=null)
			warehouse.setDescription(warehouseDto.getDescription());
		if(warehouseDto.getImageUrl()!=null)
			warehouse.setImageUrl(warehouseDto.getImageUrl());
		if(warehouseDto.getRacks()!=null) {
			List<Racks> a = new ArrayList<>();
			for(RacksDto b : warehouseDto.getRacks()) {
				
				Racks rack = new Racks();
				if(b.getId()!=null)
					rack.setId(b.getId());
				if(b.getDescription()!=null)
					rack.setDescription(b.getDescription());
				if(warehouseService.findById(b.getWarehouseId())!=null) 
					rack.setWarehouse(warehouseService.findById(b.getWarehouseId()));
				a.add(rack);
			}
			warehouse.setRacks(a);
			
		}
		
		return warehouse;
	}

	@Override
	public List<WarehouseDto> WarehouseListToDtoList(List<Warehouse> entitylist) {
		List<WarehouseDto> listDto = new ArrayList<>();
		for(Warehouse warehouse : entitylist) {
			WarehouseDto temp = new WarehouseDto(warehouse);
			listDto.add(temp);			
		   }

		return listDto;
	}

}
