package com.example.ReCapProject.entities.requests.corporateCustomer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.ReCapProject.core.entities.abstracts.Request;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest implements Request {

	@NotNull
	private int corporateCustomerId;
	
	@NotNull
	@NotBlank
	private String companyName;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min = 10, max = 10)
	private String taxNumber;
	
}
