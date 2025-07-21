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

import com.mpgiannis.warehousemanagement.dto.ProductDto;
import com.mpgiannis.warehousemanagement.entity.Product;
import com.mpgiannis.warehousemanagement.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	private ProductService productService;
	@Autowired
	public ProductRestController(ProductService theProductService) {
		productService = theProductService;
	}
	
	
	@GetMapping("")
	public List<ProductDto> findAll() {
		return productService.ProductListToDtoList(productService.findAll());
	}

	
	
	@GetMapping("/{productId}")
	public ProductDto getProduct(@PathVariable int productId) {
		
		Product theProduct = productService.findById(productId);
		ProductDto a =null;
		
		if (theProduct == null) 
		
		{
			throw new NoFoundException("Product id not found - " + productId);
		}
		else {a=new ProductDto(theProduct);}
		
		
		
		
		return  a;
	}
	

	
	@PostMapping("")
	public ProductDto addProduct(@RequestBody ProductDto theProductDto) {
		
		//just in case they pass an id in JSON set id to 0 to force a save of new item instead of update
		
		theProductDto.setId(0);
		
		return productService.save(theProductDto);
	}
	
	
	@PutMapping("")
	public ProductDto updateProduct(@RequestBody ProductDto theProduct) {
	
		return productService.update(theProduct);
	}
	
	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		
		Product tempProduct = productService.findById(productId);
		
		// throw exception if null
		
		if (tempProduct == null) {
			throw new NoFoundException("Employee id not found - " + productId);
		}
		
		productService.deleteById(productId);
		
		return "Deleted product id - " + productId;
	}

}
