package com.example.ReCapProject.entities.requests.rental;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ReCapProject.core.entities.abstracts.Request;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest implements Request{
	
	@NotNull
	private int carId;
	
	@NotNull
	private int userId;
	
	@NotNull
	@NotBlank
	private String cardName;
	
	@NotBlank
	@NotNull
	private String cardBeholderName;
	
	@Nullable
	@Length(min = 16, max = 16)
	private String cardNumber;
	
	@Nullable
	@Length(min = 3, max = 3)
	private String cvvCode;
	
	@Nullable
	@DateTimeFormat(pattern = "MM/yyyy")
	private String expireDate;
	
	@Nullable
	private boolean saveCard;
	
	@NotNull
	private Date rentDate;
	
	@NotNull
	private Date returnDate;
	
}
