package com.example.ReCapProject.entities.requests;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest implements Request {

	private int individualCustomerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
}
