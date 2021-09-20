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

import com.example.ReCapProject.business.abstracts.CarImageService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.requests.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.UpdateCarImageRequest;

@RestController
@RequestMapping("/api/carimages")
@CrossOrigin
public class CarImagesController {
	
	private CarImageService carImageService;

	@Autowired
	public CarImagesController(CarImageService carImageService) {
		this.carImageService = carImageService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid CreateCarImageRequest carImage) {
		return this.carImageService.add(carImage);
	}
	
	@PostMapping("/update")
	public Result update(@Valid UpdateCarImageRequest carImage) {
		return this.carImageService.update(carImage);
	}
	
	@DeleteMapping("/delete")
	public Result delete(int carImageId) {
		return this.carImageService.delete(carImageId);
	}
	
	@GetMapping("/getallcarimages")
	public DataResult<List<CarImage>> getAllCarImages() {
		return this.carImageService.getAll();
	}
	
	@GetMapping("/getcarimagesbycarid")
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId) {
		return this.carImageService.getCarImagesByCarId(carId);
	}
	
}
