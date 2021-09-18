package com.example.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {
	
	private int userId;
	private String email;
	private String firstName;
	private String lastName;

}
