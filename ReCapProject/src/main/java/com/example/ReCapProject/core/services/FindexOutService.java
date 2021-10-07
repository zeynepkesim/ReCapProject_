package com.example.ReCapProject.core.services;

import com.example.ReCapProject.business.constants.messages.CustomerMessages;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;

//Simulation of an OutService

public class FindexOutService {

	public DataResult<Double> evaluateFindexPointForCorporate(String taxNumber) {
		if(taxNumber.length() == 10) {
			double evaluation = Math.random()*180;
			
			return new SuccessDataResult<Double>(evaluation);
		}		
		return new ErrorDataResult<Double>(CustomerMessages.TAX_NUMBER_IS_NOT_VALID);
	}
	
	public DataResult<Double> evaluateFindexPointForIndividual(String nationalIdNumber) {
		if(nationalIdNumber.length() == 11) {
			double evaluation = Math.random()*180;
			
			return new SuccessDataResult<Double>(evaluation);
		}
		return new ErrorDataResult<Double>(CustomerMessages.NATIONAL_ID_NUMBER_IS_NOT_VALID);
	}
	
}
