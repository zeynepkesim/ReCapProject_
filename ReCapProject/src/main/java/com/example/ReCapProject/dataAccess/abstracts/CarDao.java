package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository <Car, Integer> {
	
//	@Query("Select new com.example.ReCapProject.entities.dtos.CarDetailDto"
//	  		+ " (c.isAvailable, c.dailyPrice, b.brandName, b.modelName, b.modelYear, co.colorName, ci.cityName)"
//	  		+ " From Car c Inner Join c.brand b"
//			+ " Inner Join c.color co"
//			+ " Inner Join c.city ci" )
//	List<CarDetailDto> getCarDetails();
	
	List<Car> getByBrand_BrandName(String brandName);
	List<Car> getByColor_ColorName(String colorName);
	List<Car> getByCity_CityName(String cityName);
	
}
