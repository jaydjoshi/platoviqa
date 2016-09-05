package com.platovi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platovi.dao.StateDao;
import com.platovi.model.City;
import com.platovi.model.State;
import com.platovi.service.StateService;

@Service
@Transactional
public class StateServiceImpl implements StateService {

	@Autowired
	StateDao StateDao;
	
	

	@Transactional
	public void saveState(State p) {
		// TODO Auto-generated method stub
		StateDao.saveState(p);
	}

	@Transactional
	public void editState(State p) {
		// TODO Auto-generated method stub
		StateDao.editState(p);
		
	}

	@Transactional
	public State getState(int id) {
		// TODO Auto-generated method stub
		 return StateDao.getState(id);
	}

	@Transactional
	public List<State> listAllStates(int row) {
		// TODO Auto-generated method stub
		return StateDao.listAllStates(row);
	}
	
	@Transactional
	public List<State> listAllStates() {
		// TODO Auto-generated method stub
		return StateDao.listAllStates();
	}

	@Transactional
	public State findStateByName(String name) {
		// TODO Auto-generated method stub
		return StateDao.findStateByName(name);
	}

	@Transactional
	public List<String> getAllStateNames() {
		// TODO Auto-generated method stub
		return StateDao.getAllStateNames();
	}

	@Transactional
	public List<City> getAllStateNamesByCountry(int countryId) {
		// TODO Auto-generated method stub
		return StateDao.getAllStateNamesByCountry(countryId);
	}

	@Override
	public List<State> listAllStatesNames(int maxrow) {
		// TODO Auto-generated method stub
		return StateDao.listAllStatesNames(maxrow);
	}

	

}
