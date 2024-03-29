package com.example.ReCapProject.entities.dtos;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDetailDto implements Dto {

	private int userId;
	private String cardBeholderName;
	private String creditCardNumber;
	private String cvvCode;
	private String expireDate;
	
}
