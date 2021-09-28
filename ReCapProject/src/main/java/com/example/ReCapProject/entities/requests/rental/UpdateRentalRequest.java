package com.example.ReCapProject.entities.requests.rental;

import java.util.Date;

import javax.validation.constraints.NotBlank;

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
public class UpdateRentalRequest implements Request  {

	@NotNull
	private int rentalId;
	
	@NotNull
	private Date returnDate;
	
	@NotNull
	private long returnKilometer;
	
	@NotNull
	@NotBlank
	private String returnCity;
	
	@NotNull
	private boolean isReturned;
	
}
