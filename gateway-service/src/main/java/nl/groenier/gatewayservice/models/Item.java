package nl.groenier.gatewayservice.models;

public class Item {

	private long id;

	private String description;

	private double weight;

	private long origin_location_id;

	private long destination_location_id;

	public Item() {
	}

	public Item(String description, double weight, long destination_location, long destination_location_id) {
		this.description = description;
		this.weight = weight;
		this.origin_location_id = origin_location_id;
		this.destination_location_id = destination_location_id;
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

	public long getOrigin_location_id() {
		return origin_location_id;
	}

	public void setOrigin_location_id(long origin_location_id) {
		this.origin_location_id = origin_location_id;
	}

	public long getDestination_location_id() {
		return destination_location_id;
	}

	public void setDestination_location_id(long destination_location_id) {
		this.destination_location_id = destination_location_id;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", description='" + description + '\'' +
				", weight=" + weight +
				", origin_location_id=" + origin_location_id +
				", destination_location_id=" + destination_location_id +
				'}';
	}
}
