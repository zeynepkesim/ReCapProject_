package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.entities.concretes.CorporateCustomer;
import com.example.ReCapProject.entities.requests.CreateCorporateCustomerRequest;

public interface CorporateCustomerService extends BaseService<CreateCorporateCustomerRequest, Integer> {

	DataResult<List<CorporateCustomer>> getAll();
	
}
