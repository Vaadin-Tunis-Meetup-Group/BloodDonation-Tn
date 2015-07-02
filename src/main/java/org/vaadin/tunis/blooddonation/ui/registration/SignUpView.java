package org.vaadin.tunis.blooddonation.ui.registration;

import javax.annotation.PostConstruct;

import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = SignUpView.VIEW_NAME)
public class SignUpView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "SignUp";
	private TextField fullName;
	private TextField username;
	private EmailField email;
	private DateField birthDate;
	private PasswordField password;
	private PasswordField confirmPassword;
	private Button signUp;

	public SignUpView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		addStyleName("login-screen");
		FormLayout loginForm = new FormLayout();

		loginForm.addStyleName("login-form");
		loginForm.setSizeUndefined();
		loginForm.setMargin(false);

		loginForm.addComponent(fullName = new TextField("FullName", ""));
		fullName.setWidth(15, Unit.EM);
		loginForm.addComponent(email = new EmailField("Email", ""));
		email.setWidth(15, Unit.EM);

		loginForm.addComponent(birthDate = new DateField("BirthDate"));
		birthDate.setWidth(15, Unit.EM);
		loginForm.addComponent(username = new TextField("Username", ""));
		username.setWidth(15, Unit.EM);
		loginForm.addComponent(password = new PasswordField("Password"));
		password.setWidth(15, Unit.EM);
		loginForm.addComponent(confirmPassword = new PasswordField(
				"Confirm Password"));
		confirmPassword.setWidth(15, Unit.EM);
		password.setDescription("Write anything");
		CssLayout buttons = new CssLayout();
		buttons.setStyleName("buttons");
		loginForm.addComponent(buttons);

		buttons.addComponent(signUp = new Button("SignUp"));
		signUp.setDisableOnClick(true);
		signUp.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					signUp();
				} finally {
					signUp.setEnabled(true);
				}
			}
		});
		signUp.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		signUp.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setStyleName("centering-layout");
		centeringLayout.addComponent(loginForm);
		centeringLayout.setComponentAlignment(loginForm,
				Alignment.MIDDLE_CENTER);
		addComponent(centeringLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private void signUp() {
//		if (accessControl.signIn(username.getValue(), password.getValue())) {
//			loginListener.loginSuccessful();
//		} else {
//			showNotification(new Notification("Login failed",
//					"Please check your username and password and try again.",
//					Notification.Type.HUMANIZED_MESSAGE));
//			username.focus();
//		}
	}

}
