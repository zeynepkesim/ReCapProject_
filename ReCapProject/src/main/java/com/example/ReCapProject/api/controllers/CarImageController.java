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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ReCapProject.business.abstracts.CarImageService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

@RestController
@RequestMapping("/api/carimages")
@CrossOrigin
public class CarImageController {

	private CarImageService carImageService;

	@Autowired
	public CarImageController(CarImageService carImageService) {
		this.carImageService = carImageService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam("carId") int carId, MultipartFile file) throws IOException {
		
		CreateCarImageRequest creataCarImageRequest = new CreateCarImageRequest();
		creataCarImageRequest.setCarId(carId);
		creataCarImageRequest.setFile(file);
		return this.carImageService.add(creataCarImageRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid UpdateCarImageRequest carImage, MultipartFile file) throws IOException {
		carImage.setFile(file);
		return this.carImageService.update(carImage);
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteCarImageRequest imageId) {
		return this.carImageService.delete(imageId);
	}
	
	@GetMapping("/getimagesbycarid")
	public DataResult<List<CarImage>> getByCarId(int carId) {
		return this.carImageService.getImagesByCarId(carId);
	}
	
}
