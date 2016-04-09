package com.platovi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platovi.dao.PlaceDao;
import com.platovi.model.Place;
import com.platovi.service.PlaceService;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceDao placeDao;
	
	public void savePlace(Place p) {
		placeDao.savePlace(p);

	}

	public void editPlace(Place p) {
		placeDao.editPlace(p);

	}

	public Place getPlace(String id) {
		
		return placeDao.getPlace(id);
	}

	public List<Place> listAllPlaces() {
		
		return placeDao.listAllPlaces();
	}

	public Place findPlaceByName(String name) {
		
		return placeDao.findPlaceByName(name);
	}

	public List<Place> findAllPlacesByCityId(int cityId) {
		// TODO Auto-generated method stub
		return placeDao.findAllPlacesByCityId(cityId);
	}

}
