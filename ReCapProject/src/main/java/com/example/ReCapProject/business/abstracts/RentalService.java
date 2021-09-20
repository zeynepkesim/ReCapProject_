package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.CreateRentalRequest;
import com.example.ReCapProject.entities.requests.UpdateRentalRequest;

public interface RentalService extends BaseService<CreateRentalRequest, Integer>{
	
	Result update(UpdateRentalRequest entity);
	DataResult<List<Rental>> getAll();
	DataResult<List<RentalDto>> getCarAndRentalDetails();
	DataResult<List<Rental>> getOpenRentals();
	DataResult<List<Rental>> getClosedRentals();
	
}
