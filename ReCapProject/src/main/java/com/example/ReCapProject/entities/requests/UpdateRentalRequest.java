package com.example.ReCapProject.entities.requests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	private int rentalId;
	private Date rentDate;
	private Date returnDate;
	private boolean isReturned;

}
