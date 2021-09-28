package com.example.ReCapProject.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.MaintenanceService;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.requests.maintenance.CreateMaintenanceRequest;
import com.example.ReCapProject.entities.requests.maintenance.DeleteMaintenanceRequest;
import com.example.ReCapProject.entities.requests.maintenance.UpdateMaintenanceRequest;

@RestController
@RequestMapping("/api/maintenances")
@CrossOrigin
public class MaintenancesController {
	
	private MaintenanceService maintenanceService;
	
	@Autowired
	public MaintenancesController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@PostMapping("/addmaintenance")
	public Result add(CreateMaintenanceRequest maintenance) {
		return this.maintenanceService.add(maintenance);
	}

	@PostMapping("/updatemaintenance")
	public Result update(UpdateMaintenanceRequest maintenance) {
		return this.maintenanceService.update(maintenance);
	}
	
	@DeleteMapping("/deletemaintenance")
	public Result delete(DeleteMaintenanceRequest maintenanceId) {
		return this.maintenanceService.delete(maintenanceId);
	}
	
	@GetMapping("/getallmaintenance")
	public Result getAll() {
		return this.maintenanceService.getAll();
	}
	
}
