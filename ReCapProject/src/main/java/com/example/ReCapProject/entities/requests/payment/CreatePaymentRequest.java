package com.example.ReCapProject.entities.requests.payment;

import javax.validation.constraints.NotBlank;

import com.example.ReCapProject.core.entities.abstracts.Request;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements Request {

	@NotNull
	private int rentalId;
	
	@NotNull
	private int userId;
	
	@Nullable
	private boolean isSaved = false;
	
	@NotNull
	@NotBlank
	private String cardBeholderName;
	
	@NotNull
	@NotBlank
	private String cardNumber;
	
	@NotNull
	@NotBlank
	private String cvvCode;
	
	@NotNull
	@NotBlank
	private String expireDate;
	
}
