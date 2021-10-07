package com.example.ReCapProject.business.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ReCapProject.business.abstracts.CarImageService;
import com.example.ReCapProject.business.constants.messages.CarImageMessages;
import com.example.ReCapProject.business.constants.paths.Paths;
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
import com.example.ReCapProject.entities.dtos.CarImageDetailDto;
import com.example.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.example.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;	
	private CarDao carDao;
	
	private ModelMapper modelMapper;
	
	
	@Autowired
	public CarImageManager(CarImageDao carImageDao, CarDao carDao, ModelMapper modelMapper) {
		
		this.carImageDao = carImageDao;		
		this.carDao = carDao;
		
		this.modelMapper = modelMapper;
		
	}
	

	@Override
	public Result add(CreateCarImageRequest entity, MultipartFile file) throws IOException {
		
		var result = BusinessRules.run(checkCarImageLimit(entity.getCarId(), 5), checkImageType(file), checkCarImageIsEmpty(file));
		
		if(result != null)
			return result;
			
		LocalDateTime date = LocalDateTime.now();
		String imageName = UUID.randomUUID().toString();
		
		File myFile = new File(Paths.CAR_IMAGES_PATH + imageName + "." + file.getContentType().substring(file.getContentType().indexOf("/")+1));
		myFile.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());   //Literally copying the file (byte by byte)!
		fos.close();
		
		Car car = this.carDao.getById(entity.getCarId());
		car.setCarId(entity.getCarId());
		
		CarImage carImage = new CarImage();
		carImage.setImagePath(myFile.toString());
		carImage.setDate(date);
		carImage.setCar(car);
				
		this.carImageDao.save(carImage);
		return new SuccessResult(CarImageMessages.CAR_IMAGE_ADDED);
		
	}
	
	
	@Override
	public Result update(UpdateCarImageRequest entity, MultipartFile file) throws IOException {
			
		var result = BusinessRules.run(checkImageType(file), checkCarImageIsEmpty(file));
		
		if(result != null)
			return result;
		
		LocalDateTime date = LocalDateTime.now();
		String imageName = UUID.randomUUID().toString();
		
		File myFile = new File(Paths.CAR_IMAGES_PATH + imageName + "." + file.getContentType().substring(file.getContentType().indexOf("/")+1));
		myFile.createNewFile();
		
		FileOutputStream fileOutputStream = new FileOutputStream(myFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();
		
		Car car = this.carDao.getById(entity.getCarId());
		car.setCarId(entity.getCarId());
		
		CarImage carImage = this.carImageDao.getById(entity.getCarImageId());
		carImage.setImagePath(myFile.toString());
		carImage.setDate(date);
		carImage.setCar(car);
				
		this.carImageDao.save(carImage);
		return new SuccessResult(CarImageMessages.CAR_IMAGE_UPDATED);
		
	}
	

	@Override
	public Result delete(DeleteCarImageRequest entity) {
		
		this.carImageDao.deleteById(entity.getCarImageId());
		return new SuccessResult(CarImageMessages.CAR_IMAGE_DELETED);
		
	}
	

	@Override
	public DataResult<List<CarImage>> getAll() {
		
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), CarImageMessages.CAR_IMAGES_LISTED);
	
	}
	

	@Override
	public DataResult<List<CarImage>> getByCarId(int carId) {
		
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.getByCar_CarId(carId), CarImageMessages.CAR_IMAGES_LISTED);
	
	}
	
	
	@Override
	public DataResult<CarImage> getById(int carImageId) {
		
		return new SuccessDataResult<CarImage>(this.carImageDao.getById(carImageId), CarImageMessages.CAR_IMAGES_LISTED);
	
	}

	
	@Override
	public DataResult<List<CarImageDetailDto>> getAllCarImageDetails() {

		List<CarImage> carImages = this.carImageDao.findAll();
		
		List<CarImageDetailDto> carImageDetailDtos = carImages.stream()
													 		  .map(this::convertToDto)
								                              .collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarImageDetailDto>>(carImageDetailDtos, CarImageMessages.CAR_IMAGES_LISTED);
		
	}
	
	
	@Override
	public DataResult<List<CarImageDetailDto>> getCarImageDetailsByCarId(int carId) {
		
		List<CarImage> carImages = this.carImageDao.getByCar_CarId(carId);
		
		List<CarImageDetailDto> carImageDetailDtos = carImages.stream()
				                                              .map(this::convertToDto)
				                                              .collect(Collectors.toList());
															
		return new SuccessDataResult<List<CarImageDetailDto>>(carImageDetailDtos, CarImageMessages.CAR_IMAGES_LISTED);
		
	}
	

	@Override
	public DataResult<CarImageDetailDto> getDetailsById(int carImageId) {

		CarImage carImage = this.carImageDao.getById(carImageId);
		
		return new SuccessDataResult<CarImageDetailDto>(modelMapper.map(carImage, CarImageDetailDto.class), CarImageMessages.CAR_IMAGES_LISTED);
	
	}
	
	
	private CarImageDetailDto convertToDto(CarImage carImage) {
		
		CarImageDetailDto carImageDetailDto = modelMapper.map(carImage, CarImageDetailDto.class);
		
		return carImageDetailDto;
		
	}

	
	private Result checkCarImageLimit(int carId, int limit) {
		
		if(this.carImageDao.countByCar_CarId(carId) >= limit) {
			return new ErrorResult(CarImageMessages.CAR_IMAGE_LIMIT_EXCEEDED);
		}
		return new SuccessResult();
		
	}
	
	
	private Result checkImageType(MultipartFile file) {
			
		if(checkCarImageIsEmpty(file).isSuccess()) {
			
		if(!file.getContentType().substring(file.getContentType().indexOf("/")+1).equals("jpeg")) {
			System.out.println(file.getContentType());
			return new ErrorResult(CarImageMessages.CAR_IMAGE_FORMAT_ERROR);
			}
		}
		return new SuccessResult();
	}
	
	
	private Result checkCarImageIsEmpty(MultipartFile file) {
		
		if(file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult(CarImageMessages.CAR_IMAGE_UNUPLOADED);
		}		
		return new SuccessResult();		
	}

}
