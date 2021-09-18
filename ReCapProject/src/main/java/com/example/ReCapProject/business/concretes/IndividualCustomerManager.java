package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.IndividualCustomerService;
import com.example.ReCapProject.core.constants.Messages;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.example.ReCapProject.entities.concretes.IndividualCustomer;
import com.example.ReCapProject.entities.requests.CreateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerDao individualCustomerDao;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao) {
		this.individualCustomerDao = individualCustomerDao;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest entity) {
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setFirstName(entity.getFirstName());
		individualCustomer.setLastName(entity.getLastName());
		individualCustomer.setEmail(entity.getEmail());
		individualCustomer.setPassword(entity.getPassword());
		
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMER_ADDED);
		
	}

	@Override
	public Result update(CreateIndividualCustomerRequest entity) {
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setFirstName(entity.getFirstName());
		individualCustomer.setLastName(entity.getLastName());
		individualCustomer.setEmail(entity.getEmail());
		individualCustomer.setPassword(entity.getPassword());
		individualCustomer.setUserId(entity.getIndividualCustomerId());
		
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMER_UPDATED);
	}

	@Override
	public Result delete(Integer userId) {
		this.individualCustomerDao.deleteById(userId);
		return new SuccessResult(Messages.CUSTOMER_DELETED);
	}

	@Override
	public DataResult<List<IndividualCustomer>> getAll() {
		return new SuccessDataResult<List<IndividualCustomer>>(this.individualCustomerDao.findAll(), Messages.CUSTOMERS_LISTED);
	}

}
