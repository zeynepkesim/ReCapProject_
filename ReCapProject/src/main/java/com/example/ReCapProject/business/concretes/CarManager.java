package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CarService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.business.paths.Paths;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.example.ReCapProject.entities.concretes.Brand;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.concretes.Color;
import com.example.ReCapProject.entities.dtos.CarDetailDto;
import com.example.ReCapProject.entities.requests.car.CreateCarRequest;
import com.example.ReCapProject.entities.requests.car.DeleteCarRequest;
import com.example.ReCapProject.entities.requests.car.UpdateCarRequest;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private CarImageDao carImageDao;
	
	@Autowired
	public CarManager(CarDao carDao, CarImageDao carImageDao) {
		this.carDao = carDao;
		this.carImageDao = carImageDao;
	}

	@Override
	public Result add(CreateCarRequest entity) {
		
		Color color = new Color();
		color.setColorId(entity.getColorId());
		
		Brand brand = new Brand();
		brand.setBrandId(entity.getBrandId());
		
		Car car = new Car();
		car.setBrand(brand);
		car.setColor(color);
		car.setDailyPrice(entity.getDailyPrice());
		car.setDescription(entity.getDescription());
		car.setMinFindexPoint(entity.getMinFindexPoint());
		
		// Default image
		if(this.carImageDao.getByCar_CarId(car.getCarId()).isEmpty()) {
			CarImage carImage = new CarImage();
			carImage.setImagePath(Paths.CAR_IMAGE_DEFAULT_PATH);
			carImage.setCar(car);
			carImageDao.save(carImage);
		}
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CAR_ADDED);
	}

	@Override
	public Result update(UpdateCarRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setDailyPrice(entity.getDailyPrice());
		car.setDescription(entity.getDescription());
		car.setAvailable(entity.isAvailable());
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CAR_UPDATED);
	}

	@Override
	public Result delete(DeleteCarRequest entity) {
		this.carDao.deleteById(entity.getCarId());
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
	public DataResult<List<CarDetailDto>> getCarsDetail() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarDetails(), Messages.CAR_DETAILS_LISTED);
	}

	@Override
	public DataResult<List<Car>> getCarByBrandName(String brandName) {
		return new SuccessDataResult<List<Car>>(this.carDao.getByBrand_BrandName(brandName));
	}

	@Override
	public DataResult<List<Car>> getCarByColorName(String colorName) {
		return new SuccessDataResult<List<Car>>(this.carDao.getByColor_ColorName(colorName));
	}

	@Override
	public DataResult<List<Car>> getAvailableCars() {
		return new SuccessDataResult<List<Car>>(this.carDao.getByIsAvailableIsTrue());
	}
	
	
}
