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
	public List<City> listAllCity(int maxRow) {
		return cityDao.listAllCity(maxRow);
	}
	
	@Transactional
	public City findCityByName(String name) {
		// TODO Auto-generated method stub
		return cityDao.findCityByName(name);
	}
	

	@Transactional
	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance,int rownum) {
		// TODO Auto-generated method stub
		return cityDao.listAllCitiesByDistance(city,earthradius, cityGeoLocation, distance,rownum);
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
	public List<City> getAllCityNamesByCountry(int countryId,int maxrow) {
		// TODO Auto-generated method stub
		return cityDao.getAllCityNamesByCountry(countryId,maxrow);
	}

	@Transactional
	public List<String> listAllMetropolitanCities() {
		// TODO Auto-generated method stub
		return cityDao.listAllMetropolitanCities();
	}

	@Transactional
	public List<City> listAllCityNames(int maxRow) {
		// TODO Auto-generated method stub
		return cityDao.listAllCityNames(maxRow);
	}

	@Transactional
	public List<Object[]> getAllCityIdName() {
		// TODO Auto-generated method stub
		return cityDao.getAllCityIdName();
	}

	@Transactional
	public List<City> listAllCities() {
		// TODO Auto-generated method stub
		return cityDao.listAllCities();
	}

}
