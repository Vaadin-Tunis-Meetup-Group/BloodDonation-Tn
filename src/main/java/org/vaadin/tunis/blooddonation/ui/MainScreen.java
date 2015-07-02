package org.vaadin.tunis.blooddonation.ui;

import org.vaadin.tunis.blooddonation.ui.about.AboutView;
import org.vaadin.tunis.blooddonation.ui.users.UsersView;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
	private Menu menu;

	public MainScreen(Navigator navigator, CssLayout viewContainer) {

		setStyleName("main-screen");

		menu = new Menu(navigator);
		// menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
		// SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
		menu.addView(new MapView(), MapView.VIEW_NAME, MapView.VIEW_NAME,
				FontAwesome.MAP_MARKER);
		menu.addView(new UsersView(), UsersView.VIEW_NAME, UsersView.VIEW_NAME,
				FontAwesome.USERS);
		menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
				FontAwesome.INFO_CIRCLE);
		// menu.addView(new TestUserView(), TestUserView.VIEW_NAME,
		// TestUserView.VIEW_NAME, FontAwesome.USERS);

		navigator.addViewChangeListener(viewChangeListener);

		addComponent(menu);
		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
		setSizeFull();
	}

	// notify the view menu about view changes so that it can display which view
	// is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener() {

		@Override
		public boolean beforeViewChange(ViewChangeEvent event) {
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			menu.setActiveView(event.getViewName());
		}

	};
}
