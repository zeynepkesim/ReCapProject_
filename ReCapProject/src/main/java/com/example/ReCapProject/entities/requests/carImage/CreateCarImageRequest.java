package com.example.ReCapProject.entities.requests.carImage;

import org.springframework.web.multipart.MultipartFile;

import com.example.ReCapProject.core.entities.abstracts.Request;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImageRequest implements Request {

	@NotNull
	private int carId;
	
	
	@NotNull
	MultipartFile file;
	
}
