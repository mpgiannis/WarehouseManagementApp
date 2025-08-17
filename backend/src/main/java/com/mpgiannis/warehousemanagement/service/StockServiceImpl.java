package com.mpgiannis.warehousemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.RacksRepository;
import com.mpgiannis.warehousemanagement.dao.StockRepository;

import com.mpgiannis.warehousemanagement.dto.StockDto;

import com.mpgiannis.warehousemanagement.entity.Stock;
@Service
public class StockServiceImpl implements StockService {
	
	
	private StockRepository stockRepository;
	
	@Autowired
	public StockServiceImpl(StockRepository theStockRepository) {
		stockRepository = theStockRepository;
	}

	@Override
	public List<Stock> findAll() {
		
		return stockRepository.findAll();
	}

	@Override
	public List<StockDto> StockListToDtoList(List<Stock> entitylist) {
		List<StockDto> listDto = new ArrayList<>();
		for(Stock stock : entitylist) {
			StockDto temp = new StockDto(stock);
			listDto.add(temp);			
		   }

		return listDto;
	}
	
	public List<Stock> findByRackId(int rackId) {
	    return stockRepository.findByRack_Id(rackId); 
	}

}
