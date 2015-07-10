package org.vaadin.tunis.blooddonation.ui;

import java.util.Iterator;
import java.util.Set;

import org.vaadin.tunis.blooddonation.caching.ConnectedUsers;
import org.vaadin.tunis.blooddonation.persistence.mapping.GeoPosition;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;
import org.vaadin.tunis.blooddonation.ui.authentication.CurrentUser;

import com.vaadin.addon.touchkit.gwt.client.vcom.Position;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class MapView extends CssLayout implements View {

	public static final String VIEW_NAME = "MapView";
	// private ProductForm form;
	// private SampleCrudLogic viewLogic = new SampleCrudLogic(this);

	private GoogleMap googleMap;
	// private GoogleMapMarker kakolaMarker = new GoogleMapMarker(
	// "DRAGGABLE: Kakolan vankila", new LatLon(60.44291, 22.242415),
	// true, null);
	// private GoogleMapInfoWindow kakolaInfoWindow = new GoogleMapInfoWindow(
	// "Kakola used to be a provincial prison.", kakolaMarker);
	private final String apiKey = "";

	public MapView() {

		setSizeFull();
		addStyleName("crud-view");
		googleMap = new GoogleMap(null, null, null);

		googleMap.setZoom(10);
		googleMap.setSizeFull();
		// kakolaMarker.setAnimationEnabled(false);

		// googleMap.addMarker(kakolaMarker);
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		// OpenInfoWindowOnMarkerClickListener infoWindowOpener = new
		// OpenInfoWindowOnMarkerClickListener(
		// googleMap, kakolaMarker, kakolaInfoWindow, this);
		// googleMap.addMarkerClickListener(infoWindowOpener);

		// kakolaInfoWindow.setWidth("400px");
		// kakolaInfoWindow.setHeight("500px");
		// form = new ProductForm(viewLogic);
		addComponent(googleMap);
		// addComponent(form);
		// viewLogic.init();

	}

	@Override
	public void attach() {
		super.attach();
		addConnectedUsersMarker();

	}

	private void addConnectedUsersMarker() {
		Position currentUserPosition = CurrentUser.get().getGeoPosition()
				.getCurrentPosition();
		googleMap.setCenter(new LatLon(currentUserPosition.getLatitude(),
				currentUserPosition.getLongitude()));
		Set<User> connectedUsers = ConnectedUsers.INSTANCE.getConnectedUsers();
		Iterator<User> iterator = connectedUsers.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if (!user.equals(CurrentUser.get())) {
				GeoPosition userGeoPosition = user.getGeoPosition();
				if (userGeoPosition != null
						&& userGeoPosition.getCurrentPosition() != null) {
					GoogleMapMarker userMarker = new GoogleMapMarker(
							user.getFullName(), new LatLon(userGeoPosition
									.getCurrentPosition().getLatitude(),
									userGeoPosition.getCurrentPosition()
											.getLongitude()), false);
					googleMap.addMarker(userMarker);

				}
				;
			}
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void showError(String msg) {
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	public void showSaveNotification(String msg) {
		Notification.show(msg, Type.TRAY_NOTIFICATION);
	}

	// public void editProduct(Product product) {
	// if (product != null) {
	// form.addStyleName("visible");
	// form.setEnabled(true);
	// } else {
	// form.removeStyleName("visible");
	// form.setEnabled(false);
	// }
	// form.editProduct(product);
	// }

}
