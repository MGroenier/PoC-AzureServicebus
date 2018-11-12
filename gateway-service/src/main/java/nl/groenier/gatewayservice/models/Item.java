package nl.groenier.gatewayservice.models;

public class Item {

	private long id;

	private String description;

	private double weight;

	private Location origin_location;

	private Location destination_location;

	public Item() {
	}

	public Item(String description, double weight, Location origin_location, Location destination_location) {
		this.description = description;
		this.weight = weight;
		this.origin_location = origin_location;
		this.destination_location = destination_location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Location getOrigin_location() {
		return origin_location;
	}

	public void setOrigin_location(Location origin_location) {
		this.origin_location = origin_location;
	}

	public Location getDestination_location() {
		return destination_location;
	}

	public void setDestination_location(Location destination_location) {
		this.destination_location = destination_location;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", description='" + description + '\'' +
				", weight=" + weight +
				", origin_location=" + origin_location +
				", destination_location=" + destination_location +
				'}';
	}
}
