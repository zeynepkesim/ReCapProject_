package com.example.ReCapProject.entities.dtos;

import java.time.LocalDateTime;

import com.example.ReCapProject.core.entities.abstracts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDetailDto implements Dto {
	
	private int carId;
	private boolean inMaintenance;
	private LocalDateTime maintenanceDate;
	
}
