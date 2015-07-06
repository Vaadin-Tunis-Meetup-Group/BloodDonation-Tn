package org.vaadin.tunis.blooddonation.persistence.mapping;

import com.vaadin.addon.touchkit.gwt.client.vcom.Position;

public class GeoPosition {
	private Position currentPosition;
	private Position lastPosition;

	public Position getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
}
