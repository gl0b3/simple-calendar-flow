package org.vaadin.addons.gl0b3.simplecalendar;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport(value = "./styles/simple-calendar-flow-test-styles.css")
public class TestView extends VerticalLayout {

	public TestView() {
		setSizeFull();

		add(new H2("Simple Calendar for Vaadin Flow v24+"));

		MonthCalendar monthCalendar = new MonthCalendar(2024, 1, "hu", true);
		addAndExpand(monthCalendar);
	}
}
