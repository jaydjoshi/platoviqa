package com.platovi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country {
	
	@Id
	@GeneratedValue
	private int countryId;
	private String countryName;
	private String title;
	private String description;
	
	//image file location
	private String imageMediumPath;
	private String imageLargePath;
	
	//image internet path
	private String imageInternetSmallPath;
	private String imageInternetLargePath;
	private String imageInternetLargeSource;
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
