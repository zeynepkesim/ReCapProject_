package com.example.ReCapProject.entities.requests.rental;

import java.util.Date;

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
public class CreateRentalRequest implements Request{
	
	@NotNull
	private int carId;
	
	@NotNull
	private int userId;
	
	@NotNull
	@NotBlank
	private String cardName;
	
	@Nullable
	private String cardBeholderName;
	
	@Nullable
	private String cardNumber;
	
	@Nullable
	private String cvvCode;
	
	@Nullable
	private String expireDate;
	
	@NotNull
	@NotBlank
	private String pickUpCity;
	
	@NotNull
	@NotBlank
	private String returnCity;
	
	@Nullable
	private boolean saveCard;
	
	@NotNull
	private Date rentDate;
	
	@NotNull
	private Date returnDate;
	
}
