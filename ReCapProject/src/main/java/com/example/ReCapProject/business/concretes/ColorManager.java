package com.example.ReCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.ColorService;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.ColorDao;
import com.example.ReCapProject.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		this.colorDao = colorDao;
	}

	@Override
	public Result add(Color entity) {
		this.colorDao.save(entity);
		return new SuccessResult("Başarıyla eklendi.");
		
	}

	@Override
	public Result update(Color entity) {
		this.colorDao.save(entity);
		return new SuccessResult("Başarıyla güncellendi.");
		
	}

	@Override
	public Result delete(Integer colorId) {
		this.colorDao.deleteById(colorId);
		return new SuccessResult("Başarıyla silindi.");
	}

}
