package com.mpgiannis.warehousemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpgiannis.warehousemanagement.entity.ImportsExports;

public interface ImportsExportsRepository extends JpaRepository<ImportsExports, Integer> {

	List<ImportsExports> findByProduct_id(int product_id);
	
	List<ImportsExports> findByRackId(int rackId);
}
