package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;
import com.example.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.example.ReCapProject.entities.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.example.ReCapProject.entities.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.example.ReCapProject.entities.requests.individualCustomer.UpdateIndividualCustomerRequest;

@Repository
public interface IndividualCustomerService {
	
	Result add(CreateIndividualCustomerRequest entity);
	Result update(UpdateIndividualCustomerRequest entity);
	Result delete(DeleteIndividualCustomerRequest entity);
	
	DataResult<List<IndividualCustomer>> getAll();
	
	DataResult<IndividualCustomer> getById(int userId);
	
	DataResult<List<IndividualCustomerDetailDto>> getAllIndividualCustomerDetails();
	DataResult<IndividualCustomerDetailDto> getDetailsById(int userId);
	
}
