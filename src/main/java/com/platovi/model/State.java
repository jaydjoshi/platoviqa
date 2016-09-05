package com.platovi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class State {
	
	@Id
	@GeneratedValue
	private int stateId;
	private String stateName;
	
	private String title;
	private String description;
	private City[] citiesInStateToVisit;
	
	@OneToOne
	private Country country;
	
	private float rating;
	
	/*//adding for filters
	private String isReligious;
	private String isTrending;
	private String isMetropolitan;
	private String isHillorMountain;
	private String isBeachCity;
	private String isHeritage;
	private String isAdventure;
	private String isGreenCity;
	private String isDesert;
	private String isNightLife;
//	private String travellingPartner;
*/	
	//image file location
	private String imageMediumPath;
	private String imageLargePath;
	
	//image internet path
	private String imageInternetSmallPath;
	private String imageInternetLargePath;
	private String imageInternetLargeSource;
		
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public City[] getCitiesInStateToVisit() {
		return citiesInStateToVisit;
	}
	public void setCitiesInStateToVisit(City[] citiesInStateToVisit) {
		this.citiesInStateToVisit = citiesInStateToVisit;
	}
	public String getImageMediumPath() {
		return imageMediumPath;
	}
	public void setImageMediumPath(String imageMediumPath) {
		this.imageMediumPath = imageMediumPath;
	}
	public String getImageLargePath() {
		return imageLargePath;
	}
	public void setImageLargePath(String imageLargePath) {
		this.imageLargePath = imageLargePath;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getImageInternetSmallPath() {
		return imageInternetSmallPath;
	}
	public void setImageInternetSmallPath(String imageInternetSmallPath) {
		this.imageInternetSmallPath = imageInternetSmallPath;
	}
	public String getImageInternetLargePath() {
		return imageInternetLargePath;
	}
	public void setImageInternetLargePath(String imageInternetLargePath) {
		this.imageInternetLargePath = imageInternetLargePath;
	}
	public String getImageInternetLargeSource() {
		return imageInternetLargeSource;
	}
	public void setImageInternetLargeSource(String imageInternetLargeSource) {
		this.imageInternetLargeSource = imageInternetLargeSource;
	}
	
	
	



}
