package com.example.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.CorporateCustomer;

@Repository
public interface CorporateCustomerDao extends JpaRepository <CorporateCustomer, Integer> {

//	@Query("Select new com.example.ReCapProject.entities.dtos.CorporateCustomerDetailDto"
//	  		+ " (cc.userId, cc.taxNumber, cc.companyName, cc.email)"
//	  		+ " From CorporateCustomer cc")
//	List<CorporateCustomerDetailDto> getCorporateCustomerDetails();
	
	boolean existsByEmail(String email);
	boolean existsByTaxNumber(String taxNumber);
	
}
