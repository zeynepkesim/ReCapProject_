package com.example.ReCapProject.entities.requests.additionalService;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest implements Request {
	
	private int additionalServiceId;
	private double additionalServiceFee;

}
