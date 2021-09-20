package com.example.ReCapProject.entities.requests;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest implements Request {

	private String brandName;
	private String modelName;
	private String description;
	private String colorName;
	private int modelYear;
	private double dailyPrice;
	// private boolean isAvailable; Put this in UpdateCarRequest
	
}
