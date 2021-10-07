package com.example.ReCapProject.api.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ReCapProject.business.abstracts.CarImageService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.dtos.CarImageDetailDto;
import com.example.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

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
	public Result add(@Valid CreateCarImageRequest carImage, MultipartFile file) throws IOException {
		return this.carImageService.add(carImage, file);
	}
	
	
	@PostMapping("/update")
	public Result update(@Valid UpdateCarImageRequest carImage, MultipartFile file) throws IOException {
		return this.carImageService.update(carImage, file);
	}
	
	
	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteCarImageRequest imageId) {
		return this.carImageService.delete(imageId);
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<CarImage>> getAll() {
		return this.carImageService.getAll();
	}
	
	
	@GetMapping("/getbycarid")
	public DataResult<List<CarImage>> getByCarId(int carId) {
		return this.carImageService.getByCarId(carId);
	}
	
	
	@GetMapping("/getbyid")
	public DataResult<CarImage> getById(int carImageId) {
		return this.carImageService.getById(carImageId);
	}
	
	
	@GetMapping("/getallcarimagedetails")
	public DataResult<List<CarImageDetailDto>> getAllCarImageDetails() {
		return this.carImageService.getAllCarImageDetails();
	}
	

	@GetMapping("/getcarimagedetailsbycarid")
	public DataResult<List<CarImageDetailDto>> getCarImageDetailsByCarId(int carId) {
		return this.carImageService.getCarImageDetailsByCarId(carId);
	}
	
	
	@GetMapping("/getdetailsbyid")
	public DataResult<CarImageDetailDto> getDetailsById(int carImageId) {
		return this.carImageService.getDetailsById(carImageId);
	}
	
}
