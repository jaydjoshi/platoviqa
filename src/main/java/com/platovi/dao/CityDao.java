package com.platovi.dao;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.GeoLocation;

public interface CityDao {
	
    public void saveCity(City c);
    
    public void editCity(City c);
    
    public City getCity(int id);
     
    public List<City> listAllCity(int maxRow);
    
    public City findCityByName(String name);

	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance,int rownum);

	public List<String> getAllCityNames();

	public List<City> getAllCityNamesByCategory(String categoryName);

	public List<City> getAllCityNamesByState(int stateId);

	public List<City> getAllCityNamesByCountry(int countryId);

	public List<String> listAllMetropolitanCities();

	public List<City> listAllCityNames(int maxRow);

	public List<Object[]> getAllCityIdName();


}
