package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.DamageService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.DamageDao;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.Damage;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRequest;

@Service
public class DamageManager implements DamageService {
	
	private DamageDao damageDao;
	private CarDao carDao;

	@Autowired
	public DamageManager(DamageDao damageDao, CarDao carDao) {
		this.damageDao = damageDao;
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateDamageRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		
		Damage damage = new Damage();
		damage.setDamageInfo(entity.getDamageInfo());
		damage.setCar(car);
		
		this.damageDao.save(damage);
		
		return new SuccessResult(Messages.DAMAGE_ADDED);
		
	}

	@Override
	public Result update(UpdateDamageRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		
		Damage damage = this.damageDao.getById(entity.getDamageId());
		damage.setDamageInfo(entity.getDamageInfo());
		damage.setCar(car);
		
		this.damageDao.save(damage);
		
		return new SuccessResult(Messages.DAMAGE_UPDATED);
		
	}

	@Override
	public Result delete(DeleteDamageRequest entity) {
		
		this.damageDao.deleteById(entity.getDamageId());
		return new SuccessResult(Messages.DAMAGE_DELETED);
		
	}

	@Override
	public DataResult<List<Damage>> getAll() {
		
		return new SuccessDataResult<List<Damage>> (this.damageDao.findAll(), Messages.DAMAGES_LISTED);
		
	}

	@Override
	public DataResult<List<Damage>> getDamagesByCarId(int carId) {
		
		return new SuccessDataResult<List<Damage>>(this.damageDao.getByCar_CarId(carId), Messages.DAMAGES_LISTED);
	
	}

}
