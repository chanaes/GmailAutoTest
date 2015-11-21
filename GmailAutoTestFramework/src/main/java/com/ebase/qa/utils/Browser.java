package com.ebase.qa.utils;

/**
 *
 */
public enum Browser {

	CHROME("chrome"), IE("ie"), FIREFOX("firefox");

	private final String value;

	private Browser(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}

}
