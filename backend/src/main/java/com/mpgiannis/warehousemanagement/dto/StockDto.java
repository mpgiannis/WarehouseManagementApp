package com.mpgiannis.warehousemanagement.dto;
import com.mpgiannis.warehousemanagement.entity.Stock;

public class StockDto {
	
	private Integer id;
	
	private Integer productId;
	
	private Integer quantity;
	
	private Integer rackId;
	
	public StockDto() {}
	
	public StockDto(Stock stock) {
		
		this.id=stock.getId();
		this.productId=stock.getProduct().getId();
		this.quantity=stock.getQuantity();	
		this.rackId=stock.getRack().getId();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    public Integer getRackId() {
        return rackId;
    }

    public void setRackId(Integer rackId) {
        this.rackId = rackId;
    }
	
	
	

		
	

}
