package org.vaadin.tunis.blooddonation.controllers.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.tunis.blooddonation.caching.ConnectedUsers;
import org.vaadin.tunis.blooddonation.controllers.UserController;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.security.SecurityUtil;
import org.vaadin.tunis.blooddonation.ui.authentication.CurrentUser;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
@Component
public class BasicAccessControl implements AccessControl {

	@Autowired
	UserController userController;

	@Override
	public boolean signIn(String username, String password) {
		if (username == null || username.isEmpty())
			return false;
		User user = userController.getUserByUserName(username);
		if (user != null
				&& SecurityUtil.hashPassword(password).equals(
						user.getPassword())) {
			CurrentUser.set(user);
			ConnectedUsers.INSTANCE.addUser(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserSignedIn() {
		return !(CurrentUser.get() == null);
	}

	@Override
	public boolean isUserInRole(String role) {
		if ("admin".equals(role)) {
			// Only the "admin" user is in the "admin" role
			return getPrincipalName().equals("admin");
		}

		// All users are in all non-admin roles
		return true;
	}

	@Override
	public String getPrincipalName() {
		return CurrentUser.get().getUserName();
	}

}
