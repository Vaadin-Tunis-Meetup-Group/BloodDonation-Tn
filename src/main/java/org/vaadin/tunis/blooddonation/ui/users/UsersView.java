package org.vaadin.tunis.blooddonation.ui.users;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tunis.blooddonation.controllers.UserController;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.ui.users.components.UsersGrid;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "Users";

	@Autowired
	UserController userController;
	private UsersGrid grid;

	private Button newUser;

	private Button deleteUser;

	public UsersView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		addStyleName("crud-view");
		HorizontalLayout topLayout = createTopBar();

		grid = new UsersGrid();
		grid.addSelectionListener(new SelectionListener() {

			@Override
			public void select(SelectionEvent event) {
				// viewLogic.rowSelected(grid.getSelectedRow());
			}
		});
		VerticalLayout barAndGridLayout = new VerticalLayout();
		barAndGridLayout.addComponent(topLayout);
		barAndGridLayout.addComponent(grid);
		barAndGridLayout.setMargin(true);
		barAndGridLayout.setSpacing(true);
		barAndGridLayout.setSizeFull();
		barAndGridLayout.setExpandRatio(grid, 1);
		barAndGridLayout.setStyleName("crud-main-layout");

		addComponent(barAndGridLayout);
		grid.setUsers(userController.getAllUsers());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	public HorizontalLayout createTopBar() {
		TextField filter = new TextField();
		filter.setStyleName("filter-textfield");
		filter.setInputPrompt("Filter");
		// ResetButtonForTextField.extend(filter);
		filter.setImmediate(true);
		filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
			@Override
			public void textChange(FieldEvents.TextChangeEvent event) {
				grid.setFilter(event.getText());
			}
		});

		newUser = new Button("New User");
		newUser.addStyleName(ValoTheme.BUTTON_PRIMARY);
		newUser.setIcon(FontAwesome.PLUS_CIRCLE);
		newUser.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
			}
		});

		deleteUser = new Button("Delete User");
		deleteUser.addStyleName(ValoTheme.BUTTON_PRIMARY);
		deleteUser.setIcon(FontAwesome.TRASH_O);
		deleteUser.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				User selectedUser = grid.getSelectedRow();
				userController.deleteUser(selectedUser);
				grid.removeUser(selectedUser);
			}
		});
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setSpacing(true);
		topLayout.setWidth("100%");
		topLayout.addComponent(filter);
		topLayout.addComponent(newUser);
		topLayout.addComponent(deleteUser);
		topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
		topLayout.setExpandRatio(filter, 1);
		topLayout.setStyleName("top-bar");
		return topLayout;
	}
}
