package ru.zubmike.demo.evotor.types.dto;

public enum ResponseCode {
	
	OK(0),
	USER_ALREADY_EXISTS(1),
	INTERNAL_ERROR(2),
	USER_NOT_EXISTS(3),
	WRONG_PASSWORD(4);
	
	private final int value;

	private ResponseCode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}
