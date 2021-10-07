package com.example.ReCapProject.core.services.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.constants.messages.CustomerMessages;
import com.example.ReCapProject.core.services.FindexOutService;
import com.example.ReCapProject.core.services.abstracts.FindexService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.CorporateCustomer;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;

@Service
public class FindexServiceAdapter implements FindexService {

	private FindexOutService findexService;
	
	
	@Autowired
	public FindexServiceAdapter() {
		
		this.findexService = new FindexOutService();
		
	}
	
	
	@Override
	public DataResult<Double> evaluateFindexPointForCorporateCustomer(String taxNumber) {
		return this.findexService.evaluateFindexPointForCorporate(taxNumber);
	}

	
	@Override
	public DataResult<Double> evaluateFindexPointForIndividualCustomer(String nationalIdNumber) {
		return this.findexService.evaluateFindexPointForIndividual(nationalIdNumber);
	}
	
	
	@Override
	public Result checkFindexPointsForCorporate(CorporateCustomer corporateCustomer, Car car) {
		if(evaluateFindexPointForCorporateCustomer(corporateCustomer.getTaxNumber()).getData() < car.getMinFindexPoint()) {		
			return new ErrorResult(CustomerMessages.CUSTOMER_FINDEX_POINT_IS_NOT_ENOUGH);
		}		
		return new SuccessResult();
	}
	
	
	@Override
	public Result checkFindexPointsForIndividual(IndividualCustomer individualCustomer, Car car) {
		if(evaluateFindexPointForIndividualCustomer(individualCustomer.getNationalIdNumber()).getData() < car.getMinFindexPoint()) {		
			return new ErrorResult(CustomerMessages.CUSTOMER_FINDEX_POINT_IS_NOT_ENOUGH);
		}		
		return new SuccessResult();
	}

}
