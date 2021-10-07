package com.example.ReCapProject.entities.dtos;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto implements Dto {

	private boolean isAvailable;
	private double dailyPrice;
	private String brandBrandName;   //when it's brandName, mapping doesn't work.
	private String brandModelName;   //
	private int brandModelYear;      //
	private String colorName;
	private String cityName;
	
}
