package com.project.propertytax.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.project.propertytax.util.YearContraint;



@Entity
public class User implements Comparable<User> {
 
	public User() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@YearContraint
	private String yearAssessment;
	
	@NotBlank
	@Pattern(regexp="[a-zA-Z]+")
	private String name;
	@NotBlank
	private String address;
	
	@NotBlank
	@Email
	private String email;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Zone zone;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Description description;
	
	@NotBlank
	@YearContraint
	private String constructedYear;
	
	@NotBlank
	@Pattern(regexp="[0-9]+")
	private String area;
	private Long totalTax;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYearAssessment() {
		return yearAssessment;
	}
	public void setYearAssessment(String yearAssessment) {
		this.yearAssessment = yearAssessment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public String getConstructedYear() {
		return constructedYear;
	}
	public void setConstructedYear(String constructedYear) {
		this.constructedYear = constructedYear;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Long getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Long totalTax) {
		this.totalTax = totalTax;
	}
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((constructedYear == null) ? 0 : constructedYear.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (totalTax ^ (totalTax >>> 32));
		result = prime * result + ((yearAssessment == null) ? 0 : yearAssessment.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (constructedYear == null) {
			if (other.constructedYear != null)
				return false;
		} else if (!constructedYear.equals(other.constructedYear))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalTax != other.totalTax)
			return false;
		if (yearAssessment == null) {
			if (other.yearAssessment != null)
				return false;
		} else if (!yearAssessment.equals(other.yearAssessment))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", yearAssessment=" + yearAssessment + ", name=" + name + ", address=" + address
				+ ", email=" + email + ", zone=" + zone + ", description=" + description + ", constructedYear="
				+ constructedYear + ", area=" + area + ", totalTax=" + totalTax + ", getId()=" + getId()
				+ ", getYearAssessment()=" + getYearAssessment() + ", getName()=" + getName() + ", getAddress()="
				+ getAddress() + ", getEmail()=" + getEmail() + ", getZone()=" + getZone() + ", getDescription()="
				+ getDescription() + ", getConstructedYear()=" + getConstructedYear() + ", getArea()=" + getArea()
				+ ", getTotalTax()=" + getTotalTax() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
	@Override
	public int compareTo(User user) {
		return this.getId()-user.getId();
	}

	
}
