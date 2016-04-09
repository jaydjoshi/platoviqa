package com.platovi.dao.impl;

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
	

	public List<State> listAllStates() {
		List<State> placeList =  em.createQuery("SELECT a from State a").getResultList();
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
	
	

}
