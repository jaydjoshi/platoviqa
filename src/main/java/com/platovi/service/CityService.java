package com.platovi.service;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.GeoLocation;

public interface CityService {
	
	public void saveCity(City c);
    
    public void editCity(City c);
    
    public City getCity(int cityId);
     
    public List<City> listAllCity();
    
    public City findCityByName(String name);

	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance);

	public List<String> getAllCityNames();

	public List<City> getAllCityNamesByCategory(String categoryName);

	public List<City> getAllCityNamesByState(int stateId);

	public List<City> getAllCityNamesByCountry(int countryId);

	/*public List<City> listCitiesNearBy(City currentCity, double earthRadiusInKm, GeoLocation currentCityGeoLocation,
			double nearbyDistanceInKm);*/
}
