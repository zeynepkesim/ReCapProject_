package com.example.ReCapProject.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDto implements Dto {

	private String invoiceNo;
	private LocalDateTime creationDate;
	private int rentalId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private double rentalPrice;
	private long pickUpKilometer;
	private long returnKilometer;	
	private int carId;
	private String brandBrandName;
	private String modelName;
	private int modelYear;
	
}
