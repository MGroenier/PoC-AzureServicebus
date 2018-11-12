package nl.groenier.gatewayservice.models;

public class Tracklog {

	private long id;

	private Item item;
	private long item_id;

	private Location location;

	public Tracklog() {
	}

	public Tracklog(long item_id, Location location) {
		this.item_id = item_id;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Tracklog{" +
				"id=" + id +
				", item_id=" + item_id +
				", location=" + location +
				'}';
	}
}
