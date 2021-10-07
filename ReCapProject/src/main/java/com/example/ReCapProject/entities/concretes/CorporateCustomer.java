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
@Table(name = "corporate_customers")
@EqualsAndHashCode(callSuper = false)
public class CorporateCustomer extends Customer {

	@NotNull
	@Column(name = "tax_number")
	private String taxNumber;
	
	@NotNull
	@Column(name = "company_name")
	private String companyName;
	
}
