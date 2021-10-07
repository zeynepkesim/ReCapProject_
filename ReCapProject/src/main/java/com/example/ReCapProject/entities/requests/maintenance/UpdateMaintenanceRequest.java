package com.example.ReCapProject.entities.requests.maintenance;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest implements Request {

	private int maintenanceId;
	private boolean isReturned;
	
}
