package com.example.ReCapProject.entities.requests.carMaintenance;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	private int carId;
	
	@NotNull
	private int carMaintenanceId;
	
	@NotNull
	private boolean isAvailable;

}
