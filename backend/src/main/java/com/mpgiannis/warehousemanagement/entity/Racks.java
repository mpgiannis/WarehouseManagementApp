package com.mpgiannis.warehousemanagement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="racks")
public class Racks {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="description")
	private String description;
	@ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name = "warehouse_id" )
	private Warehouse warehouse;
	@OneToMany(mappedBy = "rack", fetch=FetchType.LAZY)
	private List<ImportsExports> importsExports;
	
	public Racks() {}


	public Racks(String description) {
		
		this.description = description;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	
	public Warehouse getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	
	public List<ImportsExports> getImportsExports() {
		return importsExports;
	}


	public void setImportsExports(List<ImportsExports> importsExports) {
		this.importsExports = importsExports;
	}


	@Override
	public String toString() {
		return "Racks [id=" + id + ", description=" + description + ", warehouse=" + warehouse + ", importsExports="
				+ importsExports + "]";
	}

}
