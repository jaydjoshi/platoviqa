package com.platovi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.platovi.dao.CountryDao;
import com.platovi.model.City;
import com.platovi.model.Country;

@Repository
public class CountryDaoImpl implements CountryDao {
	
	@PersistenceContext
    private EntityManager em;

	public void saveCountry(Country p) {
		em.persist(p);
	}

	public void editCountry(Country p) {
		em.merge(p);
	}

	public Country getCountry(int id) {
		return em.find(Country.class, id);
	}
	

	public List<Country> listAllCountrys() {
		List<Country> placeList =  em.createQuery("SELECT a from Country a").getResultList();
        return placeList;
	}
	

	@SuppressWarnings("unchecked")
	public Country findCountryByName(String name) {
		Query query = em.createQuery("SELECT a FROM Country a WHERE a.countryName=?1");
        query.setParameter(1, name);
        List<Country> places = query.getResultList();
        if(places.size() == 0) {
            return null;
        } else {
            return places.get(0);
        }
	}
	
	public List<String> getAllCountryNames() {
		// TODO Auto-generated method stub
		 Query query = em.createQuery("SELECT a.countryName FROM Country a");
        
        List<String> countries = query.getResultList();
        return countries;
	}

}
