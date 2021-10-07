package com.example.ReCapProject.business.abstracts;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.requests.payment.CreatePaymentRequest;
import com.example.ReCapProject.entities.requests.payment.CreatePaymentWithSavedCreditCardRequest;

@Repository
public interface PaymentService {

	Result add(CreatePaymentRequest entity);
	Result addWithSavedCreditCard(CreatePaymentWithSavedCreditCardRequest entity);
	
}
