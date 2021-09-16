package com.example.ReCapProject.core.utilities.results;

public class SuccessDataResult<E> extends DataResult<E> {

	public SuccessDataResult(E data) {
		super(true, data);
	}
	 
	public SuccessDataResult(String message) {
		super(true, message, null);
	}
	
	public SuccessDataResult(String message, E data) {
		super(true, message, data);
	}
	
	public SuccessDataResult() {
		super(true, null);
	}

}
