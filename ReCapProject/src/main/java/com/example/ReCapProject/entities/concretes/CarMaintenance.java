package com.example.ReCapProject.entities.concretes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_maintenances")
public class CarMaintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_maintenance_id")
	private int carMaintenanceId;
	
	@Nullable
	@Column(name = "is_in_car_maintenance", columnDefinition = "boolean default false")
	private boolean isInCarMaintenance = false;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "car_id")
	private Car car;

}
