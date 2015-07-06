package org.vaadin.tunis.blooddonation.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.persistence.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

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
		Iterator<User> usersIter = userRepository.findAll().iterator();
		List<User> users = new ArrayList<User>();
		while (usersIter.hasNext()) {
			User user = (User) usersIter.next();
			users.add(user);
		}
		return users;
	}

	public User addUser(User newUser) throws NullPointerException {
		if (newUser != null)
			return userRepository.save(newUser);
		throw new NullPointerException();
	}

	public User getUserByUserName(String userName) throws NullPointerException {
		if (userName != null)
			return userRepository.findByUserName(userName);
		throw new NullPointerException();
	}

	public User getUserByEmail(String email) throws NullPointerException {
		if (email != null)
			return userRepository.findByEmail(email);
		throw new NullPointerException();
	}

}
