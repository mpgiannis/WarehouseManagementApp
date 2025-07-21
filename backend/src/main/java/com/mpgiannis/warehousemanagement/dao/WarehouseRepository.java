package com.mpgiannis.warehousemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mpgiannis.warehousemanagement.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
