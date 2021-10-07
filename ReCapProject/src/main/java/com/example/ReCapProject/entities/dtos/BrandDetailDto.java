package com.example.ReCapProject.entities.dtos;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDetailDto implements Dto {
	
	private String brandBrandName;   //when it's brandName, mapping doesn't work.
	private String modelName;
	private int modelYear;

}
