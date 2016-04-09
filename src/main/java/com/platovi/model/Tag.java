package com.platovi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag{
	
	@Id
	@GeneratedValue
	private int tagId;
	private String tagName;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL )  
    @JoinTable(name="tag_city", joinColumns=@JoinColumn(name="tag_id"), inverseJoinColumns=@JoinColumn(name="city_id")) 
	private List<City> cities;
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	 
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}	
	
}