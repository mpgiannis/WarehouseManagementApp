package com.mpgiannis.warehousemanagement.service;

import java.util.List;

import com.mpgiannis.warehousemanagement.dto.ProductDto;
import com.mpgiannis.warehousemanagement.entity.Product;

public interface ProductService {
	

	public List<Product> findAll();
	
	public Product findById(int theId);
	
	public ProductDto save(ProductDto theProductDto);
	
	public void deleteById(int theId);
	
	public ProductDto update(ProductDto newProduct);
	
	public List<ProductDto> ProductListToDtoList(List<Product> entitylist);

}
