package com.platovi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.platovi.dao.StateDao;
import com.platovi.model.City;
import com.platovi.model.State;

@Repository
public class StateDaoImpl implements StateDao {
	
	@PersistenceContext
    private EntityManager em;

	public void saveState(State p) {
		em.persist(p);
	}

	public void editState(State p) {
		em.merge(p);
	}

	public State getState(int id) {
		return em.find(State.class, id);
	}
	

	public List<State> listAllStates(int row) {
		List<State> placeList =  em.createQuery("SELECT a from State a ORDER BY a.rating DESC").setMaxResults(row).getResultList();
        return placeList;
	}
	

	@SuppressWarnings("unchecked")
	public State findStateByName(String name) {
		Query query = em.createQuery("SELECT a FROM State a WHERE a.stateName=?1");
        query.setParameter(1, name);
        List<State> places = query.getResultList();
        if(places.size() == 0) {
            return null;
        } else {
            return places.get(0);
        }
	}
	
	public List<String> getAllStateNames() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT concat(a.stateName, ', ', a.country.countryName) FROM State a");
		
        List<String> states = query.getResultList();
        return states;
	}

	@Override
	public List<City> getAllStateNamesByCountry(int countryId) {
		Query query =   em.createQuery("SELECT a from State a WHERE a.country.countryId=?1 ORDER BY a.rating DESC");
		query.setParameter(1, countryId);
        return query.getResultList();
	}

	@Override
	public List<State> listAllStatesNames(int maxrow) {
		// TODO Auto-generated method stub
		List<Object[]> placeList =  em.createQuery("SELECT a.stateName,a.imageMediumPath from State a ORDER BY a.rating DESC").setMaxResults(maxrow).getResultList();
		
		List<State> states = new ArrayList<State>();
		for (Object[] object : placeList) {
			State state = new State();
			state.setStateName((String)object[0]);
			state.setImageMediumPath((String)object[1]);
			states.add(state);
		}
		
		return states;
        
		
	}

}
