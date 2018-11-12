package nl.groenier.tracklogservice.models;

import javax.persistence.*;

@Entity
public class Tracklog {

	@Id
	@GeneratedValue
	private long id;

	private long item_id;

	@ManyToOne
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
