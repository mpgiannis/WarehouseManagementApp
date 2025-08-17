package com.mpgiannis.warehousemanagement.service;

import com.mpgiannis.warehousemanagement.dto.StockDto;
import com.mpgiannis.warehousemanagement.entity.Stock;
import java.util.List;

public interface StockService {
	
	List<Stock> findAll();
	
	public List<StockDto> StockListToDtoList(List<Stock> entitylist);

	List<Stock> findByRackId(int rackId);

}
