package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.DamageRecord;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRecordRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRecordRequest;

public interface DamageRecordService {
	
	Result add(CreateDamageRecordRequest entity);
	Result update(UpdateDamageRecordRequest entity);
	Result delete(DeleteDamageRecordRequest entity);
	
	DataResult<List<DamageRecord>> getAll();
	DataResult<List<DamageRecord>> getDamageRecordsByCarId(int carId);	

}
