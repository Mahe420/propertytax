package com.project.propertytax.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Zone implements Comparable<Zone>{

	public Zone() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="zone",cascade= {CascadeType.ALL})
	private List<Uav> uav;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Uav> getUav() {
		return uav;
	}

	public void setUav(List<Uav> uav) {
		this.uav = uav;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uav == null) ? 0 : uav.hashCode());
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
		Zone other = (Zone) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uav == null) {
			if (other.uav != null)
				return false;
		} else if (!uav.equals(other.uav))
			return false;
		return true;
	}

	@Override
	public int compareTo(Zone zone) {
		return this.getId()-zone.getId();
	} 
	
}
