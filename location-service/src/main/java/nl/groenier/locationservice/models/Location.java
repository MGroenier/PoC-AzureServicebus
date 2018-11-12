package nl.groenier.locationservice.models;

import org.springframework.data.annotation.Id;

public class Location {

	@Id
	public String id;

	public String city;

	public String street;

	public int streetnumber;

	public String suffix;

	public String postal_code;

	public String country_code;

	public Double longitude;

	public Double latitude;

	public Location() {
	}

	public Location(String city, String street, int streetnumber, String suffix, String postal_code, String country_code, Double longitude, Double latitude) {
		this.city = city;
		this.street = street;
		this.streetnumber = streetnumber;
		this.suffix = suffix;
		this.postal_code = postal_code;
		this.country_code = country_code;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(int streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location{" +
				"id='" + id + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", streetnumber=" + streetnumber +
				", suffix='" + suffix + '\'' +
				", postal_code='" + postal_code + '\'' +
				", country_code='" + country_code + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}
