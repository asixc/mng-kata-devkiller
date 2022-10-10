package com.mango.customer.exceptions;

public class SloganNotFoundException extends RuntimeException {
	private final static String MSG = "Slogan not found";

    public SloganNotFoundException(String msg) {
		super(msg);
    }
	public SloganNotFoundException(){
		super(MSG);
	}
}
