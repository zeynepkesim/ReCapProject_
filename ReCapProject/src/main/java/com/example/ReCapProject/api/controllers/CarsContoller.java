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
import com.example.ReCapProject.entities.requests.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarsContoller {

	private CarService carService;

	@Autowired
	public CarsContoller(CarService carService){
		this.carService = carService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid CreateCarRequest car){
		return this.carService.add(car);
	}
	
	@PostMapping("/update")
	public Result update(@Valid UpdateCarRequest car){
		return this.carService.update(car);
	}
	
	@DeleteMapping("/delete")
	public Result delete(int carId){
		return this.carService.delete(carId);
	}
	
	@GetMapping("/getallcars")
	public DataResult<List<Car>> getAllCars() {
		return this.carService.getAll();
	}
	
	@GetMapping("/getcarsdetail")
	public DataResult<List<CarDetailDto>> getCarsDetail(){
		return this.carService.getCarsDetail();
	}
	
	@GetMapping("/getcarbybrandname")
	public DataResult<List<Car>> getCarByBrandName(String brandName){
		return this.carService.getCarByBrandName(brandName);
	}
	
	@GetMapping("/getcarbycolorname")
	public DataResult<List<Car>> getCarByColorName(String colorName){
		return this.carService.getCarByColorName(colorName);
	}
		
}
