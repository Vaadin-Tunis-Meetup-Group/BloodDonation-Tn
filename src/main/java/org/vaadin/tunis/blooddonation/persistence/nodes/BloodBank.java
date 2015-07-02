package org.vaadin.tunis.blooddonation.persistence.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class BloodBank extends AbstractEntity{
	private String name;
	private String address;
	private String telephone;
	private String fax;
	private String email;
	private String geoPositionX;
	private String geoPositionY;

	public void setName(String param) {
		this.name = param;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String param) {
		this.address = param;
	}

	public String getAddress() {
		return address;
	}

	public void setTelephone(String param) {
		this.telephone = param;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setFax(String param) {
		this.fax = param;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGeoPositionX() {
		return geoPositionX;
	}

	public void setGeoPositionX(String geoPositionX) {
		this.geoPositionX = geoPositionX;
	}

	public String getGeoPositionY() {
		return geoPositionY;
	}

	public void setGeoPositionY(String geoPositionY) {
		this.geoPositionY = geoPositionY;
	}

}
