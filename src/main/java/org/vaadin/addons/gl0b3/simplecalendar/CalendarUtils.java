package org.vaadin.addons.gl0b3.simplecalendar;

import java.util.List;

import elemental.json.Json;
import elemental.json.JsonArray;

/**
 * Collection for simple calendar related methods
 * @author Károly Kótay-Szabó (gl0b3)
 */
public class CalendarUtils {

	/**
	 * Convert {@link List<String>} dates into {@link JsonArray}
	 *
	 * @param stringDates {@link List<String>} dates which have to be converted into {@link JsonArray}
	 * @return the converted {@link JsonArray}
	 */
	public static JsonArray convertToJsonArray(List<String> stringDates) {
		JsonArray jsonDateArray = Json.createArray();
		for ( int i = 0; i < stringDates.size(); i++ ) {
			jsonDateArray.set(i, stringDates.get(i));
		}
		return jsonDateArray;
	}
}
