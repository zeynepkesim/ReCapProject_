package com.example.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.CityService;
import com.example.ReCapProject.business.constants.messages.CityMessages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.CityDao;
import com.example.ReCapProject.entities.concretes.City;
import com.example.ReCapProject.entities.dtos.CityDetailDto;
import com.example.ReCapProject.entities.requests.city.CreateCityRequest;
import com.example.ReCapProject.entities.requests.city.DeleteCityRequest;
import com.example.ReCapProject.entities.requests.city.UpdateCityRequest;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	
	private ModelMapper modelMapper;
	
		
	@Autowired
	public CityManager(CityDao cityDao, ModelMapper modelMapper) {
		
		this.cityDao = cityDao;
		
		this.modelMapper = modelMapper;
		
	}

	
	@Override
	public Result add(CreateCityRequest entity) {
		
		var result = BusinessRules.run(checkIfCityExists(entity.getCityName()));
		
		if(result != null)
			return result;
		
		City city = new City();
		city.setCityName(entity.getCityName().toLowerCase().trim());
		
		this.cityDao.save(city);
		return new SuccessResult(CityMessages.CITY_ADDED);
		
	}
	

	@Override
	public Result update(UpdateCityRequest entity) {
		
		var result = BusinessRules.run(checkIfCityExists(entity.getCityName()));
		
		if(result != null)
			return result;
		
		City city = this.cityDao.getById(entity.getCityId());
		city.setCityName(entity.getCityName().toLowerCase().trim());
		
		this.cityDao.save(city);
		return new SuccessResult(CityMessages.CITY_UPDATED);
		
	}
	

	@Override
	public Result delete(DeleteCityRequest entity) {
		
		this.cityDao.deleteById(entity.getCityId());
		return new SuccessResult(CityMessages.CITY_DELETED);
		
	}
	

	@Override
	public DataResult<List<City>> getAll() {
		
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), CityMessages.CITIES_LISTED);
	
	}
	
	
	@Override
	public DataResult<City> getById(int cityId) {
		
		return new SuccessDataResult<City>(this.cityDao.getById(cityId), CityMessages.CITIES_LISTED);
	
	}
	
	
	@Override
	public DataResult<List<CityDetailDto>> getAllCityDetails() {
		
		List<City> cities = this.cityDao.findAll();
		
		List<CityDetailDto> cityDetailDtos = cities.stream()
													 .map(this::convertToDto)
								                     .collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityDetailDto>>(cityDetailDtos, CityMessages.CITIES_LISTED);
		
	}
	
	
	@Override
	public DataResult<CityDetailDto> getDetailsById(int cityId) {
		
		City city = this.cityDao.getById(cityId);
		
		return new SuccessDataResult<CityDetailDto>(modelMapper.map(city, CityDetailDto.class), CityMessages.CITIES_LISTED);
	
	}
	
	
	private CityDetailDto convertToDto(City city) {
		
		CityDetailDto cityDetailDto = modelMapper.map(city, CityDetailDto.class);
		
		return cityDetailDto;
		
	}
		
	private Result checkIfCityExists(String cityName) {
		
		if(this.cityDao.existsByCityName(cityName.toLowerCase().trim())) {
			return new ErrorResult(CityMessages.CITY_ALREADY_EXISTS);
		}		
		return new SuccessResult();		
	}

}