package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;
import com.example.ReCapProject.entities.requests.CreateIndividualCustomerRequest;

public interface IndividualCustomerService extends BaseService<CreateIndividualCustomerRequest, Integer>  {
	DataResult<List<IndividualCustomer>> getAll();
}
