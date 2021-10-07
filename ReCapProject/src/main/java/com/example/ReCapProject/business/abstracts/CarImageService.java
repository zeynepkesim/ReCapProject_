package com.example.ReCapProject.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.dtos.CarImageDetailDto;
import com.example.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

@Repository
public interface CarImageService {

	Result add(CreateCarImageRequest entity, MultipartFile file) throws IOException;
	Result update(UpdateCarImageRequest entity, MultipartFile file) throws IOException;
	Result delete(DeleteCarImageRequest entity);
	
	DataResult<List<CarImage>> getAll();
	DataResult<List<CarImage>> getByCarId(int carId);
	
	DataResult<CarImage> getById(int carImageId); 
	
	DataResult<List<CarImageDetailDto>> getAllCarImageDetails();
	DataResult<List<CarImageDetailDto>> getCarImageDetailsByCarId(int carId);
	DataResult<CarImageDetailDto> getDetailsById(int carImageId);  
	 	
}

