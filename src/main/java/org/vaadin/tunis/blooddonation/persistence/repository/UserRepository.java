package org.vaadin.tunis.blooddonation.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByFullName(String fullName);

	User findByUserName(String userName);

	User findByEmail(String email);
	
	User findOneByActivationKey(String key);
	
	User findByUserNameAndActivated(String userName,boolean activated);
	
	@Query("Match (u:User) where u.activated = false and u.createdDate > ?1")
    List<User> findNotActivatedUsersByCreationDateBefore(Date date);

}