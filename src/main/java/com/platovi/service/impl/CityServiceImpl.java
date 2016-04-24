package com.platovi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platovi.dao.CityDao;
import com.platovi.model.City;
import com.platovi.model.GeoLocation;
import com.platovi.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Transactional
	public void saveCity(City c) {
		cityDao.saveCity(c);	
	}

	@Transactional
	public void editCity(City c) {
		cityDao.editCity(c);		
	}

	@Transactional
	public City getCity(int cityId) {
		return cityDao.getCity(cityId);
	}

	@Transactional
	public List<City> listAllCity() {
		return cityDao.listAllCity();
	}
	
	@Transactional
	public City findCityByName(String name) {
		// TODO Auto-generated method stub
		return cityDao.findCityByName(name);
	}
	

	@Transactional
	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance) {
		// TODO Auto-generated method stub
		return cityDao.listAllCitiesByDistance(city,earthradius, cityGeoLocation, distance);
	}

	@Transactional
	public List<String> getAllCityNames() {
		// TODO Auto-generated method stub
		return cityDao.getAllCityNames();
	}

	

	@Transactional
	public List<City> getAllCityNamesByCategory(String categoryName) {
		// TODO Auto-generated method stub
		return cityDao.getAllCityNamesByCategory(categoryName);
	}

	@Transactional
	public List<City> getAllCityNamesByState(int stateId) {
		// TODO Auto-generated method stub
		return cityDao.getAllCityNamesByState(stateId);
	}

	
	@Transactional
	public List<City> getAllCityNamesByCountry(int countryId) {
		// TODO Auto-generated method stub
		return cityDao.getAllCityNamesByCountry(countryId);
	}

}
