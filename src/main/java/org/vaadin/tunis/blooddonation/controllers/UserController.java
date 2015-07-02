package org.vaadin.tunis.blooddonation.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.tunis.blooddonation.persistence.nodes.BloodType;
import org.vaadin.tunis.blooddonation.persistence.nodes.Gender;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.persistence.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@RequestMapping("/adduser/{name}")
	public void addUser(@PathVariable String name) {
		User user = new User(name);
		userRepository.save(user);

	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@RequestMapping("/getuser/{name}")
	public String getUser(@PathVariable String name) {

		User userByName = userRepository.findByFullName(name);
		if (userByName != null)
			return "user : " + userByName.getFullName();
		return "No User";

	}

	@RequestMapping("/getAllUsers")
	@Transactional
	public List<User> getAllUsers() {
		Iterator<User> usersIter = userRepository.findAll()
				.as(Collection.class).iterator();
		List<User> users = new ArrayList<User>();
		while (usersIter.hasNext()) {
			User user = (User) usersIter.next();
			users.add(user);
		}
		return users;
	}

	@Transactional
	public void addUserM() {
		User user = new User();
		user.setAddress("lorem epsum");
		user.setBloodType(BloodType.A_NEGATIVE);
		user.setEmail("foo@moo.co");
		user.setFullName("foo coo");
		user.setGender(Gender.MALE);

		userRepository.save(user);

	}

	public void addUserF() {
		User user = new User();
		user.setAddress("lorem epsum");
		user.setBloodType(BloodType.AB_POSITIVE);
		user.setEmail("liii@fio.co");
		user.setFullName("lilio foo");
		user.setGender(Gender.FEMALE);

		userRepository.save(user);

	}
}
