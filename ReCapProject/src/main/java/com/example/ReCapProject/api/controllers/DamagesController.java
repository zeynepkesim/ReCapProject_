package com.example.ReCapProject.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.DamageService;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRequest;

@RestController
@RequestMapping("/api/damages")
@CrossOrigin
public class DamagesController {
	
	private DamageService damageService;

	@Autowired
	public DamagesController(DamageService damageService) {
		this.damageService = damageService;
	}
	
	@PostMapping("/adddamage")
	public Result add(CreateDamageRequest damage) {
		return this.damageService.add(damage);
	}

	@PostMapping("/updatedamage")
	public Result update(UpdateDamageRequest damage) {
		return this.damageService.update(damage);
	}
	
	@DeleteMapping("/deletedamage")
	public Result delete(DeleteDamageRequest damageId) {
		return this.damageService.delete(damageId);
	}
	
	@GetMapping("/getalldamages")
	public Result getAll() {
		return this.damageService.getAll();
	}
	
	@GetMapping("/getdamagesbycarid")
	public Result getDamagesByCarId(int carId) {
		return this.damageService.getDamagesByCarId(carId);
	}

}
