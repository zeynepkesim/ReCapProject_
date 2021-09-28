package com.example.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.DamageRecordService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.DamageRecord;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRecordRequest;

@RestController
@RequestMapping("/api/damages")
@CrossOrigin
public class DamageRecordsController {
	
	private DamageRecordService damageRecordService;

	@Autowired
	public DamageRecordsController(DamageRecordService damageRecordService) {
		this.damageRecordService = damageRecordService;
	}
	
	@PostMapping("/adddamage")
	public Result add(CreateDamageRecordRequest damage) {
		return this.damageRecordService.add(damage);
	}

	@PostMapping("/updatedamage")
	public Result update(UpdateDamageRecordRequest damage) {
		return this.damageRecordService.update(damage);
	}
	
	@DeleteMapping("/deletedamage")
	public Result delete(DeleteDamageRecordRequest damageId) {
		return this.damageRecordService.delete(damageId);
	}
	
	@GetMapping("/getalldamages")
	public DataResult<List<DamageRecord>> getAll() {
		return this.damageRecordService.getAll();
	}
	
	@GetMapping("/getdamagerecords")
	public DataResult<List<DamageRecord>> getDamageRecordsByCarId(int carId) {
		return this.damageRecordService.getDamageRecordsByCarId(carId);
	}

}
