package com.example.ReCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.BrandService;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.BrandDao;
import com.example.ReCapProject.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	@Override
	public Result add(Brand entity) {
		this.brandDao.save(entity);
		return new SuccessResult("Başarıyla eklendi.");
		
	}

	@Override
	public Result update(Brand entity) {
		this.brandDao.save(entity);
		return new SuccessResult("Başarıyla güncellendi.");
		
	}

	@Override
	public Result delete(Integer brandId) {
		this.brandDao.deleteById(brandId);
		return new SuccessResult("Başarıyla silindi.");		
	}
	
}
