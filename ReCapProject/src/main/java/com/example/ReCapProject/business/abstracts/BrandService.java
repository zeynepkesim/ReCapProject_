package com.example.ReCapProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Brand;
import com.example.ReCapProject.entities.dtos.BrandDetailDto;
import com.example.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.example.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.example.ReCapProject.entities.requests.brand.UpdateBrandRequest;

@Repository
public interface BrandService {
	
	Result add(CreateBrandRequest entity);
	Result update(UpdateBrandRequest entity);
	Result delete(DeleteBrandRequest entity);
	
	DataResult<List<Brand>> getAll();
	
	DataResult<Brand> getById(int brandId);
	
	DataResult<List<BrandDetailDto>> getAllBrandDetails();
	DataResult<BrandDetailDto> getDetailsById(int brandId);
	
}
