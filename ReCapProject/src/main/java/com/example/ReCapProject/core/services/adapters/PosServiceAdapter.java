package com.example.ReCapProject.core.services.adapters;

import org.springframework.stereotype.Service;

import com.example.ReCapProject.core.services.PosOutService;
import com.example.ReCapProject.core.services.abstracts.PosService;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.entities.requests.payment.CreatePaymentRequest;

@Service
public class PosServiceAdapter implements PosService{

	private PosOutService posOutService;
	
	public PosServiceAdapter() {
		this.posOutService = new PosOutService();
	}

	@Override
	public Result withdraw(CreatePaymentRequest entity) {
		if(this.posOutService.posService(entity.getCardBeholderName(), entity.getCarNumber(), entity.getCvvCode(), entity.getExpireDate(), entity.getPrice()))
			return new SuccessResult();
		
		return new ErrorResult();
	}

}
