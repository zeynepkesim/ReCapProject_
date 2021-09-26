package com.example.ReCapProject.api.controllers;

import java.util.List;

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
import com.example.ReCapProject.entities.requests.brand.CreateBrandRequest;
import com.example.ReCapProject.entities.requests.brand.DeleteBrandRequest;
import com.example.ReCapProject.entities.requests.brand.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin
public class BrandController {

	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@PostMapping("/addbrand")
	public Result add(CreateBrandRequest brand) {
		return this.brandService.add(brand);
	}
	
	@PostMapping("/updatebrand")
	public Result update(UpdateBrandRequest brand) {
		return this.brandService.update(brand);
	}
	
	@DeleteMapping("/deletebrand")
	public Result delete(DeleteBrandRequest brand) {
		return this.brandService.delete(brand);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Brand>> getAll() {
		return this.brandService.getAll();
	}
	
}
