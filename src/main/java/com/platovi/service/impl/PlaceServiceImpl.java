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

	public Place findPlaceByName(String name,int cityId) {
		
		return placeDao.findPlaceByName(name,cityId);
	}

	@Transactional
	public List<Place> findAllPlacesByCityId(int cityId) {
		// TODO Auto-generated method stub
		return placeDao.findAllPlacesByCityId(cityId);
	}

	@Transactional
	public List<Place> findAllPlacesByCityIdAndPlaceType(int cityId, String placeType, int maxrow,
			boolean fetchAllRecords) {
		// TODO Auto-generated method stub
		return placeDao.findAllPlacesByCityIdAndPlaceType(cityId, placeType,maxrow,fetchAllRecords);
	}

	@Transactional
	public List<Object[]> findDistinctPlaceTypebyCityId(int cityId) {
		// TODO Auto-generated method stub
		return placeDao.findDistinctPlaceTypebyCityId(cityId);
	}

	@Override
	public List<String> getAllPlaceNames(float i) {
		return placeDao.getAllPlaceNames(i);
	}
}
