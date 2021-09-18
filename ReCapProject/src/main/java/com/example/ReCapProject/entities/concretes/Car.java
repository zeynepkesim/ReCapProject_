package com.example.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rentals"})
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "description")
	private String description;
	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;
	
}
