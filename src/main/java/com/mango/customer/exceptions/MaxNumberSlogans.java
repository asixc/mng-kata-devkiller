package com.mango.customer.exceptions;

public class MaxNumberSlogans extends RuntimeException {
	private final static String MSG = "Maximum number of slogans has been reached";

	public MaxNumberSlogans(String msg) {
		super(msg);
	}
	public MaxNumberSlogans(){
		super(MSG);
	}
}
