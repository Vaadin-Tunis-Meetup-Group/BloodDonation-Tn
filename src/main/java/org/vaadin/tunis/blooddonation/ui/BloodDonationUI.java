package org.vaadin.tunis.blooddonation.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tunis.blooddonation.controllers.authentication.AccessControl;
import org.vaadin.tunis.blooddonation.ui.authentication.LoginView;
import org.vaadin.tunis.blooddonation.ui.dashboard.DashboardView;
import org.vaadin.tunis.blooddonation.ui.error.ErrorView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@Widgetset("org.vaadin.tunis.blooddonation.ui.widgetset.BloodDonationTNWidgetset")
@SpringUI
public class BloodDonationUI extends UI {

	// we can use either constructor autowiring or field autowiring
	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private AccessControl accessControl;

	CssLayout viewContainer;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		viewContainer = new CssLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setSizeFull();
		final Navigator navigator = new Navigator(BloodDonationUI.this,
				viewContainer);
		navigator.addProvider(viewProvider);
		navigator.setErrorView(ErrorView.class);

		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("My");
		if (!accessControl.isUserSignedIn()) {
			getNavigator().navigateTo(LoginView.VIEW_NAME);
			setContent(viewContainer);
		} else {
			showMainView();
		}
	}

	public void showMainView() {
		viewContainer.removeAllComponents();
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MainContainer(getNavigator(), viewContainer));
		getNavigator().navigateTo(DashboardView.VIEW_NAME);
	}

	private Button createNavigationButton(String caption, final String viewName) {
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		// If you didn't choose Java 8 when creating the project, convert this
		// to an anonymous listener class
		button.addClickListener(event -> getUI().getNavigator().navigateTo(
				viewName));
		return button;
	}

	public static BloodDonationUI get() {
		return (BloodDonationUI) UI.getCurrent();
	}

	public AccessControl getAccessControl() {
		return accessControl;
	}
}
