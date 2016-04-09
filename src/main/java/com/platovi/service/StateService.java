package com.platovi.service;

import java.util.List;

import com.platovi.model.State;

public interface StateService {
	
    public void saveState(State p);
    
    public void editState(State p);
    
    public State getState(int id);
     
    public List<State> listAllStates();
    
    public State findStateByName(String name);
    
    
}
