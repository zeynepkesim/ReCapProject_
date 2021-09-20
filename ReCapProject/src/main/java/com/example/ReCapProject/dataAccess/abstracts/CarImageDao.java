package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.CarImage;

public interface CarImageDao extends JpaRepository<CarImage, Integer> {
	
	List<CarImage> getByCar_CarId(int carId);

	int countByCar_CarId(int carId);
	
}
