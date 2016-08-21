package com.platovi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author jdhirendrajoshi
 *
 */
@Entity
public class Place {
	@Id
	@GeneratedValue
	private int placeId;
	private String placeName;

	private String placeOverview;
	private String timeRequired;
	private String cost;
	private String speciality;
	//like religious, adventure, desert, hill etc
	private String placeType;
	private String placeSubType;
	private int rank;
	private String bestSeasonToVist;
	private double longitude;
	private double latitude;
	private double lonInRadians;
	private double latInRadians;
	
	//image file location
	private String imageMediumPath;
	private String imageLargePath;
	
	private String placeSmallImage;
	private String placeLargeImage;
	private String placeImageSource;
	private String activeFlag;
	
	@OneToOne
	private City city;
	
	private float rating;
	
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceOverview() {
		return placeOverview;
	}
	public void setPlaceOverview(String placeOverview) {
		this.placeOverview = placeOverview;
	}
	public String getTimeRequired() {
		return timeRequired;
	}
	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getBestSeasonToVist() {
		return bestSeasonToVist;
	}
	public void setBestSeasonToVist(String bestSeasonToVist) {
		this.bestSeasonToVist = bestSeasonToVist;
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
	public double getLonInRadians() {
		return lonInRadians;
	}
	public void setLonInRadians(double lonInRadians) {
		this.lonInRadians = lonInRadians;
	}
	public double getLatInRadians() {
		return latInRadians;
	}
	public void setLatInRadians(double latInRadians) {
		this.latInRadians = latInRadians;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getPlaceSubType() {
		return placeSubType;
	}
	public void setPlaceSubType(String placeSubType) {
		this.placeSubType = placeSubType;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
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
	public String getPlaceSmallImage() {
		return placeSmallImage;
	}
	public void setPlaceSmallImage(String placeSmallImage) {
		this.placeSmallImage = placeSmallImage;
	}
	public String getPlaceLargeImage() {
		return placeLargeImage;
	}
	public void setPlaceLargeImage(String placeLargeImage) {
		this.placeLargeImage = placeLargeImage;
	}
	public String getPlaceImageSource() {
		return placeImageSource;
	}
	public void setPlaceImageSource(String placeImageSource) {
		this.placeImageSource = placeImageSource;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
}
