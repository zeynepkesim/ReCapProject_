package com.example.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.AdditionalServiceService;
import com.example.ReCapProject.business.constants.Messages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.example.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	
	private AdditionalServiceDao additionalServiceDao;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao) {
		this.additionalServiceDao = additionalServiceDao;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest entity) {
		
		var result = BusinessRules.run(checkIfAdditionalServiceExists(entity.getAdditionalServiceName()));
		
		if(result != null)
			return result;
		
		AdditionalService additionalService = new AdditionalService();
		additionalService.setAdditionalServiceName(entity.getAdditionalServiceName());
		additionalService.setAdditionalServiceFee(entity.getAdditionalServiceFee());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_ADDED);
		
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest entity) {
		
		AdditionalService additionalService = this.additionalServiceDao.getById(entity.getAdditionalServiceId());
		additionalService.setAdditionalServiceFee(entity.getAdditionalServiceFee());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_UPDATED);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest entity) {
		
		this.additionalServiceDao.deleteById(entity.getAdditionalServiceId());
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_DELETED);
	}

	@Override
	public DataResult<List<AdditionalService>> getAll() {
		
		return new SuccessDataResult<List<AdditionalService>>(this.additionalServiceDao.findAll(), Messages.ADDITIONAL_SERVICES_LISTED);
				
	}
	
	@Override
	public DataResult<List<AdditionalService>> getAdditionalServicesByRentalId(int rentalId) {
		
		return new SuccessDataResult<List<AdditionalService>>(this.additionalServiceDao.getByRental_RentalId(rentalId), Messages.ADDITIONAL_SERVICES_LISTED);
				
	}
	
	private Result checkIfAdditionalServiceExists(String additionalServiceName) {
		
		for (AdditionalService additionalService : this.additionalServiceDao.getByAdditionalServiceName(additionalServiceName)) {
			if(additionalService.getAdditionalServiceName().equals(additionalServiceName)) 
				return new ErrorResult(Messages.ADDITIONAL_SERVICE_ALREADY_EXISTS);
			
		}
		
		return new SuccessResult();
	}

}
