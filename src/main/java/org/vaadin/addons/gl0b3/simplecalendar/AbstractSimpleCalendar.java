package org.vaadin.addons.gl0b3.simplecalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasTheme;

/**
 * The base abstract class for Simple Calendar components
 * @author Károly Kótay-Szabó (gl0b3)
 */
public abstract class AbstractSimpleCalendar extends Component implements HasTheme, HasElement {

	/**
	 * the base year of the Year and Month Calendar
	 */
	protected int year = Year.now().getValue();

	/**
	 * the language locale for the days' and months' name
	 */
	protected String locale = Locale.getDefault().getLanguage();

	/**
	 * the order of the year and month in the header. If <code>true</code> then year is the first, month is the second.
	 */
	protected boolean yearIsFirst = true;

	/**
	 * show full weeks in the start adn end of the month or don't
	 */
	protected boolean showOtherMonthDays = false;

	/**
	 * the days naming format, NARROW, SHORT or LONG
 	 */
	protected DayNameFormat dayNameFormat = DayNameFormat.SHORT;

	/**
	 * Immediatly send the given year parameter to client and refreshes its state
	 *
	 * @param year {@link int} the year we want to change to
	 */
	public void refreshYear(int year) {
		this.year = year;
		getElement().callJsFunction("setYear", year);
	}

	/**
	 * Immediately send the given locale parameter to client and refreshes its state
	 *
	 * @param locale {@link String} the locale we want to change to
	 */
	public void refreshLocale(String locale) {
		this.locale = locale;
		getElement().callJsFunction("setLocale", locale);
	}

	/**
	 * Immediately send the given yearIsFirst parameter to client and refreshes its state
	 *
	 * @param yearIsFirst {@link boolean} the yearIsFirst we want to change to
	 */
	public void refreshYearIsFirst(boolean yearIsFirst) {
		this.yearIsFirst = yearIsFirst;
		getElement().callJsFunction("setYearIsFirst", yearIsFirst);
	}

	/**
	 * Immediately send the given showOtherMonthDays parameter to client and refreshes its state
	 *
	 * @param showOtherMonthDays {@link boolean} the showOtherMonthDays we want to change to
	 */
	public void refreshShowOtherMonthDays(boolean showOtherMonthDays) {
		this.showOtherMonthDays = showOtherMonthDays;
		getElement().callJsFunction("setShowOtherMonthDays", showOtherMonthDays);
	}

	/**
	 * Immediately send the given dayNameFormat parameter to client and refreshes its state
	 *
	 * @param dayNameFormat {@link DayNameFormat} the dayNameFormat we want to change to
	 */
	public void refreshDayNameFormat(DayNameFormat dayNameFormat) {
		this.dayNameFormat = dayNameFormat;
		if ( dayNameFormat != null ) {
			getElement().callJsFunction("setWeekdayType", dayNameFormat.getValue());
		}
	}

	/**
	 * Call this method if you want to refresh all the previously set fields on the client.
	 * Otherwise, call the another refresh method with parameters, ie: {@link AbstractSimpleCalendar#refreshYear(int)}
	 */
	public void refresh() {
		getElement().setAttribute("year", String.valueOf(this.year));
		getElement().setAttribute("locale", this.locale);
		getElement().setAttribute("year-is-first", this.yearIsFirst);
		getElement().setAttribute("show-other-month-days", this.showOtherMonthDays);
		getElement().setAttribute("weekday-type", this.dayNameFormat.getValue());
	}
}
