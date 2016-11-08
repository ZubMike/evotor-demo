package ru.zubmike.demo.evotor.types.dto;

public enum RequestType {

	CREATE_USER("CREATE-AGT"),
	GET_BALANCE("GET-BALANCE");

	private final String value;

	private RequestType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static RequestType from(String value) {
		for (RequestType type : RequestType.values()) {
			if (type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}

}
