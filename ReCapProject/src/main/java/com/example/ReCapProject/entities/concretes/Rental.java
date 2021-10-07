package com.example.ReCapProject.entities.concretes;

import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.example.ReCapProject.entities.abstracts.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rentals")
@JsonIgnoreProperties({"hibenateLazyInitializer", "handler", "invoice"})
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;
	
	@Nullable
	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "rental_price")
	private double rentalPrice;
	
	@NotNull
	@Column(name = "pick_up_kilometer")
	private long pickUpKilometer;
	
	@Nullable
	@Column(name = "return_kilometer")
	private long returnKilometer;
	
	@Column(name = "is_payed")
	private boolean isPayed = false;
	
	@Nullable
	@Column(name = "is_returned", columnDefinition = "boolean default true")
	private boolean isReturned = true;
	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private ApplicationUser user;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;
	
	
	@OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
	private List<AdditionalService> additionalServices;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
		
}
