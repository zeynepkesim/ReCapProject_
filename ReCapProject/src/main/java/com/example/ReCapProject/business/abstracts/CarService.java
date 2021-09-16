package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.dtos.CarDetailDto;

public interface CarService extends BaseService<Car, Integer>{

	DataResult<Car> getByCarId(int carId);
	DataResult<List<Car>> getAll();
	DataResult<List<CarDetailDto>> getCarsDetail();
	
}
