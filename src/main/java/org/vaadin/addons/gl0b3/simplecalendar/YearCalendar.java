package org.vaadin.addons.gl0b3.simplecalendar;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * Year Calendar
 * @author Károly Kótay-Szabó (gl0b3)
 */
@Tag("year-calendar")
@JsModule("@gl0b3/simple-calendar")
@NpmPackage(value = "@gl0b3/simple-calendar", version = "1.0.2")
public class YearCalendar extends AbstractSimpleCalendar {

	/**
	 * Default constructor with default field values
	 * After that you have to call {@link AbstractSimpleCalendar#refresh()} to send values to the client.
	 */
	public YearCalendar() {
		super();
	}

	/**
	 * Constructor the year with the given year number, language locale and yearIsFirst order toggle
	 *
	 * @param year        {@link int} the base year of the calendar
	 * @param locale      {@link String} the language locale
	 * @param yearIsFirst {@link boolean} if <code>true</code> the year is the first, the month is the second in the header
	 */
	public YearCalendar(int year, String locale, boolean yearIsFirst) {
		super();
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
	}

	/**
	 * The Year Calendar and the Month Calendar based on this year
	 *
	 * @param year {@link int}, ie: 2024
	 * @return {@link YearCalendar} this object
	 */
	public YearCalendar setYear(int year) {
		this.year = year;
		return this;
	}

	/**
	 * The locale of the Year Calendar and Month Calendar. It's important for the naming and formatting convetions.
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
	 * Set the days and month naming format. For formats see: {@link DayNameFormat}.
	 *
	 * @param dayNameFormat {@link DayNameFormat}
	 * @return this {@link YearCalendar}
	 */
	public YearCalendar setDayNameFormat(DayNameFormat dayNameFormat) {
		this.dayNameFormat = dayNameFormat;
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

	/**
	 * Call this method if you want to refresh the client componet by the given parameters
	 *
	 * @param year               {@link int} the year of the Year Calendar
	 * @param locale             {@link String}, important for the componets formatting and naming conventions
	 * @param yearIsFirst        {@link boolean} if it's <code>true</code>, the year first, the month will be the second in the header, <code>false</code> the
	 *                           opposite
	 * @param showOtherMonthDays {@link boolean} if it's <code>true</code> whole weeks will be shown at the start or at the end of the month,
	 *                           <code>false</code> only date which are in the current month will be shown
	 * @param dayNameFormat      {@link DayNameFormat} the format of the days format (NARROW, SHORT, LONG)
	 */
	public void refresh(int year, String locale, boolean yearIsFirst, boolean showOtherMonthDays, DayNameFormat dayNameFormat) {
		this.year = year;
		this.locale = locale;
		this.yearIsFirst = yearIsFirst;
		this.showOtherMonthDays = showOtherMonthDays;
		this.dayNameFormat = dayNameFormat;
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
		getElement().setAttribute("show-other-month-days", showOtherMonthDays);
		if ( this.dayNameFormat != null ) {
			getElement().setAttribute("weekday-type", dayNameFormat.getValue());
		}
	}
}