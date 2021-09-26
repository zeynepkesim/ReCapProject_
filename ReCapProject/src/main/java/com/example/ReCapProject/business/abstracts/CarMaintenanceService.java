package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CarMaintenance;
import com.example.ReCapProject.entities.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.example.ReCapProject.entities.requests.carMaintenance.UpdateCarMaintenanceRequest;

public interface CarMaintenanceService {
	
	Result add(CreateCarMaintenanceRequest entity);
	Result update(UpdateCarMaintenanceRequest entity);
	Result delete(DeleteCarMaintenanceRequest entity);
	
	DataResult<List<CarMaintenance>> getAll();

}
