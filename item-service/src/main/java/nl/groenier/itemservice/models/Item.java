package nl.groenier.itemservice.models;

import org.springframework.data.annotation.Id;

public class Item {

	@Id
	public String id;

	public String targetCity;

	public Item() {
	}

	public Item(String targetCity) {
		this.targetCity = targetCity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetCity() {
		return targetCity;
	}

	public void setTargetCity(String targetCity) {
		this.targetCity = targetCity;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", targetCity='" + targetCity + '\'' +
				'}';
	}

}
