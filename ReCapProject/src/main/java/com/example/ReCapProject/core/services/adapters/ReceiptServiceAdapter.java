package com.example.ReCapProject.core.services.adapters;

import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.ReceiptService;
import com.example.ReCapProject.core.services.CreateReceiptService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.entities.concretes.Receipt;
import com.example.ReCapProject.entities.requests.creditCard.CreateCreditCardRequest;
import com.example.ReCapProject.entities.requests.creditCard.CreditCardInfoRequest;
import com.example.ReCapProject.entities.requests.rental.CreateRentalRequest;
import com.example.ReCapProject.entities.requests.rental.RentalInfoRequest;

@Service
public class ReceiptServiceAdapter implements ReceiptService {

	private CreateReceiptService createReceiptService;
	private CarDao carDao;
	
	public ReceiptServiceAdapter(CarDao carDao) {
		this.carDao = carDao;
		createReceiptService = new CreateReceiptService();
	}

	@Override
	public DataResult<Receipt> createReceipt(CreateCreditCardRequest creditCard, CreateRentalRequest rental) {
		
		CreditCardInfoRequest creditCardInfo = new CreditCardInfoRequest();
		creditCardInfo.setCardBeholderName(creditCard.getCardBeholderName());
		creditCardInfo.setCardNumber(creditCard.getCardNumber());
		
		RentalInfoRequest rentalInfo = new RentalInfoRequest();
		rentalInfo.setBrandName(this.carDao.getById(rental.getCarId()).getBrand().getBrandName());
		rentalInfo.setModelName(this.carDao.getByCarId(rental.getCarId()).getBrand().getModelName());
		rentalInfo.setModelYear(this.carDao.getByCarId(rental.getCarId()).getBrand().getModelYear());
		rentalInfo.setRentDate(rental.getRentDate());
		
		return this.createReceiptService.createReceipt(creditCardInfo, rentalInfo);
		
	}

	

}
