package org.vaadin.tunis.blooddonation.ui.authentication;

import org.vaadin.tunis.blooddonation.persistence.nodes.User;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;

/**
 * Class for retrieving and setting the name of the current user of the current
 * session (without using JAAS). All methods of this class require that a
 * {@link VaadinRequest} is bound to the current thread.
 * 
 * 
 * @see com.vaadin.server.VaadinService#getCurrentRequest()
 */
public final class CurrentUser {

	/**
	 * The attribute key used to store the username in the session.
	 */
	public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = CurrentUser.class
			.getCanonicalName();

	private CurrentUser() {
	}

	/**
	 * Returns the user stored in the current session, or an null if no user is
	 * stored.
	 * 
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static User get() {
		User currentUser = (User) getCurrentRequest().getWrappedSession()
				.getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		return currentUser;
	}

	/**
	 * Sets the current user and stores it in the current session. Using a
	 * {@code null} user will remove the user from the session.
	 * 
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static void set(User user) {
		if (user == null) {
			getCurrentRequest().getWrappedSession().removeAttribute(
					CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		} else {
			getCurrentRequest().getWrappedSession().setAttribute(
					CURRENT_USER_SESSION_ATTRIBUTE_KEY, user);
		}
	}

	private static VaadinRequest getCurrentRequest() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request == null) {
			throw new IllegalStateException(
					"No request bound to current thread");
		}
		return request;
	}
}
