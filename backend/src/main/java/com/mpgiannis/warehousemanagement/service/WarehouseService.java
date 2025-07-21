package com.mpgiannis.warehousemanagement.service;

import java.util.List;

import com.mpgiannis.warehousemanagement.dto.WarehouseDto;
import com.mpgiannis.warehousemanagement.entity.Warehouse;

public interface WarehouseService {

	public List<Warehouse> findAll();
	
	public Warehouse findById(int theId);
	
	public WarehouseDto save(WarehouseDto theWarehouse);
	
	public void deleteById(int theId);
	
	public WarehouseDto update(WarehouseDto newWarehouse);
	
	public List<WarehouseDto> WarehouseListToDtoList(List<Warehouse> entitylist);
	
	//public boolean existsbyId(Integer id);
}
