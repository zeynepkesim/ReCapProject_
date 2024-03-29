package com.example.ReCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.ReCapProject.entities.abstracts.Customer;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
@EqualsAndHashCode(callSuper = false)
public class IndividualCustomer extends Customer {

	@NotNull
	@Column(name = "national_id_number")
	private String nationalIdNumber;
	
	@NotNull
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull
	@Column(name = "last_name")
	private String lastName;
		
}
