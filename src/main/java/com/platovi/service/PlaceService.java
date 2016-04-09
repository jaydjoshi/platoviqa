package com.platovi.service;

import java.util.List;

import com.platovi.model.City;
import com.platovi.model.Place;

public interface PlaceService {
	
    public void savePlace(Place p);
    
    public void editPlace(Place p);
    
    public Place getPlace(String id);
     
    public List<Place> listAllPlaces();
    
    public Place findPlaceByName(String name);
    
    public List<Place> findAllPlacesByCityId(int cityId);
}
