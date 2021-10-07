package com.example.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.BrandService;
import com.example.ReCapProject.business.constants.messages.BrandMessages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.BrandDao;
import com.example.ReCapProject.entities.concretes.Brand;
import com.example.ReCapProject.entities.dtos.BrandDetailDto;
import com.example.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.example.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.example.ReCapProject.entities.requests.brand.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	
	private ModelMapper modelMapper;
	
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapper modelMapper) {
		
		this.brandDao = brandDao;
		
		this.modelMapper = modelMapper;
		
	}

	
	@Override
	public Result add(CreateBrandRequest entity) {
		
		var result = BusinessRules.run(checkIfBrandExists(entity.getBrandName(), entity.getModelName(), entity.getModelYear()));
		
		if(result != null)
			return result;
		
		Brand brand = new Brand();
		brand.setBrandName(entity.getBrandName().toLowerCase().trim());
		brand.setModelName(entity.getModelName().toLowerCase().trim());
		brand.setModelYear(entity.getModelYear());
		
		this.brandDao.save(brand);
		return new SuccessResult(BrandMessages.BRAND_ADDED);
		
	}
	
	
	@Override
	public Result update(UpdateBrandRequest entity) {
		
		var result = BusinessRules.run(checkIfBrandExists(entity.getBrandName(), entity.getModelName(), entity.getModelYear()));
		
		if(result != null)
			return result;
		
		Brand brand = this.brandDao.getById(entity.getBrandId());
		brand.setBrandName(entity.getBrandName().toLowerCase().trim());
		brand.setModelName(entity.getModelName().toLowerCase().trim());
		brand.setModelYear(entity.getModelYear());
		
		this.brandDao.save(brand);
		return new SuccessResult(BrandMessages.BRAND_UPDATED);
		
	}
	
	
	@Override
	public Result delete(DeleteBrandRequest entity) {
		
		this.brandDao.deleteById(entity.getBrandId());
		return new SuccessResult(BrandMessages.BRAND_DELETED);
		
	}

	
	@Override
	public DataResult<List<Brand>> getAll() {
		
		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(), BrandMessages.BRANDS_LISTED);
		
	}
	
	
	@Override
	public DataResult<Brand> getById(int brandId) {
		
		return new SuccessDataResult<Brand>(this.brandDao.getById(brandId), BrandMessages.BRANDS_LISTED);
		
	}

	
	@Override
	public DataResult<List<BrandDetailDto>> getAllBrandDetails() {
		
		List<Brand> brands = this.brandDao.findAll();
		
		List<BrandDetailDto> brandDetailDtos = brands.stream()
													 .map(this::convertToDto)
								                     .collect(Collectors.toList());
		
		return new SuccessDataResult<List<BrandDetailDto>>(brandDetailDtos, BrandMessages.BRANDS_LISTED);
		
	}


	@Override
	public DataResult<BrandDetailDto> getDetailsById(int brandId) {
		
		Brand brand = this.brandDao.getById(brandId);
		
		return new SuccessDataResult<BrandDetailDto>(modelMapper.map(brand, BrandDetailDto.class), BrandMessages.BRANDS_LISTED);
		
	}
	
	
	private BrandDetailDto convertToDto(Brand brand) {
		
		BrandDetailDto brandDetailDto = modelMapper.map(brand, BrandDetailDto.class);
		
		return brandDetailDto;
		
	}
	
	
	private Result checkIfBrandExists(String brandName, String modelName, int modelYear) {
		
		if(this.brandDao.existsByBrandNameAndModelNameAndModelYear(brandName.toLowerCase().trim(), modelName.toLowerCase().trim(), modelYear)) {
			return new ErrorResult(BrandMessages.BRAND_ALREADY_EXISTS);
		}	
		return new SuccessResult();		
	}	

}
