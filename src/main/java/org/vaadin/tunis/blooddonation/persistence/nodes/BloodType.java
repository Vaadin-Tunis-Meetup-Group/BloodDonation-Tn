package org.vaadin.tunis.blooddonation.persistence.nodes;

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
	public BloodType getBloodType(String enumValue){
		BloodType[] values = values();
		for(BloodType value:values){
			if(value.bloodType.equals(enumValue)){
				return value;
			}
		}
		return null;
	}

}