package com.example.ReCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.BrandService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.Brand;
import com.example.ReCapProject.entities.dtos.BrandDetailDto;
import com.example.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.example.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.example.ReCapProject.entities.requests.brand.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin
public class BrandsController {

	private BrandService brandService;
	

	@Autowired
	public BrandsController(BrandService brandService) {
		
		this.brandService = brandService;
		
	}
	
	
	@PostMapping("/add")
	public Result add(@Valid CreateBrandRequest brand) {		
		return this.brandService.add(brand);		
	}
	
	
	@PostMapping("/update")
	public Result update(@Valid UpdateBrandRequest brand) {	
		return this.brandService.update(brand);	
	}
	
	
	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteBrandRequest brand) {
		return this.brandService.delete(brand);
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<Brand>> getAll() {
		return this.brandService.getAll();
	}
	
	
	@GetMapping("/getbyid")
	public DataResult<Brand> getById(int brandId) {
		return this.brandService.getById(brandId);
	}
	
	
	@GetMapping("/getallbranddetails")
	public DataResult<List<BrandDetailDto>> getAllBrandDetails() {
		return this.brandService.getAllBrandDetails();
	}
	
	
	@GetMapping("/getdetailsbyid")
	public DataResult<BrandDetailDto> getDetailsById(int brandId) {
		return this.brandService.getDetailsById(brandId);
	}
	
}
