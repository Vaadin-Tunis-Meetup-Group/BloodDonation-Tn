package org.vaadin.tunis.blooddonation.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.persistence.repository.UserRepository;
import org.vaadin.tunis.blooddonation.service.util.RandomUtil;

/**
 * Service class for managing users.
 * 
 * @author HaithemMosbahi
 */

@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	public User registerUser(User newUser, String baseUrl) {

		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		newUser.setCreatedDate(new Date());
		userRepository.save(newUser);

		mailService.sendActivationEmail(newUser, baseUrl);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public User activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		User user = userRepository.findOneByActivationKey(key);

		// activate given user for the registration key.
		if (user != null) {
			user.setActivated(true);
			user.setActivationKey(null);
			user = userRepository.save(user);
			log.debug("Activated user: {}", user);
		}
		return user;
	}

	public void updateUserInformation(User user) {

	}

	public void changeUserPassword(String password) {

	}

	/**
	 * Not activated users should be automatically deleted after 1 day.
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 * </p>
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		List<User> users = userRepository
				.findNotActivatedUsersByCreationDateBefore(cal.getTime());
		if(!users.isEmpty())
		   userRepository.delete(users);
	}

}
