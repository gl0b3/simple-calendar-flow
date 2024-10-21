package org.vaadin.addons.gl0b3.simplecalendar;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("month-calendar")
@JsModule("@gl0b3/simple-calendar")
@NpmPackage(value = "@gl0b3/simple-calendar", version = "1.0.1")
public class MonthCalendar extends Component implements HasTheme, HasElement {

	public MonthCalendar() {
	}

	public MonthCalendar(int year, int month, String locale, boolean yearIsFirst) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("month", String.valueOf(month));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
	}

	public void refresh(int year, int month, String locale, boolean yearIsFirst) {
		getElement().setAttribute("year", String.valueOf(year));
		getElement().setAttribute("month", String.valueOf(month));
		getElement().setAttribute("locale", locale);
		getElement().setAttribute("year-is-first", yearIsFirst);
	}
}