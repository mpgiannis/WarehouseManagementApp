package com.mpgiannis.warehousemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.ImportsExportsRepository;
import com.mpgiannis.warehousemanagement.dao.StockRepository;
import com.mpgiannis.warehousemanagement.dto.ImportsExportsDto;
import com.mpgiannis.warehousemanagement.entity.ImportsExports;
import com.mpgiannis.warehousemanagement.entity.Product;
import com.mpgiannis.warehousemanagement.entity.Racks;
import com.mpgiannis.warehousemanagement.entity.Reports;
import com.mpgiannis.warehousemanagement.entity.Stock;

@Service
public class ImportsExportsServiceImpl implements ImportsExportsService{
	
		private ImportsExportsRepository importsExportsRepository;
		@Autowired
		private ReportsService reportsService;
		@Autowired
		private ProductService productService;
		@Autowired
		private RacksService racksService;
		@Autowired
		private StockRepository stockRepository;


		@Autowired
		public ImportsExportsServiceImpl(ImportsExportsRepository theImports_ExportsRepository) {
			importsExportsRepository = theImports_ExportsRepository;
		
		}
		
		@Override
		public List<ImportsExports> findAll() {
			return importsExportsRepository.findAll();
		
		}

		@Override
		public ImportsExports findById(int theId) {
			Optional<ImportsExports> result = importsExportsRepository.findById(theId);
			ImportsExports theImports_Exports = null;
			if (result.isPresent()) {
				theImports_Exports = result.get();
			}
			return theImports_Exports;
		}
		
		
		@Override
		public ImportsExportsDto save(ImportsExportsDto theImportsExportsDto) {
			
			Reports report = reportsService.findById(theImportsExportsDto.getReportId());
			ImportsExportsDto imex =null;
			if(report!=null) {
				Product product = productService.findById(theImportsExportsDto.getProductId());
				if(product!=null) {
					Racks rack = racksService.findById(theImportsExportsDto.getRackId());
					    
					if(rack!=null) {
						importsExportsRepository.save(dtoToEntity(theImportsExportsDto));
						  int delta = theImportsExportsDto.getAmount();
			                if ("EXPORT".equalsIgnoreCase(report.getType())) {
			                    delta = -delta;
			                }

			                // 4. Update or create Stock entry
			                Stock stock = stockRepository.findByProduct(product)
			                    .orElseGet(() -> new Stock(product, 0, rack));  // If no stock entry exists, create one

			                stock.setQuantity(stock.getQuantity() + delta);

			                // 5. Save updated stock
			                stockRepository.save(stock);
					    imex=theImportsExportsDto;
					}
					else {
						
						throw new RuntimeException("Did not found rack with this id :"+ theImportsExportsDto.getRackId());
					   }	
				}
				else 
				{
					throw new RuntimeException("Did not found product with this id :"+ theImportsExportsDto.getProductId());
				}
				
			}
			else 
			{
				throw new RuntimeException("Did not found report with this id :"+ theImportsExportsDto.getReportId());
			}
			return imex;
		}
		
		
		@Override
		public ImportsExportsDto update(ImportsExportsDto newImportsExportsDto) {
			
			Optional<ImportsExports> oldImportsExports = importsExportsRepository.findById(newImportsExportsDto.getId());
			
			
			ImportsExports updateImportsExports = null;
			
	         if(oldImportsExports.isPresent()) {
				
				
	        	 updateImportsExports=importsExportsRepository.save(dtoToEntity(newImportsExportsDto));
	            
			}
			else {
				throw new RuntimeException("Did not found ImportsExports id :"+ newImportsExportsDto.getId());
				
			}
			
	         return new ImportsExportsDto(updateImportsExports);
			
		}

		@Override
		public void deleteById(int theId) {
			importsExportsRepository.deleteById(theId);
		}

		@Override
		public List<ImportsExports> findbyProduct_id(int product_id) {
			List<ImportsExports> result = importsExportsRepository.findByProduct_id(product_id);
			if (result!=null) {
				return result;
					
			}
			else {
				
				throw new RuntimeException("Did not find Date_rep - " + product_id);
			}
		}
		
		
		
		
		public ImportsExports dtoToEntity(ImportsExportsDto importexportDto) {
			
			ImportsExports imex = new ImportsExports();
			if(importexportDto.getId()!=null)
				imex.setId(importexportDto.getId());
			if(reportsService.findById(importexportDto.getReportId())!=null)
				imex.setReport(reportsService.findById(importexportDto.getReportId()));
			if(productService.findById(importexportDto.getProductId())!=null)
				imex.setProduct(productService.findById(importexportDto.getProductId()));
			if(racksService.findById(importexportDto.getRackId())!=null)
				imex.setRack(racksService.findById(importexportDto.getRackId()));
			if(importexportDto.getAmount()!=null)
				imex.setAmount(importexportDto.getAmount());
			return imex;
		}
		
		
		
		public List<ImportsExportsDto> ImportsExportsListToDtoList(List<ImportsExports> entitylist)
		{
			List<ImportsExportsDto> listDto = new ArrayList<>();
			for(ImportsExports imex : entitylist) {
				ImportsExportsDto temp = new ImportsExportsDto(imex);
				listDto.add(temp);			
			   }

			return listDto;
		 }

		
		/**
		 * 	@Override
		public List<ImportsExports> findapothema(String date, int productid) {
			List<ImportsExports> result = importsExportsRepository.findByDateAndProductId(date, productid);
			
			List<ImportsExports> theImportsExports = null;
			
			if (result!=null) {
				theImportsExports =result;
					
			}
			else {
				
				throw new RuntimeException("Did not find theImportsExports - ");
			}
			
			return theImportsExports;
			
		
		

		}
		
		
		@Override
		public List<ImExDto> searchImEx(ImExSearch search) {
			// TODO Auto-generated method stub
			return importsExportsRepository.searchImEx(search);
		}
		 */
	

		

}
