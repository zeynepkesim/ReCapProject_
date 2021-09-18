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

import com.example.ReCapProject.business.abstracts.RentalService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.CreateRentalRequest;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin
public class RentalsController {
	
	private RentalService rentalService;
	
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@PostMapping("/addrental")
	public Result add(@Valid CreateRentalRequest rental) {
		return this.rentalService.add(rental);
	}
	
	@PostMapping("/updaterental")
	public Result update(@Valid CreateRentalRequest rental) {
		return this.rentalService.update(rental);
	}
	
	@DeleteMapping("/deleterental")
	public Result delete(int rentalId) {
		return this.rentalService.delete(rentalId);
	}
		
	@GetMapping("/getall")
	public DataResult<List<Rental>> getAll() {
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getcarandrentaldetails")
	public DataResult<List<RentalDto>> getCarAndRentalDetails() {
		return this.rentalService.getCarAndRentalDetails();
	}
	
}
