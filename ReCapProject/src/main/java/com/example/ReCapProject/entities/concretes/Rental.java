package com.example.ReCapProject.entities.concretes;

import java.util.Date;

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
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;
	
	@Column(name = "rent_date")
	private Date rentDate;
	
	@Nullable
	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "is_returned", columnDefinition = "boolean default false")
	private boolean isReturned;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private ApplicationUser user;
	
}
