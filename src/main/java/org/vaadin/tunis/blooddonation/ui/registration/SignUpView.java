package org.vaadin.tunis.blooddonation.ui.registration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tunis.blooddonation.controllers.registration.RegistrationControl;
import org.vaadin.tunis.blooddonation.persistence.nodes.BloodType;
import org.vaadin.tunis.blooddonation.persistence.nodes.Gender;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.security.SecurityUtil;

import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.data.Validator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = SignUpView.VIEW_NAME)
public class SignUpView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "SignUp";

	private ComboBox gender;
	private TextField fullName;
	private TextField username;
	private EmailField email;
	private TextField phoneNumber;
	private DateField birthDate;
	private ComboBox bloodType;
	private PasswordField password;
	private PasswordField confirmPassword;
	private TextArea address;
	private Button signUp;

	@Autowired
	private RegistrationControl registrationControl;

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

		loginForm.addComponent(gender = new ComboBox("Gender"));
		gender.setWidth(15, Unit.EM);
		gender.addItem(Gender.MALE);
		gender.setItemIcon(Gender.MALE, FontAwesome.MALE);
		gender.addItem(Gender.FEMALE);
		gender.setItemIcon(Gender.FEMALE, FontAwesome.FEMALE);
		gender.setNullSelectionAllowed(false);
		gender.setRequired(true);

		loginForm.addComponent(fullName = new TextField("FullName", ""));
		fullName.setWidth(15, Unit.EM);
		fullName.setRequired(true);

		loginForm.addComponent(bloodType = new ComboBox("Blood Type"));
		for (BloodType blood : BloodType.values()) {
			bloodType.addItem(blood.getBloodType());
		}
		bloodType.setWidth(15, Unit.EM);
		bloodType.setNullSelectionAllowed(false);
		bloodType.setRequired(true);

		loginForm.addComponent(email = new EmailField("Email", ""));
		email.setWidth(15, Unit.EM);
		email.setRequired(true);

		loginForm.addComponent(phoneNumber = new TextField("Phone "));
		phoneNumber.setWidth(15, Unit.EM);

		loginForm.addComponent(birthDate = new DateField("BirthDate"));
		birthDate.setWidth(15, Unit.EM);
		birthDate.setRequired(true);

		loginForm.addComponent(address = new TextArea("Address"));
		address.setWidth(15, Unit.EM);

		loginForm.addComponent(username = new TextField("Username", ""));
		username.setWidth(15, Unit.EM);
		username.setRequired(true);

		loginForm.addComponent(password = new PasswordField("Password"));
		password.setWidth(15, Unit.EM);
		password.setRequired(true);

		loginForm.addComponent(confirmPassword = new PasswordField(
				"Confirm Password"));
		confirmPassword.setWidth(15, Unit.EM);
		confirmPassword.addValidator(passwordFieldValidator);

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
		User user = new User();
		user.setGender((Gender) gender.getValue());
		user.setFullName(fullName.getValue());
		user.setBloodType(BloodType.A_NEGATIVE.getBloodType(((String) bloodType
				.getValue()).toUpperCase()));
		user.setUserName(username.getValue());
		user.setEmail(email.getValue());
		user.setTelephone(phoneNumber.getValue());
		user.setBirthDate(birthDate.getValue());
		user.setPassword(SecurityUtil.hashPassword(password.getValue()));
		if (registrationControl.isValidUser(user)) {
			registrationControl.registerUser(user);
		} else {
			Notification.show("Please check your information");
		}
	}

	private Validator passwordFieldValidator = new Validator() {

		@Override
		public void validate(Object value) throws InvalidValueException {
			if (!password.getValue().equals(confirmPassword.getValue())) {
				throw new InvalidValueException(
						"These passwords don't match. Try again?");
			}
		}
	};

}
