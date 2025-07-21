package com.mpgiannis.warehousemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpgiannis.warehousemanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
