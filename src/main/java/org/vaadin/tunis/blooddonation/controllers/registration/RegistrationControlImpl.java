package org.vaadin.tunis.blooddonation.controllers.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.tunis.blooddonation.controllers.UserController;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;

@Component
public class RegistrationControlImpl implements RegistrationControl {

	@Autowired
	UserController userController;

	@Override
	public boolean isValidUser(User guest) throws NullPointerException {

		if (userController.getUserByUserName(guest.getUserName()) != null
				|| userController.getUserByEmail(guest.getEmail()) != null)
			return false;
		return true;

	}

	@Override
	public User registerUser(User guest) {
		return userController.addUser(guest);
	}

}
