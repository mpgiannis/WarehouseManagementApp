package com.mpgiannis.warehousemanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpgiannis.warehousemanagement.entity.Product;
import com.mpgiannis.warehousemanagement.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    
	Optional<Stock> findByProduct(Product product);

	List<Stock> findByRack_Id(int rackId);

}