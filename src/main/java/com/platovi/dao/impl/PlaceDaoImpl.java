package com.platovi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.platovi.dao.PlaceDao;
import com.platovi.model.Place;

@Repository
public class PlaceDaoImpl implements PlaceDao {
	
	@PersistenceContext
    private EntityManager em;

	public void savePlace(Place p) {
		em.persist(p);
	}

	public void editPlace(Place p) {
		em.merge(p);
	}

	public Place getPlace(String id) {
		return em.find(Place.class, id);
	}

	public List<Place> listAllPlaces() {
		List<Place> placeList =  em.createQuery("SELECT a from Place a").getResultList();
        return placeList;
	}

	@SuppressWarnings("unchecked")
	public Place findPlaceByName(String name) {
		Query query = em.createQuery("SELECT a FROM Place a WHERE a.placeName=?1");
        query.setParameter(1, name);
        List<Place> places = query.getResultList();
        if(places.size() == 0) {
            return null;
        } else {
            return places.get(0);
        }
	}
	
	public List<Place> findAllPlacesByCityId(int cityId) {
		Query query =   em.createQuery("SELECT a from Place a WHERE a.city.cityId=?1 ORDER BY a.placeType,a.rating DESC");
		query.setParameter(1, cityId);
        return query.getResultList();
	}
	
	public List<Place> findAllPlacesByCityIdAndPlaceType(int cityId,String placeType, int maxrow,boolean fetchAllRecords) {
		Query query =   em.createQuery("SELECT a from Place a WHERE a.city.cityId=?1 AND a.placeType=?2 ORDER BY a.rating DESC");
		query.setParameter(1, cityId);
		query.setParameter(2, placeType);
		
		if(fetchAllRecords){
			return query.getResultList();			
		}
		else{
			return query.setMaxResults(maxrow).getResultList();	
		}
			
	}

	@Override
	public List<Object[]> findDistinctPlaceTypebyCityId(int cityId) {
		Query query =   em.createQuery("SELECT DISTINCT a.placeType, COUNT(a.placeName) from Place a WHERE a.city.cityId=?1 GROUP BY a.placeType");
		query.setParameter(1, cityId);
        return query.getResultList();
	}

	@Override
	public List<String> getAllPlaceNames(float i) {
		Query query = em.createQuery("SELECT CONCAT(a.placeName,',',a.city.cityName,',',a.placeType) FROM Place a WHERE a.rating>=?1 ORDER BY a.rating DESC");
		query.setParameter(1, i);
        List<String> places = query.getResultList();
		return places; 	
	}
}
