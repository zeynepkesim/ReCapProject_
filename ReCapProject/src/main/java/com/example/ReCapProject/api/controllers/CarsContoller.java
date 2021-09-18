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

import com.example.ReCapProject.business.abstracts.CarService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.dtos.CarDetailDto;
import com.example.ReCapProject.entities.requests.CreateCarRequest;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarsContoller {

	private CarService carService;

	@Autowired
	public CarsContoller(CarService carService) {
		this.carService = carService;
	}
	
	@PostMapping("/addcar")
	public Result add(@Valid CreateCarRequest car) {
		return this.carService.add(car);
	}
	
	@PostMapping("/updatecar")
	public Result update(@Valid CreateCarRequest car) {
		return this.carService.update(car);
	}
	
	@DeleteMapping("/deletecar")
	public Result delete(int carId) {
		return this.carService.delete(carId);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Car>> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping("/getcardetails")
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return this.carService.getCarDetails();
	}
	
}
