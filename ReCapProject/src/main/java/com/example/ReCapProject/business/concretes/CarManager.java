package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CarService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.dtos.CarDetailDto;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	
	@Autowired
	public CarManager(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public Result add(Car entity) {
		this.carDao.save(entity);
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public Result update(Car entity) {
		this.carDao.save(entity);
		return new SuccessResult("Başarıyla güncellendi.");
	}

	@Override
	public Result delete(Integer carId) {
		this.carDao.deleteById(carId);
		return new SuccessResult("Başarıyla silindi.");
	}

	@Override
	public DataResult<Car> getByCarId(int carId) {
		return new SuccessDataResult<Car>("Başarıyla listelendi.", this.carDao.getByCarId(carId));
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>("Başarıyla listelendi.", this.carDao.findAll());
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarsDetail() {
		return new SuccessDataResult<List<CarDetailDto>>("Başarıyla listelendi.", this.carDao.getCarsDetail());
	}



}
