package com.mpgiannis.warehousemanagement.controllers;

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

import com.mpgiannis.warehousemanagement.dto.WarehouseDto;
import com.mpgiannis.warehousemanagement.entity.Warehouse;
import com.mpgiannis.warehousemanagement.service.WarehouseService;
@RestController
@RequestMapping("/warehouse")
public class WarehouseRestController {
	
private WarehouseService warehouseService;
	
	@Autowired
	public WarehouseRestController(WarehouseService theWarehouseService) {
		warehouseService = theWarehouseService;
		
	}
	

	@GetMapping("")
	public List<WarehouseDto> findAll() {
		
		return  warehouseService.WarehouseListToDtoList(warehouseService.findAll());
	}

	
	
	@GetMapping("/{storesId}")
	public WarehouseDto getStores(@PathVariable int warehouseId) {
		
		Warehouse theWarehouse = warehouseService.findById(warehouseId);
		
		if (theWarehouse == null) {
			throw new NoFoundException("Warehouse id not found - " + warehouseId);
		}
		
		return new WarehouseDto(theWarehouse);
	}
	
	
	
		@PostMapping("")
		public WarehouseDto addWarehouse(@RequestBody WarehouseDto theWarehouseDto) {
			
			theWarehouseDto.setId(0);
			
			return warehouseService.save(theWarehouseDto);}
		
		
		@PutMapping("")
		public WarehouseDto updateWarehouse(@RequestBody WarehouseDto theWarehouse) {
			
			return warehouseService.update(theWarehouse);
			
			
		}
		
		
		@DeleteMapping("/{warehouseId}")
		public String deleteWarehouse(@PathVariable int warehouseId) {
			
			Warehouse tempWarehouse = warehouseService.findById(warehouseId);
			
			
			if (tempWarehouse == null) {
				throw new NoFoundException("Warehouse id not found - " + warehouseId);
			}
			
			warehouseService.deleteById(warehouseId);
		
			return "Deleted Warehouse with id - " + warehouseId;
		}
	

}
