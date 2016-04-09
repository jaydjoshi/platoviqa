package com.platovi.dao;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.GeoLocation;

public interface CityDao {
	
    public void saveCity(City c);
    
    public void editCity(City c);
    
    public City getCity(int id);
     
    public List<City> listAllCity();
    
    public City findCityByName(String name);

	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance);

	public List<String> getAllCityNames();

	public List<City> getAllCityNamesByCategory(String categoryName);

	public List<City> getAllCityNamesByState(int stateId);


}
