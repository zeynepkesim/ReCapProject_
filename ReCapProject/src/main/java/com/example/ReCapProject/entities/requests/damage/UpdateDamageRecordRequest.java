package com.example.ReCapProject.entities.requests.damage;

import com.example.ReCapProject.core.entities.abstracts.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRecordRequest implements Request {
	
	private int damageRecordId;
	
	private int carId;
	
	private String damageInfo;

}
