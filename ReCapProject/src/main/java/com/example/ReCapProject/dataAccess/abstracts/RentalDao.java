package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository <Rental, Integer> {
	
//	@Query("Select new com.example.ReCapProject.entities.dtos.RentalDetailDto"
//			+ " (r.rentalId, r.rentDate, r.returnDate, r.pickUpKilometer, r.returnKilometer, b.brandName, b.modelName, b.modelYear, r.isPayed)"
//			+ " From Rental r Inner Join r.car c"
//			+ " Inner Join c.brand b")
//	List<RentalDetailDto> getRentalDetails();
	
	List<Rental> getByIsPayedIsTrueAndIsReturnedIsFalse();
	List<Rental> getByIsPayedIsTrueAndIsReturnedIsTrue();
	
}
