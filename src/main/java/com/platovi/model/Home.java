package com.platovi.model;

import java.util.List;

public class Home {
	private List<String> metropolitanCities;
	private List<Country> countries;
	private List<State> states;
	private List<City> cities;
	
	
	public List<String> getMetropolitanCities() {
		return metropolitanCities;
	}
	public void setMetropolitanCities(List<String> metropolitanCities) {
		this.metropolitanCities = metropolitanCities;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
	
}
