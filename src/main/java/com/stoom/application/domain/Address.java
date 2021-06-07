package com.stoom.application.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.sun.istack.NotNull;

/**
 * Class that represents an address in the business domain.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
	@SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@NotEmpty(message = "The field streetname is required")
	@Length(min = 3, max = 100, message = "The length of streetname should be between 3 to 100 characters")
	private String streetName;

	@NotNull
	private int number;

	private String complement;

	@NotEmpty(message = "The field neighbourhood is required")
	@Length(min = 3, max = 50, message = "The length of neighbourhood should be between 3 to 50 characters")
	private String neighbourhood;

	@NotEmpty(message = "The field city is required")
	@Length(min = 3, max = 50, message = "The length of city should be between 3 to 50 characters")
	private String city;

	@NotEmpty(message = "The field state is required")
	@Length(min = 3, max = 50, message = "The length of state should be between 3 to 50 characters")
	private String state;

	@NotEmpty(message = "The field country is required")
	@Length(min = 3, max = 50, message = "The length of country should be between 3 to 50 characters")
	private String country;

	@NotEmpty(message = "The field zipcode is required")
	private String zipcode;

	private Double latitude;

	private Double longitude;

	public Address() {
		super();
	}

	public Address(Long id, String streetName, int number, String complement, String neighbourhood, String city,
			String state, String country, String zipcode, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}
}