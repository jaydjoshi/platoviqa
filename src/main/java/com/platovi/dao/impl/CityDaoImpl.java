package com.platovi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.platovi.dao.CityDao;
import com.platovi.model.City;
import com.platovi.model.GeoLocation;

@Repository
public class CityDaoImpl implements CityDao {
	

    @PersistenceContext
    private EntityManager em;

	public void saveCity(City c) {
        em.persist(c);
	}

	@SuppressWarnings("unchecked")
	public List<City> listAllCity() {
        List<City> cityList =  em.createQuery("SELECT a from City a").getResultList();
        return cityList;
	}

	public City getCity(int id) {		
		return (City) em.find(City.class, id);
	}
	
	 
	public City findCityByName(String name) {
	        Query query = em.createQuery("SELECT a FROM City a WHERE a.cityName=?1");
	        query.setParameter(1, name);
	        List<City> cities = query.getResultList();
	        if(cities.size() == 0) {
	            return null;
	        } else {
	            return cities.get(0);
	        }
	}

	public void editCity(City c) {
		em.merge(c);	
	}
	

	public List<City> listAllCitiesByDistance(City city, double earthradius, GeoLocation cityGeoLocation, double distance) {
		// TODO Auto-generated method stub
		GeoLocation[] boundingCoordinates = cityGeoLocation.boundingCoordinates(distance, earthradius);
			boolean meridian180WithinDistance =
				boundingCoordinates[0].getLongitudeInRadians() >
				boundingCoordinates[1].getLongitudeInRadians();
		Query query = em.createQuery("SELECT a FROM City a WHERE (a.latInRadians >= ?1 AND a.latInRadians <= ?2) AND (a.lonInRadians >= ?3 " +
				(meridian180WithinDistance ? "OR" : "AND") + " a.lonInRadians <= ?4)  AND " +
				"acos(sin(?5) * sin(a.latInRadians) + cos(?6) * cos(a.latInRadians) * cos(a.lonInRadians - ?7)) <= ?8") ;
		
		query.setParameter(1, boundingCoordinates[0].getLatitudeInRadians());
		query.setParameter(2, boundingCoordinates[1].getLatitudeInRadians());
		query.setParameter(3, boundingCoordinates[0].getLongitudeInRadians());
		query.setParameter(4, boundingCoordinates[1].getLongitudeInRadians());
		query.setParameter(5, cityGeoLocation.getLatitudeInRadians());
		query.setParameter(6, cityGeoLocation.getLatitudeInRadians());
		query.setParameter(7, cityGeoLocation.getLongitudeInRadians());
		query.setParameter(8, distance / earthradius);
		List<City> cities = query.getResultList();
		return cities;
	}

	public List<String> getAllCityNames() {
		// TODO Auto-generated method stub
		 Query query = em.createQuery("SELECT a.cityName FROM City a");
        
        List<String> cities = query.getResultList();
        return cities;
	}

	/*
	 * (non-Javadoc)
	 * @see com.platovi.dao.CityDao#getAllCityNamesByCategory(com.platovi.model.City)
	 */
	public List<City> getAllCityNamesByCategory(String categoryName) {
		
		// TODO Auto-generated method stub
		Query query = null;
		
		if("isReligious".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isReligious != 'No'");
		}
		else if("isBeachCity".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isBeachCity != 'No'");
		}
		else if("isTrending".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isTrending != 'No'");
		}
		else if("isMetropolitan".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isMetropolitan != 'No'");
		}
		else if("isHillorMountain".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isHillorMountain != 'No'");
		}
		else if("isHeritage".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isHeritage != 'No'");
		}
		else if("isAdventure".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isAdventure != 'No'");
		}
		else if("isGreenCity".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isGreenCity != 'No'");
		}
		else if("isDesert".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isDesert != 'No'");
		}
		else if("isNightLife".equalsIgnoreCase(categoryName)){
			query =  em.createQuery("SELECT a from City a WHERE a.isNightLife != 'No'");
		}
		/*switch (categoryName) {
		case "isReligious":
			query =  em.createQuery("SELECT a from City a WHERE a.isReligious != 'No'");
			
			break;
		case "isBeachCity":
			query =  em.createQuery("SELECT a from City a WHERE a.isBeachCity != 'No'");
			
			break;
		case "isTrending":
			query =  em.createQuery("SELECT a from City a WHERE a.isTrending != 'No'");
			
			break;
		case "isMetropolitan":
			query =  em.createQuery("SELECT a from City a WHERE a.isMetropolitan != 'No'");
			
			break;
		case "isHillorMountain":
			query =  em.createQuery("SELECT a from City a WHERE a.isHillorMountain != 'No'");
			
			break;
		case "isHeritage":
			query =  em.createQuery("SELECT a from City a WHERE a.isHeritage != 'No'");
			
			break;
		case "isAdventure":
			query =  em.createQuery("SELECT a from City a WHERE a.isAdventure != 'No'");
			
			break;
		case "isGreenCity":
			query =  em.createQuery("SELECT a from City a WHERE a.isGreenCity != 'No'");
			
			break;
		case "isDesert":
			query =  em.createQuery("SELECT a from City a WHERE a.isDesert != 'No'");
			
			break;
		case "isNightLife":
			query =  em.createQuery("SELECT a from City a WHERE a.isNightLife != 'No'");
			
			break;
		}*/
		
		List<City> cityList = query.getResultList();
        return cityList;
	}

	@Override
	public List<City> getAllCityNamesByState(int stateId) {
      

		Query query =   em.createQuery("SELECT a from City a WHERE a.state.stateId=?1");
		query.setParameter(1, stateId);
        return query.getResultList();
	
}


	
}
