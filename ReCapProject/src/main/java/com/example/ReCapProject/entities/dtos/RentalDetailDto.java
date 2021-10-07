package com.example.ReCapProject.entities.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailDto implements Dto {
	
	private int rentalId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private long pickUpKilometer;
	private long returnKilometer;	
	private String brandBrandName;   //when it's brandName, mapping doesn't work.
	private String modelName;
	private int modelYear;
	private boolean isPayed;
	private List<AdditionalServiceDetailDto> additionalServices;
	
}
