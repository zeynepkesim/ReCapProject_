package com.example.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReCapProject.business.abstracts.CreditCardService;
import com.example.ReCapProject.core.utilities.results.DataResult;
import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.concretes.CreditCard;
import com.example.ReCapProject.entities.dtos.CreditCardDto;
import com.example.ReCapProject.entities.requests.creditCard.CreateCreditCardRequest;
import com.example.ReCapProject.entities.requests.creditCard.DeleteCreditCardRequest;
import com.example.ReCapProject.entities.requests.creditCard.UpdateCreditCardRequest;

@RestController
@RequestMapping("/api/creditcards")
@CrossOrigin
public class CreditCardsController {

	private CreditCardService creditCardService;

	@Autowired
	public CreditCardsController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}
	
	@PostMapping("/addcreditcard")
	public Result add(CreateCreditCardRequest entity) {
		return this.creditCardService.add(entity);
	}
	
	@PostMapping("/updatecreditcard")
	public Result update(UpdateCreditCardRequest entity) {
		return this.creditCardService.update(entity);
	}
	
	@DeleteMapping("/deletecreditcard")
	public Result delete(DeleteCreditCardRequest entity) {
		return this.creditCardService.delete(entity);
	}
	
	@GetMapping("/getcreditcardsforuser")
	public DataResult<List<CreditCard>> getCreditCardForUser(int userId) {
		return this.creditCardService.getCreditCardsForUser(userId);
	}
	
	@GetMapping("/getcreditcarddetails")
	public DataResult<List<CreditCardDto>> getCreditCardDetails(int userId) {
		return this.creditCardService.getCreditCardDetails(userId);
	}
	
}
