package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.RentalService;
import com.example.ReCapProject.core.constants.Messages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.RentalDao;
import com.example.ReCapProject.entities.concretes.ApplicationUser;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.CreateRentalRequest;
import com.example.ReCapProject.entities.requests.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	private CarDao carDao;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao) {
		this.rentalDao = rentalDao;
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateRentalRequest entity) {
		
		var result = BusinessRules.run(checkIfCarIsAvailable());
		
		if(result != null)
			return result;
			
		// We are not creating a new Car. Instead of that we get the Car by ID and changing the valuables we want.
		// Note: If we create a new Car its going to delete all the valuables that we didn't insert in here.
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setAvailable(false);
		
		ApplicationUser user = new ApplicationUser();
		user.setUserId(entity.getUserId());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setRentDate(entity.getRentDate());
		rental.setReturnDate(entity.getReturnDate());
		
		this.rentalDao.save(rental);
		this.carDao.save(car);
		return new SuccessResult(Messages.RENTAL_ADDED);
	}

	@Override
	public Result update(UpdateRentalRequest entity) {
		
		//Create UpdateRentalRequest
		Rental rental = this.rentalDao.getById(entity.getRentalId());
		rental.setReturnDate(entity.getReturnDate());
		
		Car car = rental.getCar();
		car.setAvailable(entity.isReturned());
		
		// car.setAvailable(entity.isReturned()); Put this in UpdateRentalRequest
	
		this.carDao.save(car);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL_UPDATED);
	}

	@Override
	public Result delete(Integer rentalId) {
		this.rentalDao.deleteById(rentalId);
		return new SuccessResult(Messages.RENTAL_DELETED);
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), Messages.RENTALS_LISTED);
	}
	
	@Override
	public DataResult<List<RentalDto>> getCarAndRentalDetails() {
		return new SuccessDataResult<List<RentalDto>>(this.rentalDao.getCarAndRentalDetail(), Messages.RENTAL_DETAILS_LISTED);
	}
	
	private Result checkIfCarIsAvailable() {
		if(this.carDao.existsByIsAvailableIsTrue()) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.CAR_IS_NOT_RETURNED);
	}

	@Override
	public DataResult<List<Rental>> getOpenRentals() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.getByCar_IsAvailableIsFalse(), Messages.RENTALS_LISTED);
	}

	@Override
	public DataResult<List<Rental>> getClosedRentals() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.getByCar_IsAvailableIsTrue(), Messages.RENTALS_LISTED);
	}
	

}
