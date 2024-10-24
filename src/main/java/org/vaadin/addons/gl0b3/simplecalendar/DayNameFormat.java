package org.vaadin.addons.gl0b3.simplecalendar;

/**
 * The naming format of the days and month.
 * It follows the Luxon weekdays format: https://moment.github.io/luxon/api-docs/index.html#infoweekdays
 * @author Károly Kótay-Szabó (gl0b3)
 */
public enum DayNameFormat {
	/**
	 * the shortest format
	 */
	NARROW("narrow"),
	/**
	 * short format
	 */
	SHORT("short"),
	/**
	 * long format
	 */
	LONG("long");

	/**
	 * Holding the {@link String} value of the day name format
	 */
	private final String value;

	DayNameFormat(String value) {
		this.value = value;
	}

	/**
	 * Gives back the {@link String} value of the format
	 *
	 * @return the {@link String} value of the format
	 */
	public String getValue() {
		return this.value;
	}
}