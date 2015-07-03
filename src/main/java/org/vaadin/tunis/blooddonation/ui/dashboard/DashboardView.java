package org.vaadin.tunis.blooddonation.ui.dashboard;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends GridLayout implements View {
	public static final String VIEW_NAME = "Dashboard";

	public DashboardView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		setColumns(3);
		setRows(3);
		addStyleName("dashboard-view");
		addComponent(new Label("The dashboard is not yet implemented"), 1, 1);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
