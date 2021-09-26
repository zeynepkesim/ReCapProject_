package com.example.ReCapProject.entities.requests.corporateCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest implements Request{

	private String companyName;
	
	@Email
	private String email;
	
	private String password;
	
	@Size(min = 10, max = 10)
	private String taxNumber;
	
}
