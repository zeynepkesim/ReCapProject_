package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.AdditionalService;
import com.example.ReCapProject.entities.dtos.AdditionalServiceDto;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
	
	List<AdditionalService> getByRental_RentalId(int rentalId);
	
	List<AdditionalService> getByAdditionalServiceName(String additionalServiceName);
	
	@Query("Select new com.example.ReCapProject.entities.dtos.AdditionalServiceDto"
	  		+ " (r.rentalId, a.additionalServiceName, a.additionalServiceFee)"
	  		+ " From AdditionalService a Inner Join a.rental r")
	List<AdditionalServiceDto> getAdditionalServiceDetails();

}
