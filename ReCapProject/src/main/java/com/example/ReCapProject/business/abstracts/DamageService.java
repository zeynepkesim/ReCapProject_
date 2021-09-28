package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Damage;
import com.example.ReCapProject.entities.requests.damage.CreateDamageRequest;
import com.example.ReCapProject.entities.requests.damage.DeleteDamageRequest;
import com.example.ReCapProject.entities.requests.damage.UpdateDamageRequest;

public interface DamageService {
	
	Result add(CreateDamageRequest entity);
	Result update(UpdateDamageRequest entity);
	Result delete(DeleteDamageRequest entity);
	
	DataResult<List<Damage>> getAll();
	DataResult<List<Damage>> getDamagesByCarId(int carId);
	

}
