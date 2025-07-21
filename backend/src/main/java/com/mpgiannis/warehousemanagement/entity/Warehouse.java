package com.mpgiannis.warehousemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="warehouse")
public class Warehouse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="imageUrl")
	private String imageUrl;
	@OneToMany(mappedBy = "warehouse",orphanRemoval = true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Racks> racks = new ArrayList<>();

	public Warehouse() {}



	public Warehouse(String description) {

		this.description = description;
	
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
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


	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	public List<Racks> getRacks() {
		return racks;
	}



	public void setRacks(List<Racks> racks) {
		this.racks = racks;
	}



	@Override
	public String toString() {
		return "Stores [id = " + id + ", name = "+name+ ", description = " + description +  "]";
	}
	
	
	public void add(Racks tempRack) {
		
		if(racks==null) {
			
			racks= new ArrayList<>();
			
		}
		racks.add(tempRack);
		tempRack.setWarehouse(this);
	}

}
