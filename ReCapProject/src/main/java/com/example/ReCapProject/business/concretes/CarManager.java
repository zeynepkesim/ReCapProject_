package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CarService;
import com.example.ReCapProject.core.constants.Messages;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.entities.concretes.Brand;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.Color;
import com.example.ReCapProject.entities.dtos.CarDetailDto;
import com.example.ReCapProject.entities.requests.CreateCarRequest;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	
	@Autowired
	public CarManager(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateCarRequest entity) {
		
		Color color = new Color();
		color.setColorName(entity.getColorName());
		
		Brand brand = new Brand();
		brand.setBrandName(entity.getBrandName());
		brand.setModelName(entity.getModelName());
		brand.setModelYear(entity.getModelYear());
		
		Car car = new Car();
		car.setBrand(brand);
		car.setColor(color);
		car.setDailyPrice(entity.getDailyPrice());
		car.setDescription(entity.getDescription());
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CAR_ADDED);
	}

	@Override
	public Result update(CreateCarRequest entity) {
		
		Color color = new Color();
		color.setColorName(entity.getColorName());
		
		Brand brand = new Brand();
		brand.setBrandName(entity.getBrandName());
		brand.setModelName(entity.getModelName());
		brand.setModelYear(entity.getModelYear());
		
		Car car = new Car();
		car.setBrand(brand);
		car.setColor(color);
		car.setDailyPrice(entity.getDailyPrice());
		car.setDescription(entity.getDescription());
		car.setCarId(entity.getCarId());
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CAR_UPDATED);
	}

	@Override
	public Result delete(Integer carId) {
		this.carDao.deleteById(carId);
		return new SuccessResult(Messages.CAR_DELETED);
	}

	@Override
	public DataResult<Car> getByCarId(int carId) {
		return new SuccessDataResult<Car>(this.carDao.getByCarId(carId), Messages.CAR_LISTED);
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>(this.carDao.findAll(), Messages.CARS_LISTED);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarDetails(), Messages.CARS_DETAILS_LISTED);
	}

	
}
