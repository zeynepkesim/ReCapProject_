package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.dtos.CarDetailDto;
import com.example.ReCapProject.entities.requests.CreateCarRequest;

@Repository
public interface CarService extends BaseService<CreateCarRequest, Integer>{

	DataResult<Car> getByCarId(int carId);
	DataResult<List<Car>> getAll();
	DataResult<List<CarDetailDto>> getCarDetails();
	
}
