package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.dtos.AdditionalServiceDto;
import com.example.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest entity);
	Result update(UpdateAdditionalServiceRequest entity);
	Result delete(DeleteAdditionalServiceRequest entity);
	
	DataResult<List<AdditionalService>> getAll();
	DataResult<List<AdditionalService>> getAdditionalServicesByRentalId(int rentalId);
	
	DataResult<List<AdditionalServiceDto>> getAdditionalServiceDetails();

}
