package com.mpgiannis.warehousemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity(name="imports_exports")
@Table(name="imports_exports")
public class ImportsExports {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="reports_id")
	private Reports report;
	
	@ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="rack_id")
	private Racks rack;
	
	@Column(name="amount")
	private int amount;
			


	public ImportsExports() {}
	
	
	
	public ImportsExports(Reports report, Product product, Racks rack, int amount) {
	
		this.report = report;
		this.product = product;
		this.rack = rack;
		this.amount = amount;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public Reports getReport() {
		return report;
	}
	
	
	
	public void setReport(Reports report) {
		this.report = report;
	}
	
	
	//@JsonIgnore
	public Product getProduct() {
		return product;
	}
	
	
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	//@JsonIgnore
	public Racks getRack() {
		return rack;
	}
	
	
	
	public void setRack(Racks rack) {
		this.rack = rack;
	}
	
	
	
	public int getAmount() {
		return amount;
	}
	
	
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	@Override
	public String toString() {
		return "ImportsExports [id=" + id + ", report=" + report + ", product=" + product + ", rack=" + rack
				+ ", amount=" + amount + "]";
	}




}
