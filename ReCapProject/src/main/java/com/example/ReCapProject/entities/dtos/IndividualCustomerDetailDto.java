package com.example.ReCapProject.entities.dtos;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDetailDto implements Dto {

	private int userId;
	private String nationalIdNumber;
	private String firstName;
	private String lastName;
	private String email;
	
}
