package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.Car;
import com.example.ReCapProject.entities.dtos.CarDetailDto;

@Repository
public interface CarDao extends JpaRepository<Car, Integer>{
	
	Car getByCarId(int carId);
	
	@Query("Select new com.example.ReCapProject.entities.dtos.CarDetailDto"
	  		+ " (c.modelName, b.brandName, cl.colorName, c.dailyPrice)"
	  		+ " From Car c Inner Join c.brand b"
			+ " Inner Join c.color cl")
	List<CarDetailDto> getCarsDetail();
	
	
}