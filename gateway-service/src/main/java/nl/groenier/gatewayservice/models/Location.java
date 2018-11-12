package nl.groenier.gatewayservice.models;

public class Location {

	private long id;
	private String city;
	private String street;
	private Integer streetnumber;
	private String suffix;
	private String postal_code;
	private String country_code;
	private double longitude;
	private double latitude;

	public Location() {
	}

	public Location(String city, String street, Integer streetnumber, String suffix, String postal_code, String country_code, double longitude, double latitude) {
		this.city = city;
		this.street = street;
		this.streetnumber = streetnumber;
		this.suffix = suffix;
		this.postal_code = postal_code;
		this.country_code = country_code;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Integer getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(Integer streetnumber) {
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location{" +
				"id=" + id +
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
