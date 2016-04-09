package com.platovi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.platovi.model.City;
import com.platovi.model.Place;
import com.platovi.service.CityService;
import com.platovi.service.PlaceService;

@Controller 
@RequestMapping("/rest/place")
public class PlaceController {
	
	@Autowired
	PlaceService placeService;
	@Autowired
	CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map> listAllPlaces(@RequestParam(value="cityId", required = false) int cityId) {
		List<Place> placeList = new ArrayList<Place>();
		Map<String, ArrayList<Place>> placeMap = null;
		if(cityId == 0)
		{
        	return new ResponseEntity<Map>(new HashMap(), HttpStatus.BAD_REQUEST);
		}
		else
		{
			placeList = placeService.findAllPlacesByCityId(cityId);
			placeMap = convertListToMap(placeList);
		}
        
        return new ResponseEntity<Map>( placeMap, HttpStatus.OK);
    }
		
	/**
	 * @author jdhirendrajoshi
	 * @param placeList
	 * @return Map<String, ArrayList<Place>>
	 * converts List to Map of key String with place type and value ArrayList<Place>
	 */
	   private Map<String, ArrayList<Place>> convertListToMap(List<Place> placeList) {
		   
		   ArrayList<Place> placeListSee = new ArrayList<Place>();
		   ArrayList<Place> placeListDo = new ArrayList<Place>();
		   ArrayList<Place> placeListEat = new ArrayList<Place>();
		   ArrayList<Place> placeListDrink = new ArrayList<Place>();
		   ArrayList<Place> placeListBuy = new ArrayList<Place>();
		   ArrayList<Place> placeListSleep = new ArrayList<Place>();
		   ArrayList<Place> placeListOther = new ArrayList<Place>();
		   
		   List<Place> placeListSorted = new ArrayList<Place>();
		   for (Place place : placeList) {
			if(place.getPlaceType().equalsIgnoreCase("see")){
				placeListSee.add(place);
			}
			else if(place.getPlaceType().equalsIgnoreCase("do")){
				placeListDo.add(place);
			}
			else if(place.getPlaceType().equalsIgnoreCase("eat")){
				placeListEat.add(place);
			}
			else if(place.getPlaceType().equalsIgnoreCase("drink")){
				placeListDrink.add(place);
			}
			else if(place.getPlaceType().equalsIgnoreCase("buy")){
				placeListBuy.add(place);
			}
			else if(place.getPlaceType().equalsIgnoreCase("sleep")){
				placeListSleep.add(place);
			}
			else {
				placeListOther.add(place);
			}
							
		}
		   /*placeListSorted.addAll(placeListSee);
			placeListSorted.addAll(placeListDo);
			placeListSorted.addAll(placeListEat);
			placeListSorted.addAll(placeListDrink);
			placeListSorted.addAll(placeListBuy);
			placeListSorted.addAll(placeListSleep);
			placeListSorted.addAll(placeListOther);*/
			
			Map<String, ArrayList<Place>> placeMapSorted = new LinkedHashMap<String, ArrayList<Place>>();
			if(placeListSee!=null && placeListSee.size()>0){
				placeMapSorted.put("see", placeListSee);
			}
			if(placeListDo!=null && placeListDo.size()>0){
				placeMapSorted.put("do", placeListDo);
			}
			if(placeListEat!=null && placeListEat.size()>0){
				placeMapSorted.put("eat", placeListEat);
			}
			if(placeListDrink!=null && placeListDrink.size()>0){
				placeMapSorted.put("drink", placeListDrink);
			}
			if(placeListBuy!=null && placeListBuy.size()>0){
				placeMapSorted.put("buy", placeListBuy);
			}
			if(placeListSleep!=null && placeListSleep.size()>0){
				placeMapSorted.put("sleep", placeListSleep);
			}
			if(placeListOther!=null && placeListOther.size()>0){
				placeMapSorted.put("other", placeListOther);
			}
			
		   /*String placeType= null;
		   Map<String, ArrayList<Place>> placeMap = new HashMap<String, ArrayList<Place>>();
		   
			for (Place place : placeListSorted) {
				placeType = place.getPlaceType();
				if(!placeMap.containsKey(placeType)){
					ArrayList<Place> placeTempList = new ArrayList<Place>();
					placeTempList.add(place);
					placeMap.put(placeType, placeTempList);
				}else{
					ArrayList<Place> placeTempList = placeMap.get(placeType);
					placeTempList.add(place);
					placeMap.put(placeType, placeTempList);
				}
			}*/
			return placeMapSorted;
	}


	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Place> createPlace(
	            @RequestBody Place place
	    ) {
		   		
	    		placeService.savePlace(place);
	            return new ResponseEntity<Place>(place, HttpStatus.CREATED);
	        
	    }

}
