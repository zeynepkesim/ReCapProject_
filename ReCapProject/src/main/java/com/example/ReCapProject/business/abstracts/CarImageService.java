package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.requests.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.UpdateCarImageRequest;

@Repository
public interface CarImageService extends BaseService<CreateCarImageRequest, Integer> {
	
	Result update(UpdateCarImageRequest entity);
	DataResult<List<CarImage>> getCarImagesByCarId(int carId);
	DataResult<List<CarImage>> getAll();

}
