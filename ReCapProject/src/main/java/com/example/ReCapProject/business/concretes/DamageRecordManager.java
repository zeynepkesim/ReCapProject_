package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.DamageRecordService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.DamageRecordDao;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.DamageRecord;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRecordRequest;

@Service
public class DamageRecordManager implements DamageRecordService {
	
	private DamageRecordDao damageRecordDao;
	private CarDao carDao;

	@Autowired
	public DamageRecordManager(DamageRecordDao damageRecordDao, CarDao carDao) {
		this.damageRecordDao = damageRecordDao;
		this.carDao = carDao;
	}

	@Override
	public Result add(CreateDamageRecordRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		
		DamageRecord damage = new DamageRecord();
		damage.setDamageInfo(entity.getDamageInfo());
		damage.setCar(car);
		
		this.damageRecordDao.save(damage);
		
		return new SuccessResult(Messages.DAMAGE_RECORD_ADDED);
		
	}

	@Override
	public Result update(UpdateDamageRecordRequest entity) {
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		
		DamageRecord damage = this.damageRecordDao.getById(entity.getDamageRecordId());
		damage.setDamageInfo(entity.getDamageInfo());
		damage.setCar(car);
		
		this.damageRecordDao.save(damage);
		
		return new SuccessResult(Messages.DAMAGE_RECORD_UPDATED);
		
	}

	@Override
	public Result delete(DeleteDamageRecordRequest entity) {
		
		this.damageRecordDao.deleteById(entity.getDamageRecordId());
		return new SuccessResult(Messages.DAMAGE_RECORD_DELETED);
		
	}

	@Override
	public DataResult<List<DamageRecord>> getAll() {
		
		return new SuccessDataResult<List<DamageRecord>> (this.damageRecordDao.findAll(), Messages.DAMAGE_RECORDS_LISTED);
		
	}

	@Override
	public DataResult<List<DamageRecord>> getDamageRecordsByCarId(int carId) {
		
		return new SuccessDataResult<List<DamageRecord>>(this.damageRecordDao.getByCar_CarId(carId), Messages.DAMAGE_RECORDS_LISTED);
	
	}

}
