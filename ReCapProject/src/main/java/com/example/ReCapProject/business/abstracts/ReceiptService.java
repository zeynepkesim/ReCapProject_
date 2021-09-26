package com.example.ReCapProject.business.abstracts;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.entities.concretes.Receipt;
import com.example.ReCapProject.entities.requests.creditCard.CreateCreditCardRequest;
import com.example.ReCapProject.entities.requests.rental.CreateRentalRequest;

public interface ReceiptService {

	public DataResult<Receipt> createReceipt(CreateCreditCardRequest creditCard, CreateRentalRequest rental);
}
