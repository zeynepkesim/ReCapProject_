package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CreditCardService;
import com.example.ReCapProject.business.abstracts.FindexPointService;
import com.example.ReCapProject.business.abstracts.ReceiptService;
import com.example.ReCapProject.business.abstracts.RentalService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorDataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.example.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.example.ReCapProject.dataAccess.abstracts.ReceiptDao;
import com.example.ReCapProject.dataAccess.abstracts.RentalDao;
import com.example.ReCapProject.entities.concretes.ApplicationUser;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.CorporateCustomer;
import com.example.ReCapProject.entities.concretes.CreditCard;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;
import com.example.ReCapProject.entities.concretes.Receipt;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.creditCard.CreateCreditCardRequest;
import com.example.ReCapProject.entities.requests.rental.CreateRentalRequest;
import com.example.ReCapProject.entities.requests.rental.DeleteRentalRequest;
import com.example.ReCapProject.entities.requests.rental.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarDao carDao;
	private CorporateCustomerDao corporateCustomerDao;
	private IndividualCustomerDao individualCustomerDao;
	private ReceiptDao receiptDao;
	private ApplicationUserDao applicationUserDao;
	
	private FindexPointService findexPointService;
	private CreditCardService creditCardService;
	private ReceiptService receiptService;
	
	
	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao, CorporateCustomerDao corporateCustomerDao, IndividualCustomerDao individualCustomerDao, 
			ReceiptDao receiptDao, ApplicationUserDao applicationUserDao, 
			FindexPointService findexPointService, CreditCardService creditCardService, ReceiptService receiptService) {
		
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.corporateCustomerDao = corporateCustomerDao;
		this.individualCustomerDao = individualCustomerDao;
		this.receiptDao = receiptDao;
		this.applicationUserDao = applicationUserDao;
		
		this.findexPointService = findexPointService;
		this.creditCardService = creditCardService;
		this.receiptService = receiptService;
		
	}

	@Override
	public Result addForCorporate(CreateRentalRequest entity) {
		var result = BusinessRules.run(checkIfCarIsAvailable(), 
				checkFindexPointsForCorporate(corporateCustomerDao.getById(entity.getUserId()), carDao.getById(entity.getCarId())),
				checkCarsPickUpCity(entity.getCarId(), entity.getPickUpCity()));
		
		if(result != null)
			return result;
		
		if(checkIfThereIsSavedCreditCard(entity).isSuccess())
			return new SuccessResult(Messages.RENTAL_ADDED);
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setAvailable(false);
		car.setCity(entity.getReturnCity());
		
		ApplicationUser user = this.applicationUserDao.getById(entity.getUserId());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setRentDate(entity.getRentDate());
		rental.setReturnDate(entity.getReturnDate());
		
		CreateCreditCardRequest creditCard = new CreateCreditCardRequest();
		creditCard.setCardBeholderName(entity.getCardBeholderName());
		creditCard.setCardName(entity.getCardName());
		creditCard.setCardNumber(entity.getCardNumber());
		creditCard.setCvvCode(entity.getCvvCode());
		creditCard.setExpireDate(entity.getExpireDate());
		creditCard.setUserId(entity.getUserId());
		creditCard.setSaveCard(entity.isSaveCard());
		
		if (!creditCardService.add(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_IS_INVALID);
		
		// Can't be null if there is no saved card
		if(!checkCardInfo(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_INFO_IS_NULL);
		
		
		receiptService.createReceipt(creditCard, entity);
		
		this.rentalDao.save(rental);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RENTAL_ADDED);
	}
	
	@Override
	public Result addForIndividual(CreateRentalRequest entity) {
		var result = BusinessRules.run(checkIfCarIsAvailable(), 
				checkFindexPointsForIndividual(individualCustomerDao.getById(entity.getUserId()), carDao.getByCarId(entity.getCarId())),
				checkCarsPickUpCity(entity.getCarId(), entity.getPickUpCity()));
						
		
		if(result != null)
			return result;
		
		if(checkIfThereIsSavedCreditCard(entity).isSuccess())
			return new SuccessResult(Messages.RENTAL_ADDED);
		
		Car car = this.carDao.getByCarId(entity.getCarId());
		car.setAvailable(false);
		car.setCity(entity.getReturnCity());
		
		ApplicationUser user = this.applicationUserDao.getById(entity.getUserId());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setRentDate(entity.getRentDate());
		rental.setReturnDate(entity.getReturnDate());
		
		CreateCreditCardRequest creditCard = new CreateCreditCardRequest();
		creditCard.setCardBeholderName(entity.getCardBeholderName());
		creditCard.setCardName(entity.getCardName());
		creditCard.setCardNumber(entity.getCardNumber());
		creditCard.setCvvCode(entity.getCvvCode());
		creditCard.setExpireDate(entity.getExpireDate());
		creditCard.setUserId(entity.getUserId());
		creditCard.setSaveCard(entity.isSaveCard());
		
		creditCardService.add(creditCard);
		
		if(!checkCardInfo(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_INFO_IS_NULL);
		
		Receipt receipt = receiptService.createReceipt(creditCard, entity).getData();
		
		this.receiptDao.save(receipt);
		this.rentalDao.save(rental);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RENTAL_ADDED);
	}

	@Override
	public Result update(UpdateRentalRequest entity) {
		
		Rental rental = this.rentalDao.getById(entity.getRentalId());
		rental.setReturnDate(entity.getReturnDate());
		
		Car car = rental.getCar();
		car.setAvailable(entity.isReturned());
		car.setCity(entity.getReturnCity());
		car.setCurrentKilometer(entity.getReturnKilometer());
	
		this.carDao.save(car);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL_UPDATED);
	}

	@Override
	public Result delete(DeleteRentalRequest entity) {
		this.rentalDao.deleteById(entity.getRentalId());
		return new SuccessResult(Messages.RENTAL_DELETED);
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), Messages.RENTALS_LISTED);
	}
	
	@Override
	public DataResult<List<RentalDto>> getCarAndRentalDetails() {
		return new SuccessDataResult<List<RentalDto>>(this.rentalDao.getCarAndRentalDetail(), Messages.RENTAL_DETAILS_LISTED);
	}
	
	@Override
	public DataResult<List<Rental>> getOpenRentals() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.getByCar_IsAvailableIsFalse(), Messages.RENTALS_LISTED);
	}
	
	@Override
	public DataResult<List<Rental>> getClosedRentals() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.getByCar_IsAvailableIsTrue(), Messages.RENTALS_LISTED);
	}
	
	private Result checkIfCarIsAvailable() {
		if(this.carDao.existsByIsAvailableIsTrue()) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.CAR_IS_NOT_RETURNED);
	}
	
	private Result checkFindexPointsForCorporate(CorporateCustomer corporateCustomer, Car car) {
		if(findexPointService.evaluateFindexPointForCorporateCustomer(corporateCustomer.getTaxNumber()).getData() < car.getMinFindexPoint())
			return new ErrorResult(Messages.CUSTOMER_FINDEX_POINT_IS_NOT_ENOUGH);
		
		return new SuccessResult();
	}
	
	private Result checkFindexPointsForIndividual(IndividualCustomer individualCustomer, Car car) {
		if(findexPointService.evaluateFindexPointForIndividualCustomer(individualCustomer.getNationalIdNumber()).getData() < car.getMinFindexPoint())
			return new ErrorResult(Messages.CUSTOMER_FINDEX_POINT_IS_NOT_ENOUGH);
		
		return new SuccessResult();
	}
	
	// One person can have more than one saved credit card! This function returns the saved card with it's unique name.
	private DataResult<CreditCard> returnCreditCardForUser(int userId, String cardName) {
		for (CreditCard card : applicationUserDao.getById(userId).getCreditCards()) {
			if(card.getCardName().equals(cardName)) {
				return new SuccessDataResult<CreditCard>(card);
			}
		}
		return new ErrorDataResult<CreditCard>();
	}
	
	private Result checkIfThereIsSavedCreditCard(CreateRentalRequest entity) {
		
		if(returnCreditCardForUser(entity.getUserId(), entity.getCardName()).isSuccess()) {
			
			Car car = this.carDao.getByCarId(entity.getCarId());
			car.setAvailable(false);
			
			ApplicationUser user = this.applicationUserDao.getById(entity.getUserId());
			
			Rental rental = new Rental();
			rental.setCar(car);
			rental.setUser(user);
			rental.setRentDate(entity.getRentDate());
			rental.setReturnDate(entity.getReturnDate());
			
			CreditCard creditCard = returnCreditCardForUser(entity.getUserId(), entity.getCardName()).getData();
			
			CreateCreditCardRequest creditCardRequest = new CreateCreditCardRequest();
			creditCardRequest.setCardBeholderName(creditCard.getCardBeholderName());
			creditCardRequest.setCardNumber(creditCard.getCreditCardNumber());
			
			Receipt receipt = receiptService.createReceipt(creditCardRequest, entity).getData();
			this.receiptDao.save(receipt);
			
			this.rentalDao.save(rental);
			this.carDao.save(car);
			return new SuccessResult();
		}
		return new ErrorResult();
	}
	
	private Result checkCardInfo(CreateCreditCardRequest card) {
		if(card.getCardName().isBlank() && card.getCardNumber().isBlank() && card.getCvvCode().isBlank() && card.getExpireDate().isBlank())	
			return new ErrorResult();

		return new SuccessResult();
	}
	
	private Result checkCarsPickUpCity(int carId, String pickUpCity) {
		Car car = this.carDao.getByCarId(carId);
		if(!car.getCity().equals(pickUpCity))
			return new ErrorResult(Messages.CAR_IS_NOT_IN_THE_CITY);
		
		return new SuccessResult();
	}
	
}
