package com.example.ReCapProject.business.abstracts;

import org.springframework.stereotype.Repository;

import com.example.ReCapProject.core.utilities.results.Result;
import com.example.ReCapProject.entities.requests.userLogin.UserLogInRequest;

@Repository
public interface LogInService {

	Result logIn(UserLogInRequest logInRequest);
	
}
