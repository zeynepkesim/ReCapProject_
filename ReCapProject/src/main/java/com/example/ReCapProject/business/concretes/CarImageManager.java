package com.example.ReCapProject.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CarImageService;
import com.example.ReCapProject.core.constants.Messages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.CarImage;
import com.example.ReCapProject.entities.requests.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {
	
	private CarImageDao carImageDao;
	
	private CarDao carDao;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, CarDao carDao) {
		this.carImageDao = carImageDao;
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateCarImageRequest entity) {
		
		var result = BusinessRules.run(checkIfNumberOfCarImagesExceed(entity.getCarId(), 5));
		
		if(result != null)
			return result;
		
		String imagePath = UUID.randomUUID().toString();
		
		LocalDateTime date = LocalDateTime.now();
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setCarId(entity.getCarId());
		
		CarImage carImage = new CarImage();
		carImage.setImagePath("C:\\ReCapProject\\ImagePath\\" + imagePath + ".png" );
		carImage.setDate(date);
		carImage.setCar(car);
		
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CAR_IMAGE_ADDED);
		
	}

	@Override
	public Result update(UpdateCarImageRequest entity) {
		
		String imagePath = UUID.randomUUID().toString();
				
		LocalDateTime date = LocalDateTime.now();
				
		Car car = this.carDao.getByCarId(entity.getCarId());
		
		CarImage carImage = this.carImageDao.getById(entity.getCarImageId());
		carImage.setImagePath("C:\\ReCapProject\\ImagePath\\" + imagePath + ".png" );
		carImage.setDate(date);
		carImage.setCar(car);
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CAR_IMAGE_UPDATED);
		
	}
	
	@Override
	public Result delete(Integer carImageId) {
		this.carImageDao.deleteById(carImageId);
		return new SuccessResult(Messages.CAR_IMAGE_DELETED);
	}
		
	@Override
	public DataResult<List<CarImage>> getAll(){
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), Messages.CAR_IMAGES_LISTED);
	}

	@Override
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId) {
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.getByCar_CarId(carId), Messages.CAR_IMAGES_LISTED);
	}
	
	public Result checkIfNumberOfCarImagesExceed(int carId, int limit) {
		if(this.carImageDao.countByCar_CarId(carId) >= limit) {
			return new ErrorResult(Messages.CAR_IMAGES_LIMIT_EXCEEDED);
		}
		return new SuccessResult();		
	}

}
