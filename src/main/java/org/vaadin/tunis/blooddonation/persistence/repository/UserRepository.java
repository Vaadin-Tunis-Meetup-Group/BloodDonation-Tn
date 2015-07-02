package org.vaadin.tunis.blooddonation.persistence.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;

public interface UserRepository extends GraphRepository<User> {

	User findByFullName(String fullName);

}
