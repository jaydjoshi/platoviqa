package com.platovi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platovi.dao.CountryDao;
import com.platovi.model.Country;
import com.platovi.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryDao CountryDao;
	
	

	@Transactional
	public void saveCountry(Country p) {
		// TODO Auto-generated method stub
		CountryDao.saveCountry(p);
	}

	@Transactional
	public void editCountry(Country p) {
		// TODO Auto-generated method stub
		CountryDao.editCountry(p);
		
	}

	@Transactional
	public Country getCountry(int id) {
		// TODO Auto-generated method stub
		 return CountryDao.getCountry(id);
	}

	@Transactional
	public List<Country> listAllCountrys(int maxrow) {
		// TODO Auto-generated method stub
		return CountryDao.listAllCountrys(maxrow);
	}

	@Transactional
	public Country findCountryByName(String name) {
		// TODO Auto-generated method stub
		return CountryDao.findCountryByName(name);
	}

	@Transactional
	public List<String> getAllCountryNames() {
		// TODO Auto-generated method stub
		return CountryDao.getAllCountryNames();
	}

}
