package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;
import com.example.ReCapProject.entities.requests.CreateIndividualCustomerRequest;
import com.example.ReCapProject.entities.requests.UpdateIndividualCustomerRequest;

public interface IndividualCustomerService extends BaseService<CreateIndividualCustomerRequest, Integer>  {
	
	Result update(UpdateIndividualCustomerRequest entity);
	DataResult<List<IndividualCustomer>> getAll();
	
}
