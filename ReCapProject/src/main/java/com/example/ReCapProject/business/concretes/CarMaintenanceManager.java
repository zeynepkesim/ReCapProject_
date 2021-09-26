package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CarMaintenanceService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.CarMaintenanceDao;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.CarMaintenance;
import com.example.ReCapProject.entities.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.UpdateCarMaintenanceRequest;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
	
	private CarMaintenanceDao carMaintenanceDao;
	private CarDao carDao;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, CarDao carDao) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateCarMaintenanceRequest entity) {
		
		var result = BusinessRules.run(checkIfCarIsInRental());
		
		if(result != null)
			return result;
		
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setAvailable(false);
		
		
		CarMaintenance carMaintenance = new CarMaintenance();
		carMaintenance.setCar(car);
		carMaintenance.setInCarMaintenance(true);
		
		this.carDao.save(car);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_ADDED);
		
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setAvailable(entity.isAvailable());
		
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(entity.getCarMaintenanceId());
		
		this.carDao.save(car);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_UPDATED);
				
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest entity) {
		this.carMaintenanceDao.deleteById(entity.getCarMaintenanceId());
		return new SuccessResult(Messages.CAR_MAINTENANCE_DELETED);
	}
	
	private Result checkIfCarIsInRental() {
		if(!this.carMaintenanceDao.existsByCar_IsAvailableIsTrue()) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.CAR_IS_IN_RENTAL);
	}

	@Override
	public DataResult<List<CarMaintenance>> getAll() {
		return new SuccessDataResult<List<CarMaintenance>>(this.carMaintenanceDao.findAll(), Messages.CAR_MAINTENANCES_LISTED);
	}

}
