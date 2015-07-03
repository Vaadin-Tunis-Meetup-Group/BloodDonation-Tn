package org.vaadin.tunis.blooddonation.controllers.registration;

import org.vaadin.tunis.blooddonation.persistence.nodes.User;

public interface RegistrationControl {
	public boolean isValidUser(User guest);

	public User registerUser(User guest);

}
