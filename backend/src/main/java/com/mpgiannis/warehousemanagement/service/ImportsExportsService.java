package com.mpgiannis.warehousemanagement.service;

import java.util.List;

import com.mpgiannis.warehousemanagement.dto.ImportsExportsDto;
import com.mpgiannis.warehousemanagement.entity.ImportsExports;

public interface ImportsExportsService {
	
    public List<ImportsExports> findAll();
	
	public ImportsExports findById(int theId);
	
	public ImportsExportsDto save(ImportsExportsDto theImportsExportsDto);
	
	public void deleteById(int theId);

	public ImportsExportsDto update(ImportsExportsDto newImportsExports);
	
	public List<ImportsExports> findbyProduct_id(int productid);
	
	public List<ImportsExportsDto> ImportsExportsListToDtoList(List<ImportsExports> entitylist);
	
	//public List<ImportsExports> findapothema(String date,int productId);
	
	//public List<ImExDto> searchImEx(ImExSearch search);
	public List<ImportsExports> findByRackId(int rackId);

}
