package org.vaadin.tunis.blooddonation.persistence.nodes;

public enum Gender {
	MALE("Male"), FEMALE("Female");

	private final String gender;

	private Gender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
}
