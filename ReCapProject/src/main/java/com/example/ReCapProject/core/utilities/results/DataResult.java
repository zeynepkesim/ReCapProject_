package com.example.ReCapProject.core.utilities.results;

public class DataResult<E> extends Result {
	
	private E data;
	
	public DataResult(boolean success, E data) {
		super(success);
		this.data = data;
	}
	
	public DataResult(boolean success, String message, E data) {
		super(success, message);
		this.data = data;		
	}

	public E getData() {
		return data;
	}	

}
