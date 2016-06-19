package com.platovi.model;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class City {
	
	@Id
	@GeneratedValue
	private int cityId;
	private String cityName;
	
	private String title;
	private int rank;
	private String description;
	private String bestSeasonToVist;
	private Place[] placesToVisit;
	private City[] citiesNearByToVisit;
	private double longitude;
	private double latitude;
	private double lonInRadians;
	private double latInRadians;
	private double distanceFromCurrentCity;
	private String idealDuration;
	
	//adding for filters
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
	//private String travellingPartner;
	
	//image file location
	private String imageMediumPath;
	private String imageLargePath;
	
	private float rating;
	
	@OneToOne
	private State state;
	
	@OneToOne
	private Country country;
	
	/*@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="cities")
	private List<Tag> tags;
	*/
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBestSeasonToVist() {
		return bestSeasonToVist;
	}
	public void setBestSeasonToVist(String bestSeasonToVist) {
		this.bestSeasonToVist = bestSeasonToVist;
	}
	public static Comparator<City> getCityDistanceComparator() {
		return CityDistanceComparator;
	}
	public static void setCityDistanceComparator(Comparator<City> cityDistanceComparator) {
		CityDistanceComparator = cityDistanceComparator;
	}
	public Place[] getPlacesToVisit() {
		return placesToVisit;
	}
	public void setPlacesToVisit(Place[] placesToVisit) {
		this.placesToVisit = placesToVisit;
	}
	public City[] getCitiesNearByToVisit() {
		return citiesNearByToVisit;
	}
	public void setCitiesNearByToVisit(City[] citiesNearByToVisit) {
		this.citiesNearByToVisit = citiesNearByToVisit;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
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
	public double getDistanceFromCurrentCity() {
		return distanceFromCurrentCity;
	}
	public void setDistanceFromCurrentCity(double distanceFromCurrentCity) {
		this.distanceFromCurrentCity = distanceFromCurrentCity;
	}
	
	
	
	public String getIsReligious() {
		return isReligious;
	}
	public void setIsReligious(String isReligious) {
		this.isReligious = isReligious;
	}
	public String getIsTrending() {
		return isTrending;
	}
	public void setIsTrending(String isTrending) {
		this.isTrending = isTrending;
	}
	public String getIsMetropolitan() {
		return isMetropolitan;
	}
	public void setIsMetropolitan(String isMetropolitan) {
		this.isMetropolitan = isMetropolitan;
	}
	public String getIsHillorMountain() {
		return isHillorMountain;
	}
	public void setIsHillorMountain(String isHillorMountain) {
		this.isHillorMountain = isHillorMountain;
	}
	public String getIsBeachCity() {
		return isBeachCity;
	}
	public void setIsBeachCity(String isBeachCity) {
		this.isBeachCity = isBeachCity;
	}
	public String getIsHeritage() {
		return isHeritage;
	}
	public void setIsHeritage(String isHeritage) {
		this.isHeritage = isHeritage;
	}
	public String getIsAdventure() {
		return isAdventure;
	}
	public void setIsAdventure(String isAdventure) {
		this.isAdventure = isAdventure;
	}
	public String getIsGreenCity() {
		return isGreenCity;
	}
	public void setIsGreenCity(String isGreenCity) {
		this.isGreenCity = isGreenCity;
	}
	public String getIsDesert() {
		return isDesert;
	}
	public void setIsDesert(String isDesert) {
		this.isDesert = isDesert;
	}
	public String getIsNightLife() {
		return isNightLife;
	}
	public void setIsNightLife(String isNightLife) {
		this.isNightLife = isNightLife;
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
	
	public String getIdealDuration() {
		return idealDuration;
	}
	public void setIdealDuration(String idealDuration) {
		this.idealDuration = idealDuration;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

/*	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
*/
	


	/*Comparator for sorting the list by roll no*/
    public static Comparator<City> CityDistanceComparator = new Comparator<City>() {

	public int compare(City c1, City c2) {

	   int distance1 = (int) c1.getDistanceFromCurrentCity();
	   int distance2 = (int) c2.getDistanceFromCurrentCity();

	   /*For ascending order*/
	   return distance1-distance2;

	   /*For descending order*/
	   //rollno2-rollno1;
   }};

	

}
