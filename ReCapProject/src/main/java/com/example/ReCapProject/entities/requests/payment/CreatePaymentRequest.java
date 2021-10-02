package com.example.ReCapProject.entities.requests.payment;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

	@NotNull
	@NotBlank
	private String cardBeholderName;
	
	@NotNull
	@NotBlank
	private String carNumber;
	
	@NotNull
	@NotBlank
	private String cvvCode;
	
	@NotNull
	@NotBlank
	private String expireDate;
	
	@NotNull
	private double price;
	
}
