package com.platovi.dao;

import java.util.List;

import com.platovi.model.Country;

public interface CountryDao {
	
    public void saveCountry(Country p);
    
    public void editCountry(Country p);
    
    public Country getCountry(int id);
     
    public List<Country> listAllCountrys();
    
    public Country findCountryByName(String name);

	public List<String> getAllCountryNames();
    
    

}
