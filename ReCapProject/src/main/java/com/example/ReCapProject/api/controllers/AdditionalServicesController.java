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

import com.example.ReCapProject.business.abstracts.AdditionalServiceService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.dtos.AdditionalServiceDto;
import com.example.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

@RestController
@RequestMapping("/api/additionalservices")
@CrossOrigin
public class AdditionalServicesController {
	
	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("/addadditionalservice")
	public Result add(@Valid CreateAdditionalServiceRequest entity) {
		return this.additionalServiceService.add(entity);
	}
	
	@PostMapping("/updateadditionalservice")
	public Result update(@Valid UpdateAdditionalServiceRequest entity) {
		return this.additionalServiceService.update(entity);
	}
	
	@DeleteMapping("/deleteadditionalservice")
	public Result delete(@Valid DeleteAdditionalServiceRequest entity) {
		return this.additionalServiceService.delete(entity);
	}
	
	@GetMapping("/getalladditionalservices")
	public DataResult<List<AdditionalService>> getAll() {
		return this.additionalServiceService.getAll();
	}
	
	@GetMapping("getadditionalservicesbyrentalid")
	public DataResult<List<AdditionalService>> getAdditionalServicesByRentalId(int rentalId) {
		return this.additionalServiceService.getAdditionalServicesByRentalId(rentalId);
	}
	
	@GetMapping("getadditionalservicedetails")
	public DataResult<List<AdditionalServiceDto>> getAdditionalServiceDetails() {
		return this.additionalServiceService.getAdditionalServiceDetails();
	}

}
