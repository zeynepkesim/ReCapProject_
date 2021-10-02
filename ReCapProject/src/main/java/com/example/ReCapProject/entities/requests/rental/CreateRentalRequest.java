package com.example.ReCapProject.entities.requests.rental;

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
	private int pickUpCityId;
	
	@NotNull
	private int returnCityId;
	
	@Nullable
	private int additionalServiceId;
	
	@Nullable
	private String cardName;
	
	@Nullable
	private String cardBeholderName;
	
	@Nullable
	private String cardNumber;
	
	@Nullable
	private String cvvCode;
	
	@Nullable
	private String expireDate;
	
	@Nullable
	private boolean saveCard;
	
	@NotNull
	private String rentDate;
	
	@NotNull
	private String returnDate;
	
}
