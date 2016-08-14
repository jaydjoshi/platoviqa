package com.platovi.service;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.Place;

public interface PlaceService {
	
    public void savePlace(Place p);
    
    public void editPlace(Place p);
    
    public Place getPlace(String id);
     
    public List<Place> listAllPlaces();
    
    public Place findPlaceByName(String name,int cityId);
    
    public List<Place> findAllPlacesByCityId(int cityId);
    
    public List<Place> findAllPlacesByCityIdAndPlaceType(int cityId,String placeType, int maxrow,boolean fetchAllRecords);

	public List<Object[]> findDistinctPlaceTypebyCityId(int cityId);
	public List<String> getAllPlaceNames(float i);
}
