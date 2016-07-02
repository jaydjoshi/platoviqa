package com.platovi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.platovi.exception.PlatoviException;
import com.platovi.model.City;
import com.platovi.model.GeoLocation;
import com.platovi.model.Place;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.PlaceService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;
import com.platovi.util.PlatoviUtility;

@Controller 
public class CityWebController {
	
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	@Autowired
	PlaceService placeService;
	
	private static final Logger LOGGER = Logger.getLogger(CityWebController.class);
	
	@RequestMapping("/city/{cityName}")
	public ModelAndView getCityDetailByName(@PathVariable(value="cityName") String cityName) {
		
		LOGGER.info("CityWebController : getCityDetailByName method starts");
		ModelAndView modelAndView = new ModelAndView();
		City city = null;
		
        if(cityName != null){
        	 cityName= PlatoviUtility.replaceHyphenWithSpace(cityName);
        	 city = cityService.findCityByName(cityName);
        }
        if(city==null){
        	LOGGER.error("CityController :: findCityByName method : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
        }
        
		modelAndView.addObject("city", city);
		modelAndView.addObject("citiesNearby", getNearbyCities(city));
		
		modelAndView.addObject("see", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.SEE,PlatoviConstants.FOUR,false));
		modelAndView.addObject("thingsToDo", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.DO,PlatoviConstants.THREE,false));
		modelAndView.addObject("eat", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.EAT,PlatoviConstants.SIX,false));
		modelAndView.addObject("drink", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.DRINK,PlatoviConstants.EIGHT,false));
		modelAndView.addObject("buy", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.BUY,PlatoviConstants.FOUR,false));
		modelAndView.addObject("sleep", placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(),PlatoviConstants.SLEEP,PlatoviConstants.FOUR,false));
	    
	    modelAndView.setViewName("cityDetail");
	    return modelAndView;
	}

	private List<City> getNearbyCities(City city) {
		// TODO Auto-generated method stub
		
		List<City> cityList= null;
		List<City> returnList= null;
		
		GeoLocation currentCityGeoLocation = GeoLocation.fromDegrees(city.getLatitude(), city.getLongitude());
		
		cityList = cityService.listAllCitiesByDistance(city ,PlatoviConstants.EARTH_RADIUS_IN_KM, currentCityGeoLocation, PlatoviConstants.NEARBY_DISTANCE_IN_KM,PlatoviConstants.CITY_NEARBY);
		
		for (City cityRecord : cityList) {
			GeoLocation cityGeoLocation = GeoLocation.fromDegrees(cityRecord.getLatitude(), cityRecord.getLongitude());
			double distance = cityGeoLocation.distanceTo(currentCityGeoLocation, PlatoviConstants.EARTH_RADIUS_IN_KM);
			cityRecord.setDistanceFromCurrentCity(Math.round(distance));
		}
		//ascending sort on distance
		Collections.sort(cityList,City.CityDistanceComparator);
		
    	
    	int size = cityList.size();
    	//starting from 1 because 1st city will be same city
		if(cityList!=null && size>0){
			if(size >=7)
			{
				returnList=cityList.subList(1, 7);
			}
			else
			{
				returnList=cityList.subList(1, size);
			}
		}
		
		return returnList;
	}
	
	
	@RequestMapping("/city/{cityName}/{placeType}")
	public ModelAndView getPlaceDetailByName(@PathVariable(value="cityName") String cityName,@PathVariable(value="placeType") String placeType) {
		
		LOGGER.info("CityWebController : getCityDetailByName method starts");
		ModelAndView modelAndView = new ModelAndView();
		City city = null;
		
        if(cityName != null){
        	cityName= PlatoviUtility.replaceHyphenWithSpace(cityName);
        	 city = cityService.findCityByName(cityName);
        }
        if(city==null){
        	LOGGER.error("CityController :: findCityByName method : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
        }
        
		modelAndView.addObject("city", city);
		
		if(placeType!=null){
			placeType= PlatoviUtility.replaceHyphenWithSpace(placeType);
		}
		
		modelAndView.addObject("placeTypes",placeService.findDistinctPlaceTypebyCityId(city.getCityId()));
		modelAndView.addObject("places",placeService.findAllPlacesByCityIdAndPlaceType(city.getCityId(), placeType, 0, true));
		
        modelAndView.addObject("placeType",placeType);
	    modelAndView.setViewName("placeType");
	    return modelAndView;
	}
	
	
	@RequestMapping("/city/{cityName}/{placeType}/{placeDetail}")
	public ModelAndView getPlaceDetail(@PathVariable(value="cityName") String cityName,@PathVariable(value="placeType") String placeType,@PathVariable(value="placeDetail") String placeDetail) {
		
		LOGGER.info("CityWebController : getCityDetailByName method starts");
		ModelAndView modelAndView = new ModelAndView();
		City city = null;
		Place place=null;
        if(cityName != null){
        	cityName= PlatoviUtility.replaceHyphenWithSpace(cityName);
        	 city = cityService.findCityByName(cityName);
        }
        if(city==null){
        	LOGGER.error("CityController :: findCityByName method : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
        }
        
		modelAndView.addObject("city", city);
	    
        if(placeDetail!=null){
        	placeDetail= PlatoviUtility.replaceHyphenWithSpace(placeDetail);
        	place= placeService.findPlaceByName(placeDetail);
        }
        modelAndView.addObject("placeTypes",placeService.findDistinctPlaceTypebyCityId(city.getCityId()));
        modelAndView.addObject("place",place);
	    modelAndView.setViewName("placeDetail");
	    return modelAndView;
	}
	
	
	@RequestMapping("/near/{cityName}")
	public ModelAndView getCitiesByNameAndLat(@PathVariable(value="cityName") String cityName,@RequestParam(value="latitude", required = false) String latitude , @RequestParam(value="longitude", required = false) String longitude) {
		LOGGER.info("CityWebController : findCitiesByNameAndType method starts");
		ModelAndView modelAndView = new ModelAndView();
		List<City> cities =  new ArrayList<City>();
		City currentCity = null;
        if(cityName == null){
        	cities = cityService.listAllCity(50);
        }
        else
        {
        	cityName= PlatoviUtility.replaceHyphenWithSpace(cityName);
        	//if You is selected then set Lat and Lon in currentCity 
        	if(PlatoviConstants.YOU.equalsIgnoreCase(cityName) && null != latitude && null != longitude && !"".equals(latitude) && !"".equals(longitude))
        	{
        		currentCity = new City();
        		currentCity.setCityName(cityName);
        		currentCity.setLatitude(Double.valueOf(latitude));
        		currentCity.setLongitude(Double.valueOf(longitude));
        	}
        	//if other tha You is selected then retrive currentCity from DB 
        	else{
        		currentCity = cityService.findCityByName(cityName);
        	}
        	if(currentCity != null){
        		
        		GeoLocation currentCityGeoLocation = GeoLocation.fromDegrees(currentCity.getLatitude(), currentCity.getLongitude());
        		
        		cities = cityService.listAllCitiesByDistance(currentCity ,PlatoviConstants.EARTH_RADIUS_IN_KM, currentCityGeoLocation, PlatoviConstants.DEFAUL_DISTANCE_IN_KM, PlatoviConstants.CITY_RESULT);
        		
        		for (City city : cities) {
        			GeoLocation cityGeoLocation = GeoLocation.fromDegrees(city.getLatitude(), city.getLongitude());
        			double distance = cityGeoLocation.distanceTo(currentCityGeoLocation, PlatoviConstants.EARTH_RADIUS_IN_KM);
        			city.setDistanceFromCurrentCity(Math.round(distance));
				}
        		
        		//sort based on distance in ascending order 
        		cities.sort(City.CityDistanceComparator);
        	}
        	else{
        		cities = cityService.listAllCity(50);
        	}
        }
        modelAndView.addObject("cityName",cityName);
        modelAndView.addObject("cities",cities);
        
	    modelAndView.setViewName("result");
	    
	    return modelAndView;
	    
    }
	
}
