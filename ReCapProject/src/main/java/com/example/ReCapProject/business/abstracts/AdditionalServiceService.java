package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.example.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

@Repository
public interface AdditionalServiceService {

	 Result add(CreateAdditionalServiceRequest entity);
	 Result update(UpdateAdditionalServiceRequest entity);
	 Result delete(DeleteAdditionalServiceRequest entity);
	 
	 DataResult<List<AdditionalService>> getAll();	
	 DataResult<List<AdditionalService>> getByRentalId(int rentalId);
	 
	 DataResult<AdditionalService> getById(int additionalServiceId); 	 
	  
	 DataResult<List<AdditionalServiceDetailDto>> getAllAdditionalServiceDetails();
	 DataResult<AdditionalServiceDetailDto> getDetailsById(int additionalServiceId); 
	 
}
