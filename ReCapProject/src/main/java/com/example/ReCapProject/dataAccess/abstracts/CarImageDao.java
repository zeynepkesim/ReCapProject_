package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.CarImage;

@Repository
public interface CarImageDao extends JpaRepository <CarImage, Integer> {

	int countByCar_CarId(int carId);
	
	List<CarImage> getByCar_CarId(int carId);
	
}
