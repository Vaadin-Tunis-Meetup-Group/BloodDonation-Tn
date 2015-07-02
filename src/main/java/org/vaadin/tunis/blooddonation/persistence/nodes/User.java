package org.vaadin.tunis.blooddonation.persistence.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class User extends AbstractEntity {

	private String fullName;
	private BloodType bloodType;
	private String telephone;
	private String email;
	private String address;
	private Gender gender;
	private String stat;

	public User(String fullName) {
		this.fullName = fullName;
	}

	public User() {
	}

	public void setFullName(String param) {
		this.fullName = param;
	}

	public String getFullName() {
		return fullName;
	}

	public void setBloodType(BloodType param) {
		this.bloodType = param;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setTelephone(String param) {
		this.telephone = param;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setEmail(String param) {
		this.email = param;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress(String param) {
		this.address = param;
	}

	public String getAddress() {
		return address;
	}

	public void setGender(Gender param) {
		this.gender = param;
	}

	public Gender getGender() {
		return gender;
	}

	public void setStat(String param) {
		this.stat = param;
	}

	public String getStat() {
		return stat;
	}
}
