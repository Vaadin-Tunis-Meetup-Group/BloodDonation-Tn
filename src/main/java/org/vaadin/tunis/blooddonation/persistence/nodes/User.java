package org.vaadin.tunis.blooddonation.persistence.nodes;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.vaadin.tunis.blooddonation.persistence.mapping.GeoPosition;

@NodeEntity
public class User extends AbstractEntity implements Serializable {

	@NotNull
	@Size(min = 2, max = 255)
	private String fullName;

	@NotNull
	@Size(min = 5, max = 20)
	private String userName;

	@NotNull
	@Size(min = 8, max = 20)
	private String password;

	@NotNull
	@Email
	private String email;

	@NotNull
	private Date birthDate;

	@NotNull
	private BloodType bloodType;

	@GraphProperty(defaultValue = "")
	private String telephone;

	@Size(max = 255)
	private String address;

	@NotNull
	private Gender gender;

	@RelatedTo(type = "DONTAE_TO", direction = Direction.OUTGOING)
	Set<User> receivers;

	@Transient
	private GeoPosition geoPosition;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress(String address) {
		this.address = address != null ? address : "";
	}

	public String getAddress() {
		return address;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setPassword(String hashPassword) {
		this.password = hashPassword;
	}

	public String getPassword() {
		return password;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
}
