package com.mpgiannis.warehousemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpgiannis.warehousemanagement.dto.StockDto;
import com.mpgiannis.warehousemanagement.entity.Stock;
import com.mpgiannis.warehousemanagement.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockRestController {
	
	private StockService stockService;
	@Autowired
	public StockRestController(StockService theProductService) {
		stockService = theProductService;
	}
	
	
	@GetMapping("")
	public List<StockDto> findAll() {
		return stockService.StockListToDtoList(stockService.findAll());
	}
	@GetMapping("/rack/{rackId}")
	public List<StockDto> getStockByRack(@PathVariable int rackId) {
	    List<Stock> stocks = stockService.findByRackId(rackId);
	    return stockService.StockListToDtoList(stocks);
	}


}
