package com.example.ReCapProject.core.utilities.results;

public class ErrorDataResult<E> extends DataResult<E> {

	public ErrorDataResult(E data) {
		super(false, data);
	}
	
	public ErrorDataResult(String message) {
		super(false, message, null);
	}
	
	public ErrorDataResult(String message, E data) {
		super(false, message, data);
	}
	
	public ErrorDataResult() {
		super(false, null);
	}

}
