package org.vaadin.addons.gl0b3.simplecalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import elemental.json.Json;
import elemental.json.JsonArray;

@Tag("year-calendar")
@JsModule("@gl0b3/simple-calendar")
@NpmPackage(value = "@gl0b3/simple-calendar", version = "1.0.1")
public class YearCalendar extends Component implements HasTheme, HasElement {

	private int year;
	private String locale;
	private boolean yearIsFirst;
	private boolean showOtherMonthDays;
	private WeekdayType weekdayType;

	public YearCalendar() {
	}

	public YearCalendar(int year, String locale, boolean yearIsFirst) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
	}

	/**
	 * The Year Calendar (and the Month Calendar) based on this year
	 *
	 * @param year {@link int}, ie: 2024
	 * @return {@link YearCalendar} this object
	 */
	public YearCalendar setYear(int year) {
		this.year = year;
		return this;
	}

	/**
	 * The locale of the Year Calendar (and of the Month Calendar). It's important for the naming and formatting convetions.
	 * <p>
	 * For supported locales see: <a href="https://github.com/moment/luxon/blob/master/docs/intl.md">Luxon Intl.md docs</a>
	 *
	 * @param locale {@link String} the desired language locale, ie: "en", "de", "hu", etc.
	 * @return {@link YearCalendar} this object
	 */
	public YearCalendar setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * The order of the year and month display in the month calendar header.
	 * If <code>true</code>, the year is first, month is the second, <code>false</code> the opposite order
	 *
	 * @param yearIsFirst {@link boolean} year-month or month-year order
	 * @return {@link YearCalendar} this object
	 */
	public YearCalendar setYearIsFirst(boolean yearIsFirst) {
		this.yearIsFirst = yearIsFirst;
		return this;
	}

	/**
	 * In a month the week's starting or ending days can be from the previous or the next month.
	 * If <code>true</code> the whole weeks will be shown, <code>false</code> only days which are in the current month will be shown
	 * Ie: first week of October 2024 begins on September 30, and last week ends with November 1, 2 and 3.
	 * So if showOtherMonthDays parameter set to <code>true</code>, these starting and ending days will be also be visible,
	 * otherwise only the days of October (for October month).
	 *
	 * @param showOtherMonthDays {@link boolean} <code>true</code> whole starting and ending weeks, <code>false</code> only
	 *                           the days of current month will be visible
	 * @return {@link YearCalendar} this object
	 */
	public YearCalendar setShowOtherMonthDays(boolean showOtherMonthDays) {
		this.showOtherMonthDays = showOtherMonthDays;
		return this;
	}

	public YearCalendar setWeekdayType(WeekdayType weekdayType) {
		this.weekdayType = weekdayType;
		return this;
	}

	public void modifyYear(int year) {
		getElement().callJsFunction("setYear", year);
	}

	public void modifyLocale(String lcoale) {
		getElement().callJsFunction("setLocale", locale);
	}

	public void modifyYearIsFirst(boolean yearIsFirst) {
		getElement().callJsFunction("setYearIsFirst", yearIsFirst);
	}

	public void modifyShowOtherMonthDays(boolean showOtherMonthDays) {
		getElement().callJsFunction("setShowOtherMonthDays", showOtherMonthDays);
	}

	public void modifyWeekdayType(WeekdayType weekdayType) {
		if ( weekdayType != null ) {
			getElement().callJsFunction("setWeekdayType", weekdayType.getValue());
		}
	}

	/**
	 * Call this method if you want to refresh all the class fields on the client.
	 * Otherwise, call the another refresh method with parameters
	 */
	public void refresh() {
		getElement().setAttribute("year", String.valueOf(this.year));
		getElement().setAttribute("locale", this.locale);
		getElement().setAttribute("year-is-first", this.yearIsFirst);
		getElement().setAttribute("show-other-month-days", this.showOtherMonthDays);
		getElement().setAttribute("weekday-type", this.weekdayType.getValue());
	}

	/**
	 * Call this method if you want to refresh the client componet by the given parameters
	 *
	 * @param year               {@link int} the year of the Year Calendar
	 * @param locale             {@link String}, important for the componets formatting and naming conventions
	 * @param yearIsFirst        {@link boolean} if it's <code>true</code>, the year first, the month will be the second in the header, <code>false</code> the
	 *                           opposite
	 * @param showOtherMonthDays {@link boolean} if it's <code>true</code> whole weeks will be shown at the start or at the end of the month,
	 *                           <code>false</code> only date which are in the current month will be shown
	 */
	public void refresh(int year, String locale, boolean yearIsFirst, boolean showOtherMonthDays) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
		getElement().setAttribute("show-other-month-days", showOtherMonthDays);
	}

	/**
	 * Remove style class from the TD cells which have the given cellType
	 *
	 * @param cellType  {@link String} the cellType: "weekday" or "weekend"
	 * @param className {@link String} the CSS style class name which have to be removed
	 */
	public void removeClassFromCellByType(String cellType, String className) {
		getElement().executeJs("setTimeout(() => { this.removeClassFromCellByType($0, $1); })", cellType, className);
	}

	/**
	 * Add the given className style class to all the days TD cells in the year.
	 *
	 * @param dates     {@link List<Date>} which cells have to me modified
	 * @param className {@link String} the CSS style class which have to be added to the cell
	 */
	public void addClassToCellByDates(List<Date> dates, String className) {
		if ( dates != null ) {
			List<String> convertedDates = dates.stream().map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toString()).toList();
			getElement().executeJs("setTimeout(() => { this.addClassToCellByDates($0, $1); })", convertToJsonArray(convertedDates), className);
		}
	}

	/**
	 * Add the given className style class to all the days TD cells in the year.
	 *
	 * @param dates     {@link List<LocalDate>} which cells have to me modified
	 * @param className {@link String} the CSS style class which have to be added to the cell
	 */
	public void addClassToCellByLocalDates(List<LocalDate> dates, String className) {
		if ( dates != null ) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> convertedDates = dates.stream().map(sdf::format).toList();
			getElement().callJsFunction("addClassToCellByDates", convertToJsonArray(convertedDates), className);
		}
	}

	/**
	 * Convert {@link List<String>} dates into {@link JsonArray}
	 *
	 * @param stringDates {@link List<String>} dates which have to be converted into {@link JsonArray}
	 * @return the converted {@link JsonArray}
	 */
	private JsonArray convertToJsonArray(List<String> stringDates) {
		JsonArray jsonDateArray = Json.createArray();
		for ( int i = 0; i < stringDates.size(); i++ ) {
			jsonDateArray.set(i, stringDates.get(i));
		}
		return jsonDateArray;
	}

	public enum WeekdayType {
		NARROW("narrow"),
		SHORT("short"),
		LONG("long");

		private final String value;

		WeekdayType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

}