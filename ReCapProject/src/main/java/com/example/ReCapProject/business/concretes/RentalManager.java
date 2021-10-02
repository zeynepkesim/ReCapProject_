package com.example.ReCapProject.business.concretes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CreditCardService;
import com.example.ReCapProject.business.abstracts.InvoiceService;
import com.example.ReCapProject.business.abstracts.RentalService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.services.abstracts.FindexService;
import com.example.ReCapProject.core.services.abstracts.PosService;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.example.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.example.ReCapProject.dataAccess.abstracts.CarDao;
import com.example.ReCapProject.dataAccess.abstracts.CityDao;
import com.example.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.example.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.example.ReCapProject.dataAccess.abstracts.RentalDao;
import com.example.ReCapProject.entities.abstracts.ApplicationUser;
import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.concretes.City;
import com.example.ReCapProject.entities.concretes.Rental;
import com.example.ReCapProject.entities.dtos.RentalDto;
import com.example.ReCapProject.entities.requests.creditCard.CreateCreditCardRequest;
import com.example.ReCapProject.entities.requests.invoice.CreateInvoiceRequest;
import com.example.ReCapProject.entities.requests.payment.CreatePaymentRequest;
import com.example.ReCapProject.entities.requests.rental.CreateRentalRequest;
import com.example.ReCapProject.entities.requests.rental.DeleteRentalRequest;
import com.example.ReCapProject.entities.requests.rental.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarDao carDao;
	private CorporateCustomerDao corporateCustomerDao;
	private IndividualCustomerDao individualCustomerDao;
	private ApplicationUserDao applicationUserDao;
	private CityDao cityDao;
	private AdditionalServiceDao additionalServiceDao;
	
	private FindexService findexPointService;
	private CreditCardService creditCardService;
	private InvoiceService invoiceService;
	private PosService posService;
	
	
	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao, CorporateCustomerDao corporateCustomerDao, CityDao cityDao,
			IndividualCustomerDao individualCustomerDao, ApplicationUserDao applicationUserDao, AdditionalServiceDao additionalServiceDao,
			FindexService findexPointService, CreditCardService creditCardService, InvoiceService invoiceService, PosService posService) {
		
		this.carDao = carDao;
		this.cityDao = cityDao;
		this.rentalDao = rentalDao;
		this.corporateCustomerDao = corporateCustomerDao;
		this.individualCustomerDao = individualCustomerDao;
		this.applicationUserDao = applicationUserDao;
		this.additionalServiceDao = additionalServiceDao;
		
		this.findexPointService = findexPointService;
		this.creditCardService = creditCardService;
		this.invoiceService = invoiceService;	
		this.posService = posService;
	}
	

	@Override
	public Result addForCorporate(CreateRentalRequest entity) {
		
		var result = BusinessRules.run(checkIfCarIsAvailable(), 
				checkCarsPickUpCityIsAvailable(entity.getCarId(), entity.getPickUpCityId()), 
				checkCarReturnCityIsAvailable(entity.getReturnCityId()),
				findexPointService.checkFindexPointsForCorporate(corporateCustomerDao.getById(entity.getUserId()), carDao.getById(entity.getCarId())));

		
		if(result != null)
			return result;

		
		Car car = this.carDao.getById(entity.getCarId());
		car.setAvailable(false);
		
		ApplicationUser user = this.applicationUserDao.getById(entity.getUserId());
		
		AdditionalService additionalService = this.additionalServiceDao.getById(entity.getAdditionalServiceId());
		
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate rentDate = LocalDate.parse(entity.getRentDate(), dateFormat);
		LocalDate returnDate = LocalDate.parse(entity.getReturnDate(), dateFormat);
		    
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setAdditionalService(additionalService);
		rental.setRentDate(rentDate);
		rental.setReturnDate(returnDate);
		rental.setPickUpKilometer(car.getCurrentKilometer());
		rental.setRentalPrice(calculatePriceForReturnDate(entity).getData());
		
		
		//---------------------
		CreateCreditCardRequest creditCard = new CreateCreditCardRequest();
		creditCard.setCardBeholderName(entity.getCardBeholderName());
		creditCard.setCardName(entity.getCardName());
		creditCard.setCardNumber(entity.getCardNumber());
		creditCard.setCvvCode(entity.getCvvCode());
		creditCard.setExpireDate(entity.getExpireDate());
		creditCard.setSaveCard(entity.isSaveCard());
		creditCard.setUserId(entity.getUserId());
		
		if (!creditCardService.add(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_IS_INVALID);
		//---------------------
		
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
		createPaymentRequest.setCardBeholderName(creditCard.getCardBeholderName());
		createPaymentRequest.setCarNumber(creditCard.getCardNumber());
		createPaymentRequest.setCvvCode(creditCard.getCvvCode());
		createPaymentRequest.setExpireDate(creditCard.getExpireDate());
		createPaymentRequest.setPrice(rental.getRentalPrice());
		
		if(!this.posService.withdraw(createPaymentRequest).isSuccess())
			return new ErrorResult(Messages.BALANCE_IS_NOT_ENOUGH);
		
		// Fields can't be null if there is no saved card
		if(!creditCardService.checkCardInfo(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_INFO_IS_NULL);
		
		this.rentalDao.save(rental);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RENTAL_ADDED);
	}
	
	
	@Override
	public Result addForIndividual(CreateRentalRequest entity) {
		
		var result = BusinessRules.run(checkIfCarIsAvailable(), 
				findexPointService.checkFindexPointsForIndividual(individualCustomerDao.getById(entity.getUserId()), carDao.getById(entity.getCarId())),
				checkCarsPickUpCityIsAvailable(entity.getCarId(), entity.getPickUpCityId()), checkCarReturnCityIsAvailable(entity.getReturnCityId()));
						
		
		if(result != null)
			return result;
	
		
		Car car = this.carDao.getById(entity.getCarId());
		car.setAvailable(false);
		
		ApplicationUser user = this.applicationUserDao.getById(entity.getUserId());
		
		AdditionalService additionalService = this.additionalServiceDao.getById(entity.getAdditionalServiceId());
		
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate rentDate = LocalDate.parse(entity.getRentDate(), dateFormat);
		LocalDate returnDate = LocalDate.parse(entity.getReturnDate(), dateFormat);
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setUser(user);
		rental.setAdditionalService(additionalService);
		rental.setRentDate(rentDate);
		rental.setReturnDate(returnDate);
		rental.setPickUpKilometer(car.getCurrentKilometer());
		rental.setRentalPrice(calculatePriceForReturnDate(entity).getData());
		
		
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
				
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
		createPaymentRequest.setCardBeholderName(creditCard.getCardBeholderName());
		createPaymentRequest.setCarNumber(creditCard.getCardNumber());
		createPaymentRequest.setCvvCode(creditCard.getCvvCode());
		createPaymentRequest.setExpireDate(creditCard.getExpireDate());
		createPaymentRequest.setPrice(rental.getRentalPrice());
		
		if(!this.posService.withdraw(createPaymentRequest).isSuccess())
			return new ErrorResult(Messages.BALANCE_IS_NOT_ENOUGH);
		
		if(!creditCardService.checkCardInfo(creditCard).isSuccess())
			return new ErrorResult(Messages.CREDIT_CARD_INFO_IS_NULL);
		
		
		this.rentalDao.save(rental);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RENTAL_ADDED);
	}
	

	@Override
	public Result update(UpdateRentalRequest entity) {
		
		var result = BusinessRules.run(checkCarReturnCityIsAvailable(entity.getReturnCityId()));
		
		if(result != null)
			return result;
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate returnDate = LocalDate.parse(entity.getReturnDate(), dateFormat);
		
		Rental rental = this.rentalDao.getById(entity.getRentalId());
		rental.setReturnKilometer(entity.getReturnKilometer());
		rental.setReturnDate(returnDate);
		
		Car car = rental.getCar();
		car.setCity(this.cityDao.getById(entity.getReturnCityId()));
		car.setAvailable(entity.isReturned());
		car.setCurrentKilometer(entity.getReturnKilometer());
		
		var result2 = BusinessRules.run(checkCarIsReturned(entity));	// When car is returned; the Invoice is being created automatically.
		
		if(result2 != null)
			return result2;
		
		cashBackForUnindicatedReturnDate(entity);
		
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		createInvoiceRequest.setRentalId(entity.getRentalId());
		
		this.invoiceService.add(createInvoiceRequest);
		
		this.carDao.save(car);
		this.rentalDao.save(rental);
		
		return new SuccessResult(Messages.RENTAL_UPDATED);
	}
	

	@Override
	public Result delete(DeleteRentalRequest entity) {
		
		Car car = this.rentalDao.getById(entity.getRentalId()).getCar();
		car.setAvailable(true);
		
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
		
		return new ErrorResult(Messages.CAR_IS_NOT_AVAILABLE);
	}
	
	
	private Result checkCarIsReturned(UpdateRentalRequest rental) {
		
		if(rental.isReturned()) 
			return new SuccessResult();
		
		return new ErrorResult(Messages.CAR_IS_NOT_RETURNED);
	}
	
		
	private Result checkCarsPickUpCityIsAvailable(int carId, int pickUpCityId) {
		
		Car car = this.carDao.getById(carId);
		if(car.getCity() != this.cityDao.getById(pickUpCityId))
			return new ErrorResult(Messages.CAR_IS_NOT_IN_THE_CITY);
		
		return new SuccessResult();
	}
	
	
	private Result checkCarReturnCityIsAvailable(int returnCityId) {
		
		for(City city : this.cityDao.findAll()) {
			if(city.getClass() != this.cityDao.getById(returnCityId).getClass()) {
				return new ErrorResult(Messages.CITY_SERVICE_IS_NOT_AVAILABLE);
			}
		}
		return new SuccessResult();
	}
	
	
	private DataResult<Double> calculatePriceForReturnDate(CreateRentalRequest entity) {
		
		if(entity.getReturnDate() == null) {
			double deposit = (this.carDao.getById(entity.getCarId()).getDailyPrice() * 90);
			return new SuccessDataResult<Double>(deposit);
		}
		double totalPrice =(rentalPriceCalculator(entity).getData() + calculateIfCarReturnedToDifferentCity(entity).getData()
							+ additionalServiceFeeCalculator(entity).getData());
		return new SuccessDataResult<Double>(totalPrice);
	}
	
	
	private Result cashBackForUnindicatedReturnDate(UpdateRentalRequest entity) {
		
		if(rentalDao.getById(entity.getRentalId()).getReturnDate() == null) {	// Get rentals returnDate before it's saved to dataBase.
			if(entity.getReturnDate() != null) {
				System.out.println("Additional fee has been charged for Open-Rent and remaining cash has been sent to User.");
				return new SuccessResult();
			}
		}
		return null;
	}
	
	
	private DataResult<Double> calculateIfCarReturnedToDifferentCity(CreateRentalRequest entity) {
		if(this.carDao.getById(entity.getCarId()).getCity() != this.cityDao.getById(entity.getReturnCityId()))
			return new SuccessDataResult<Double>(500.0);
		
		return new SuccessDataResult<Double>(0.0);
	}
	
	
	private DataResult<Double> rentalPriceCalculator(CreateRentalRequest entity) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate rentDate = LocalDate.parse(entity.getRentDate(), dateFormat);
		LocalDate returnDate = LocalDate.parse(entity.getReturnDate(), dateFormat);
		
		long days = ChronoUnit.DAYS.between(rentDate, returnDate);
		
		Car car = this.carDao.getById(entity.getCarId());
		
		return new SuccessDataResult<Double>((car.getDailyPrice() * days));			
	}
	
	private DataResult<Double> additionalServiceFeeCalculator(CreateRentalRequest entity) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate rentDate = LocalDate.parse(entity.getRentDate(), dateFormat);
		LocalDate returnDate = LocalDate.parse(entity.getReturnDate(), dateFormat);
		
		long days = ChronoUnit.DAYS.between(rentDate, returnDate);
		
		AdditionalService additionalService = this.additionalServiceDao.getById(entity.getAdditionalServiceId());
		
		return new SuccessDataResult<Double>(additionalService.getAdditionalServiceFee() * days);
		
	}
	
}
