package com.platovi.dao;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.State;

public interface StateDao {
	
    public void saveState(State p);
    
    public void editState(State p);
    
    public State getState(int id);
     
    public List<State> listAllStates(int row);
    
    public State findStateByName(String name);

	public List<String> getAllStateNames();

	public List<City> getAllStateNamesByCountry(int countryId);

	public List<State> listAllStatesNames(int maxrow);
    
    

}
