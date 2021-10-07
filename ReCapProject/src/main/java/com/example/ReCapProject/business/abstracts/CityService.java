package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.City;
import com.example.ReCapProject.entities.dtos.CityDetailDto;
import com.example.ReCapProject.entities.requests.city.CreateCityRequest;
import com.example.ReCapProject.entities.requests.city.DeleteCityRequest;
import com.example.ReCapProject.entities.requests.city.UpdateCityRequest;

@Repository
public interface CityService {

	Result add(CreateCityRequest entity);
	Result update(UpdateCityRequest entity);
	Result delete(DeleteCityRequest entity);
	
	DataResult<List<City>> getAll();
	
	DataResult<City> getById(int cityId);
	
	DataResult<List<CityDetailDto>> getAllCityDetails();
	DataResult<CityDetailDto> getDetailsById(int cityId); 
		
}
