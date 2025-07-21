package com.mpgiannis.warehousemanagement.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity(name="reports")
@Table(name="reports")
public class Reports {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="date_rep")
	private LocalDate dateRep;
	
	@Column(name="type")
	private String type;
	
	@Column(name="description_reason")
	private String descriptionReason;
	
	@Column(name="received_delivered_by")
	private String receivedDeliveredBy;
	
	@Column(name="infos")
	private String infos;
	@OneToMany(mappedBy = "report", fetch=FetchType.LAZY)
	private List<ImportsExports> importsExports;
	
	public Reports() {}

	public Reports(LocalDate date_rep,String type, String description_reason, String received_delivered_by, String infos) {
		
		this.dateRep =  date_rep;
		this.type = type;
		this.descriptionReason = description_reason;
		this.receivedDeliveredBy = received_delivered_by;
		this.infos = infos;
	
	}

	public LocalDate getDateRep() {
		return dateRep;
	}

	public void setDateRep(LocalDate dateRep) {
		this.dateRep = dateRep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescriptionReason() {
		return descriptionReason;
	}

	public void setDescriptionReason(String description_reason) {
		this.descriptionReason = description_reason;
	}

	public String getReceivedDeliveredBy() {
		return receivedDeliveredBy;
	}

	public void setReceivedDeliveredBy(String received_delivered_by) {
		this.receivedDeliveredBy = received_delivered_by;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}
	
	public List<ImportsExports> getImportsExports() {
		
		return importsExports;
	}

	public void setImportsExports(List<ImportsExports> importsExports) {
		this.importsExports = importsExports;
	}

	@Override
	public String toString() {
		return "Reports [id=" + id + ", date_rep=" + dateRep + ", type=" + type + ", description_reason="
				+ descriptionReason + ", received_delivered_by=" + receivedDeliveredBy + ", infos=" + infos
				+ ", imports_exports=" + importsExports + "]";
	}

	

}
