package com.example.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.ReCapProject.core.entities.concretes.User;
import com.example.ReCapProject.entities.abstracts.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rentals"})
@Table(name = "application_users")
public class ApplicationUser extends User{

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Customer customer;
	
	@OneToMany(mappedBy = "user")
	private List<Rental> rentals;
	
}
