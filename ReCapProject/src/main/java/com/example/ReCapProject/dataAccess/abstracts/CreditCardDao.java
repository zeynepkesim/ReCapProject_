package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.CreditCard;

@Repository
public interface CreditCardDao extends JpaRepository <CreditCard, Integer> {
	
//	@Query("Select new com.example.ReCapProject.entities.dtos.CreditCardDetailDto"
//	  		+ " (u.userId, c.cardBeholderName, c.creditCardNumber, c.cvvCode, c.expireDate)"
//	  		+ " From CreditCard c Inner Join c.user u"
//	  		+ " Where userId =: userId")
//	List<CreditCardDetailDto> getCreditCardDetails(int userId);
	
	CreditCard getByCreditCardNumber(String cardNumber);
	
	List<CreditCard> getByUser_UserId(int userId);

}
