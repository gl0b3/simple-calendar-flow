package org.vaadin.addons.gl0b3.simplecalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * Month Calendar
 *
 * @author Károly Kótay-Szabó (gl0b3)
 */
@Tag("month-calendar")
@JsModule("@gl0b3/simple-calendar")
@NpmPackage(value = "@gl0b3/simple-calendar", version = "1.0.2")
public class MonthCalendar extends AbstractSimpleCalendar {

	/**
	 * the month in the Month Calendar
	 */
	private int month = YearMonth.now().getMonthValue();

	/**
	 * Constructor for instantiating the Month Calendar with the default values.
	 * After that you have to call {@link AbstractSimpleCalendar#refresh()} to send values to the client.
	 */
	public MonthCalendar() {
		super();
	}

	/**
	 * Constructor with the frequently used parameters. The values are sent to the client immediately.
	 *
	 * @param year        {@link int}
	 * @param month       {@link int}
	 * @param locale      {@link String}
	 * @param yearIsFirst {@link boolean}
	 */
	public MonthCalendar(int year, int month, String locale, boolean yearIsFirst) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("month", String.valueOf(month));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
	}

	/**
	 * The Month Calendar based on this year
	 *
	 * @param year {@link int}, ie: 2024
	 * @return {@link MonthCalendar} this object
	 */
	public MonthCalendar setYear(int year) {
		this.year = year;
		return this;
	}

	/**
	 * The locale of the Month Calendar and Month Calendar. It's important for the naming and formatting convetions.
	 * <p>
	 * For supported locales see: <a href="https://github.com/moment/luxon/blob/master/docs/intl.md">Luxon Intl.md docs</a>
	 *
	 * @param locale {@link String} the desired language locale, ie: "en", "de", "hu", etc.
	 * @return {@link MonthCalendar} this object
	 */
	public MonthCalendar setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * The order of the year and month display in the month calendar header.
	 * If <code>true</code>, the year is first, month is the second, <code>false</code> the opposite order
	 *
	 * @param yearIsFirst {@link boolean} year-month or month-year order
	 * @return {@link MonthCalendar} this object
	 */
	public MonthCalendar setYearIsFirst(boolean yearIsFirst) {
		this.yearIsFirst = yearIsFirst;
		return this;
	}

	/**
	 * Set the days and month naming format. For formats see: {@link DayNameFormat}.
	 *
	 * @param dayNameFormat {@link DayNameFormat}
	 * @return this {@link MonthCalendar}
	 */
	public MonthCalendar setDayNameFormat(DayNameFormat dayNameFormat) {
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
	 * @return {@link MonthCalendar} this object
	 */
	public MonthCalendar setShowOtherMonthDays(boolean showOtherMonthDays) {
		this.showOtherMonthDays = showOtherMonthDays;
		return this;
	}

	/**
	 * The Month Calendar based on this year
	 *
	 * @param month {@link int}, 1-12, ie: 1 (for January)
	 * @return {@link MonthCalendar} this object
	 */
	public MonthCalendar setMonth(int month) {
		this.month = month;
		return this;
	}

	/**
	 * The Month Calendar based on this month.
	 *
	 * @param month {@link int}, 1 to 12, where 1 is January, 2 is February and so on.
	 */
	public void refreshMonth(int month) {
		this.month = month;
		getElement().callJsFunction("setMonth", month);
	}

	/**
	 * Call this method if you want to refresh all the class fields on the client.
	 * Otherwise, call the another refresh method with parameters
	 */
	public void refresh() {
		getElement().setAttribute("year", String.valueOf(this.year));
		getElement().setAttribute("month", String.valueOf(this.month));
		getElement().setAttribute("locale", this.locale);
		getElement().setAttribute("year-is-first", this.yearIsFirst);
		getElement().setAttribute("show-other-month-days", this.showOtherMonthDays);
		if ( this.dayNameFormat != null ) {
			getElement().setAttribute("weekday-type", this.dayNameFormat.getValue());
		}
	}

	/**
	 * Immediately send the given year and month parameter to client and refreshes its state
	 *
	 * @param year  {@link int} the year we want to refresh
	 * @param month {@link int}  the month we want to refresh
	 */
	public void refresh(int year, int month) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("month", String.valueOf(month));
	}

	/**
	 * Immediately send the given parameters to client and refreshes its state
	 *
	 * @param year        {@link int} the year we want to refresh
	 * @param month       {@link int}  the month we want to refresh
	 * @param locale      {@link String}
	 * @param yearIsFirst {@link boolean} if <code>true</code> year is the first, month is the second in the header, <code>false</code>
	 *                    means the opposite
	 */
	public void refresh(int year, int month, String locale, boolean yearIsFirst) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("month", String.valueOf(month));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
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
			getElement().executeJs("setTimeout(() => { this.addClassToCellByDates($0, $1); })", CalendarUtils.convertToJsonArray(convertedDates), className);
		}
	}

	/**
	 * Add the given className style class to all the days TD cells in the year.
	 *
	 * @param dates     {@link List< LocalDate >} which cells have to me modified
	 * @param className {@link String} the CSS style class which have to be added to the cell
	 */
	public void addClassToCellByLocalDates(List<LocalDate> dates, String className) {
		if ( dates != null ) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> convertedDates = dates.stream().map(sdf::format).toList();
			getElement().executeJs("setTimeout(() => { this.addClassToCellByDates($0, $1); })", CalendarUtils.convertToJsonArray(convertedDates), className);
		}
	}
}