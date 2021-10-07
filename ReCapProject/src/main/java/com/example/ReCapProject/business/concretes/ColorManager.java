package com.example.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReCapProject.business.abstracts.ColorService;
import com.example.ReCapProject.business.constants.messages.ColorMessages;
import com.example.ReCapProject.core.utilities.business.BusinessRules;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.ErrorResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.core.utilities.results.SuccessDataResult;
import com.example.ReCapProject.core.utilities.results.SuccessResult;
import com.example.ReCapProject.dataAccess.abstracts.ColorDao;
import com.example.ReCapProject.entities.concretes.Color;
import com.example.ReCapProject.entities.dtos.ColorDetailDto;
import com.example.ReCapProject.entities.requests.color.CreateColorRequest;
import com.example.ReCapProject.entities.requests.color.DeleteColorRequest;
import com.example.ReCapProject.entities.requests.color.UpdateColorRequest;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	
	private ModelMapper modelMapper;
	
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapper modelMapper) {
		
		this.colorDao = colorDao;
		
		this.modelMapper = modelMapper;
		
	}

	
	@Override
	public Result add(CreateColorRequest entity) {
		
		var result = BusinessRules.run(checkIfColorExists(entity.getColorName()));
		
		if(result != null)
			return result;
		
		Color color = new Color();
		color.setColorName(entity.getColorName().toLowerCase().trim());
		
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.COLOR_ADDED);
		
	}
	
	
	@Override
	public Result update(UpdateColorRequest entity) {

		var result = BusinessRules.run(checkIfColorExists(entity.getColorName()));
		
		if(result != null)
			return result;
		
		Color color = this.colorDao.getByColorId(entity.getColorId());
		color.setColorName(entity.getColorName().toLowerCase().trim());
		
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.COLOR_UPDATED);
		
	}

	
	@Override
	public Result delete(DeleteColorRequest entity) {
		
		this.colorDao.deleteById(entity.getColorId());
		return new SuccessResult(ColorMessages.COLOR_DELETED);
		
	}

	
	@Override
	public DataResult<List<Color>> getAll() {
		
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), ColorMessages.COLORS_LISTED);
	
	}
	
	
	@Override
	public DataResult<Color> getById(int colorId) {
		
		return new SuccessDataResult<Color>(this.colorDao.getById(colorId), ColorMessages.COLORS_LISTED);
	
	}
	
	
	@Override
	public DataResult<List<ColorDetailDto>> getAllColorDetails() {
		
		List<Color> colors = this.colorDao.findAll();
		
		List<ColorDetailDto> colorDetailDtos = colors.stream()
													 .map(this::convertToDto)
								                     .collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorDetailDto>>(colorDetailDtos, ColorMessages.COLORS_LISTED);
	
	}

	
	@Override
	public DataResult<ColorDetailDto> getDetailsById(int colorId) {
		
		Color color = this.colorDao.getById(colorId);
		
		return new SuccessDataResult<ColorDetailDto>(modelMapper.map(color, ColorDetailDto.class), ColorMessages.COLORS_LISTED);
	
	}
	
	
	private ColorDetailDto convertToDto(Color color) {
		
		ColorDetailDto colorDetailDto = modelMapper.map(color, ColorDetailDto.class);
		
		return colorDetailDto;
		
	}


	private Result checkIfColorExists(String colorName) {
		
		if(this.colorDao.existsByColorName(colorName.toLowerCase().trim())) {
			return new ErrorResult(ColorMessages.COLOR_ALREADY_EXISTS);
		}		
		return new SuccessResult();	
	}

}
