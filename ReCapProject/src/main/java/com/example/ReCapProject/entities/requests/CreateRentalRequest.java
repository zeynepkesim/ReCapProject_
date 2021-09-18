package com.example.ReCapProject.entities.requests;

import java.util.Date;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest implements Request{
	
	private int rentalId;
	private int carId;
	private int userId;
	private Date rentDate;
	private Date returnDate;
	private boolean isReturned;
	
}
