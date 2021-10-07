package com.example.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "damageRecords", "maintenances", "rentals"})
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int carId;
	
	@Nullable
	@Column(name = "is_available", columnDefinition = "boolean default true")
	private boolean isAvailable = true;
	
	@NotNull
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "min_findex_point")
	private double minFindexPoint;
	
	@Column(name = "current_kilometer")
	private long currentKilometer;
		
	
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "color_id")
	private Color color;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;
		
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<CarImage> carImages;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<DamageRecord> damageRecords;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Maintenance> maintenances;
		
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
}
