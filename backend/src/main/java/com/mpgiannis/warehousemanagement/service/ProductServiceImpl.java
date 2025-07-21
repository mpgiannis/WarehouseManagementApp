package com.mpgiannis.warehousemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpgiannis.warehousemanagement.dao.ProductRepository;
import com.mpgiannis.warehousemanagement.dto.ProductDto;
import com.mpgiannis.warehousemanagement.entity.Product;
@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRepository = theProductRepository;
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int theId) {
		Optional<Product> result = productRepository.findById(theId);
		Product theProduct = null;
		if (result.isPresent()) {
			theProduct = result.get();
		}
		return theProduct;
	}

	@Override
	public ProductDto save(ProductDto theProductDto) {
		productRepository.save(dtoToEntity(theProductDto));
		return theProductDto;
	}
	
	

	@Override
	public ProductDto update(ProductDto newProductDto) {
		
	Optional<Product> oldProduct = productRepository.findById(newProductDto.getId());
		
		
	Product updateProduct = null;
		
		if(oldProduct.isPresent()) {
			
			
			updateProduct=productRepository.save(dtoToEntity(newProductDto));
		}
		else {
			throw new RuntimeException("Did not found product id :"+ newProductDto.getId());
			
		}
	
		return new ProductDto(updateProduct);

	}

	@Override
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
	}
	

	
    public Product dtoToEntity(ProductDto productDto) {
		
    	Product product = new Product();
    	
		if(productDto.getId()!=null) {
    	product.setId(productDto.getId());}
		if(productDto.getName()!=null)
    	product.setName(productDto.getName());
		if(productDto.getDescription()!=null)
    	product.setDescription(productDto.getDescription());
		if(productDto.getBarcode()!=null)
    	product.setBarcode(productDto.getBarcode());
		if(productDto.getMeasureUnit()!=null)
    	product.setMeasure_unit(productDto.getMeasureUnit());
    	
		
		return product;
	}

	
	public List<ProductDto> ProductListToDtoList(List<Product> entitylist)
	{
		List<ProductDto> listDto = new ArrayList<>();
		for(Product product : entitylist) {
			ProductDto temp = new ProductDto(product);
			listDto.add(temp);			
		   }

		return listDto;
	 }


}
