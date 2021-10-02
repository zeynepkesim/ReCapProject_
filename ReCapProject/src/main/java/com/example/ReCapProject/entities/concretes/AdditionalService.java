package com.example.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "additional_services")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rental"})
public class AdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "additional_service_id")
	private int additionalServiceId;
	
	@Column(name = "additional_service_name")
	private String additionalServiceName;
	
	@Column(name = "additional_service_fee")
	private double additionalServiceFee;

	@OneToMany(mappedBy = "additionalService", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Rental> rental;
	
}
