package org.vaadin.tunis.blooddonation.ui.registration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tunis.blooddonation.controllers.registration.RegistrationControl;
import org.vaadin.tunis.blooddonation.persistence.nodes.BloodType;
import org.vaadin.tunis.blooddonation.persistence.nodes.Gender;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.security.SecurityUtil;
import org.vaadin.tunis.blooddonation.service.UserService;
import org.vaadin.tunis.blooddonation.ui.BloodDonationUI;
import org.vaadin.tunis.blooddonation.ui.authentication.LoginView;
import org.vaadin.tunis.blooddonation.ui.utils.listener.InstallBeanValidatorBlurListener;
import org.vaadin.tunis.blooddonation.ui.utils.listener.InstallEqualValidatorBlurListener;

import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	private TextField userName;
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
	
	@Autowired
	private UserService userService;

	public SignUpView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		addStyleName("login-screen");
		FormLayout signUpForm = new FormLayout();
		signUpForm.addStyleName("login-form");
		signUpForm.setSizeUndefined();
		signUpForm.setMargin(false);

		buildFieldGroup(signUpForm);

		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setStyleName("centering-layout");
		centeringLayout.addComponent(signUpForm);
		centeringLayout.setComponentAlignment(signUpForm,
				Alignment.MIDDLE_CENTER);
		addComponent(centeringLayout);
	}

	private FieldGroup buildFieldGroup(FormLayout signUpLayout) {
		BeanItem<User> userItem = new BeanItem<User>(new User());
		FieldGroup group = new FieldGroup(userItem);

		addGenderComboBox(signUpLayout);

		addFullNameField(signUpLayout);

		addBloodTypeComboBox(signUpLayout);

		addEmailField(signUpLayout);

		addPhoneNumberField(signUpLayout);

		addBirthDateField(signUpLayout);

		addAddressField(signUpLayout);

		addUserNameField(signUpLayout);

		addPasswordField(signUpLayout);

		addConfirmPasswordField(signUpLayout);

		bindFields(group);

		addBlurListener();

		addButtons(signUpLayout, group);

		return group;

	}

	private void addGenderComboBox(FormLayout signUpLayout) {
		signUpLayout.addComponent(gender = new ComboBox("Gender"));
		gender.setWidth(15, Unit.EM);
		gender.addItem(Gender.MALE);
		gender.setItemIcon(Gender.MALE, FontAwesome.MALE);
		gender.addItem(Gender.FEMALE);
		gender.setItemIcon(Gender.FEMALE, FontAwesome.FEMALE);
		gender.setNullSelectionAllowed(false);
		gender.setRequired(true);
	}

	private void addFullNameField(FormLayout signUpLayout) {
		signUpLayout.addComponent(fullName = new TextField("FullName", ""));
		fullName.setWidth(15, Unit.EM);
		fullName.setInputPrompt("Enter your full name...");
		fullName.setNullRepresentation("");
		fullName.setRequired(true);
	}

	private void addButtons(FormLayout signUpLayout, FieldGroup group) {
		CssLayout buttons = new CssLayout();
		buttons.setStyleName("buttons");
		signUpLayout.addComponent(buttons);
		buttons.addComponent(signUp = new Button("SignUp"));
		signUp.setDisableOnClick(true);
		signUp.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					group.commit();
					BeanItem<User> itemDataSource = (BeanItem<User>) group
							.getItemDataSource();
					User user = itemDataSource.getBean();
					signUp(user);
				} catch (CommitException e) {
					Notification.show("Please check you input");
				} finally {
					signUp.setEnabled(true);
				}
			}
		});
		signUp.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		signUp.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	}

	private void addConfirmPasswordField(FormLayout signUpLayout) {
		signUpLayout.addComponent(confirmPassword = new PasswordField(
				"Confirm Password"));
		confirmPassword.setWidth(15, Unit.EM);
	}

	private void addPasswordField(FormLayout signUpLayout) {
		signUpLayout.addComponent(password = new PasswordField("Password"));
		password.setWidth(15, Unit.EM);
		password.setNullRepresentation("");
		password.setRequired(true);
	}

	private void addUserNameField(FormLayout signUpLayout) {
		signUpLayout.addComponent(userName = new TextField("Username", ""));
		userName.setWidth(15, Unit.EM);
		userName.setNullRepresentation("");
		userName.setRequired(true);
	}

	private void addAddressField(FormLayout signUpLayout) {
		signUpLayout.addComponent(address = new TextArea("Address"));
		address.setWidth(15, Unit.EM);
		address.setNullRepresentation("");
	}

	private void addBirthDateField(FormLayout signUpLayout) {
		signUpLayout.addComponent(birthDate = new DateField("BirthDate"));
		birthDate.setWidth(15, Unit.EM);
		birthDate.setRequired(true);
	}

	private void addPhoneNumberField(FormLayout signUpLayout) {
		signUpLayout.addComponent(phoneNumber = new TextField("Phone "));
		phoneNumber.setWidth(15, Unit.EM);
		phoneNumber.setNullRepresentation("");
	}

	private void addEmailField(FormLayout signUpLayout) {
		signUpLayout.addComponent(email = new EmailField("Email", ""));
		email.setWidth(15, Unit.EM);
		email.setNullRepresentation("");
		email.setRequired(true);
	}

	private void addBloodTypeComboBox(FormLayout signUpLayout) {
		signUpLayout.addComponent(bloodType = new ComboBox("Blood Type"));
		BeanItemContainer<BloodType> bloodTypeContainer = new BeanItemContainer<BloodType>(
				BloodType.class);
		bloodTypeContainer.addAll(Arrays.asList(BloodType.values()));
		bloodType.setContainerDataSource(bloodTypeContainer);
		bloodType.setItemCaptionPropertyId("bloodType");
		bloodType.setWidth(15, Unit.EM);
		bloodType.setNullSelectionAllowed(false);
		bloodType.setRequired(true);
	}

	private void bindFields(FieldGroup group) {
		group.bind(gender, "gender");
		group.bind(fullName, "fullName");
		group.bind(bloodType, "bloodType");
		group.bind(email, "email");
		group.bind(phoneNumber, "telephone");
		group.bind(birthDate, "birthDate");
		group.bind(address, "address");

		group.bind(userName, "userName");
		group.bind(password, "password");

		group.setBuffered(true);
	}

	private void addBlurListener() {
		gender.addBlurListener(new InstallBeanValidatorBlurListener(User.class,
				gender, "gender"));
		fullName.addBlurListener(new InstallBeanValidatorBlurListener(
				User.class, fullName, "fullName"));
		bloodType.addBlurListener(new InstallBeanValidatorBlurListener(
				User.class, bloodType, "bloodType"));
		userName.addBlurListener(new InstallBeanValidatorBlurListener(
				User.class, userName, "userName"));
		email.addBlurListener(new InstallBeanValidatorBlurListener(User.class,
				email, "email"));

		birthDate.addBlurListener(new InstallBeanValidatorBlurListener(
				User.class, birthDate, "birthDate"));

		password.addBlurListener(new InstallBeanValidatorBlurListener(
				User.class, password, "password"));

		confirmPassword.addBlurListener(new InstallEqualValidatorBlurListener(
				confirmPassword, password));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private void signUp(User user) {
		user.setPassword(SecurityUtil.hashPassword(user.getPassword()));
		try {

			if (registrationControl.isValidUser(user)) {
				String baseUrl = Page.getCurrent().getLocation().getScheme()
						+ "://" + Page.getCurrent().getLocation().getHost()
						+ ":" + Page.getCurrent().getLocation().getPort();
				//register user & send activation mail
				userService.registerUser(user, baseUrl);
				//registrationControl.registerUser(user);
				Notification notif = new Notification("SignUp successfully",
						"You should receive a validation mail in few minutes",
						Notification.Type.ASSISTIVE_NOTIFICATION);

				notif.setDelayMsec(5000);
				notif.setPosition(Position.BOTTOM_RIGHT);
				notif.show(Page.getCurrent());
				BloodDonationUI.get().getNavigator()
						.navigateTo(LoginView.VIEW_NAME);
				
			} else {
				Notification.show("Please check your information",
						Notification.Type.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			Notification.show(
					"There is a problem, please check your information",
					Notification.Type.ERROR_MESSAGE);
		}
	}

}
