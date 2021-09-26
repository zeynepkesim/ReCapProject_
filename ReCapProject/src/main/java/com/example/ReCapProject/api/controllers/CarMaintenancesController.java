package com.example.ReCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.CarMaintenanceService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarMaintenance;
import com.example.ReCapProject.entities.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.UpdateCarMaintenanceRequest;


@RestController
@RequestMapping("/api/carmaintenances")
@CrossOrigin
public class CarMaintenancesController {
	
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@PostMapping("/addcarmaintenance")
	public Result add(@Valid CreateCarMaintenanceRequest carMaintenance) {
		return this.carMaintenanceService.add(carMaintenance);
	}
	
	@PostMapping("/updatecarmaintenance")
	public Result update(@Valid UpdateCarMaintenanceRequest carMaintenance) {
		return this.carMaintenanceService.update(carMaintenance);
	}
	
	@DeleteMapping("/deletecarmaintenance")
	public Result delete(DeleteCarMaintenanceRequest carMaintenance){
		return this.carMaintenanceService.delete(carMaintenance);
	}
	
	@GetMapping("/getall") 
	public DataResult<List<CarMaintenance>> getAll() {
		return this.carMaintenanceService.getAll();
	}

	
}
