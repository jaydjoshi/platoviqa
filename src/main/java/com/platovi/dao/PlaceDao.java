package com.platovi.dao;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.Place;

public interface PlaceDao {
	
    public void savePlace(Place p);
    
    public void editPlace(Place p);
    
    public Place getPlace(String id);
     
    public List<Place> listAllPlaces();
    
    public Place findPlaceByName(String name);
    
    public List<Place> findAllPlacesByCityId(int cityId);
    
    public List<Place> findAllPlacesByCityIdAndPlaceType(int cityId,String placeType, int maxrow,boolean fetchAllRecords);

	public List<Object[]> findDistinctPlaceTypebyCityId(int cityId);

}
