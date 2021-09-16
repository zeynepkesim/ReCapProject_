package com.example.ReCapProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "model_year")
	private int modelYear;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
}
