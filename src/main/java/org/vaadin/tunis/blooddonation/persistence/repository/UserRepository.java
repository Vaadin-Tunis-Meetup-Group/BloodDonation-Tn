package org.vaadin.tunis.blooddonation.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByFullName(String fullName);

	User findByUserName(String userName);

	User findByEmail(String email);

}
