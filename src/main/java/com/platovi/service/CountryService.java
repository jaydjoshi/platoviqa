package com.platovi.service;

import java.util.List;

import com.platovi.model.Country;

public interface CountryService {
	
    public void saveCountry(Country p);
    
    public void editCountry(Country p);
    
    public Country getCountry(int id);
     
    public List<Country> listAllCountrys(int maxrow);
    
    public Country findCountryByName(String name);

	public List<String> getAllCountryNames();
    
    
}
