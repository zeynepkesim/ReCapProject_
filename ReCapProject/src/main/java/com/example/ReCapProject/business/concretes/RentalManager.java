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
import com.example.ReCapProject.dataAccess.abstracts.RentalDao;
import com.example.ReCapProject.entities.concretes.ApplicationUser;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.CreateRentalRequest;

@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	
	@Autowired
	public RentalManager(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@Override
	public Result add(CreateRentalRequest entity) {
		
		var result = BusinessRules.run(checkIfCarIsReturned());
		
		if(result != null)
			return result;
			
		Car car = new Car();
		car.setCarId(entity.getCarId());
		
		ApplicationUser user = new ApplicationUser();
		user.setUserId(entity.getUserId());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setRentDate(entity.getRentDate());
		rental.setReturnDate(entity.getReturnDate());
		
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL_ADDED);
	}

	@Override
	public Result update(CreateRentalRequest entity) {
		
		Car car = new Car();
		car.setCarId(entity.getCarId());
		
		ApplicationUser user = new ApplicationUser();
		user.setUserId(entity.getUserId());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setRentalId(entity.getRentalId());
		rental.setRentDate(entity.getRentDate());
		rental.setReturnDate(entity.getReturnDate());
		
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
		return new SuccessDataResult<List<RentalDto>>(this.rentalDao.getCarAndRentalDetails(), Messages.RENTALS_DETAILS_LISTED);
	}
	
	private Result checkIfCarIsReturned() {
		if(this.rentalDao.existsByIsReturnedIsFalse()) {
			return new ErrorResult(Messages.CAR_NOT_AVAILABLE);
		}
		return new SuccessResult();
	}
	

}
