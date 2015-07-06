package org.vaadin.tunis.blooddonation.persistence.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum BloodType {
	O_POSITIVE("O+"), O_NEGATIVE("O-"), A_POSITIVE("A+"), A_NEGATIVE("A-"), B_POSITIVE(
			"B+"), B_NEGATIVE("B-"), AB_POSITIVE("AB+"), AB_NEGATIVE("AB-");
	private final String bloodType;

	private BloodType(String type) {
		this.bloodType = type;
	}

	public String getBloodType() {
		return bloodType;
	}

	public BloodType getBloodType(String enumValue) {
		BloodType[] values = values();
		for (BloodType value : values) {
			if (value.bloodType.equals(enumValue)) {
				return value;
			}
		} 
		return null;
	}

	public List<String> getValues() {
		List<String> stringValues = new ArrayList<String>();
		List<BloodType> values = Arrays.asList(values());
		Iterator<BloodType> iterator = values.iterator();
		while (iterator.hasNext()) {
			BloodType bloodType = (BloodType) iterator.next();
			stringValues.add(bloodType.getBloodType());
		}
		return stringValues;

	}

}