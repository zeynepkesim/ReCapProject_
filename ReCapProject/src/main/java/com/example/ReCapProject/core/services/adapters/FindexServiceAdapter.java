package com.example.ReCapProject.core.services.adapters;

import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.FindexPointService;
import com.example.ReCapProject.core.services.FindexService;
import com.example.ReCapProject.core.utilities.results.DataResult;

@Service
public class FindexServiceAdapter implements FindexPointService {

	private FindexService findexService;
	
	public FindexServiceAdapter() {
		findexService = new FindexService();
	}
	
	@Override
	public DataResult<Double> evaluateFindexPointForCorporateCustomer(String taxNumber) {
		return this.findexService.evaluateFindexPointForCorporate(taxNumber);
	}

	@Override
	public DataResult<Double> evaluateFindexPointForIndividualCustomer(String nationalId) {
		return this.findexService.evaluateFindexPointForIndividual(nationalId);
	}

}
