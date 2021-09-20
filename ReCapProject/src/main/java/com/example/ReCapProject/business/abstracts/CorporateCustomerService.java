package com.example.ReCapProject.business.abstracts;

import java.util.List;

import com.example.ReCapProject.core.business.abstracts.BaseService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CorporateCustomer;
import com.example.ReCapProject.entities.requests.CreateCorporateCustomerRequest;
import com.example.ReCapProject.entities.requests.UpdateCorporateCustomerRequest;

public interface CorporateCustomerService extends BaseService<CreateCorporateCustomerRequest, Integer> {

	Result update(UpdateCorporateCustomerRequest entity);
	DataResult<List<CorporateCustomer>> getAll();
	
}
