package com.platovi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.platovi.model.City;
import com.platovi.model.GeoLocation;
import com.platovi.model.Header;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.PlaceService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;

@Controller 
@RequestMapping("/rest/city")
public class CityController {
	
	@Autowired
	CityService cityService;
	@Autowired
	PlaceService placeService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	
	//final static Logger logger = Logger.getLogger(CityController.class);
	private static final Logger LOGGER = Logger.getLogger(CityController.class);
	
	
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get names of all the country,state,city
	 */
	@RequestMapping(value="/name", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCityNames() {
		
		LOGGER.info("CityController : getAllCityNames method starts");
		List<Header> resultHeader=new ArrayList<Header>();
		Header header =null;
		
        List<String> cityNames = cityService.getAllCityNames();
        List<String> stateNames = stateService.getAllStateNames();
        List<String> countryNames = countryService.getAllCountryNames();
        
        List<String> result= new ArrayList<String>();
        
        result.addAll(countryNames);
        result.addAll(stateNames);
        result.addAll(cityNames);
        
        for (String countryStr : countryNames) {
        	header= new Header();
        	header.setPlaceName(countryStr);
        	header.setPlaceType(PlatoviConstants.COUNTRY);
        	resultHeader.add(header);
		}
        
        for (String stateStr : stateNames) {
        	header= new Header();
        	header.setPlaceName(stateStr);
        	header.setPlaceType(PlatoviConstants.STATE);
        	resultHeader.add(header);
		}
        
        for (String cityStr : cityNames) {
        	header= new Header();
        	header.setPlaceName(cityStr);
        	header.setPlaceType(PlatoviConstants.CITY);
        	resultHeader.add(header);
		}
        
        return new ResponseEntity<List>( resultHeader, HttpStatus.OK);
    
    }
	
	/**
	 * remove type param attribute
	 * remove list all city method
	 * @param cityName
	 * @param latitude
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List> findCitiesByNameAndType(@RequestParam(value="cityName", required = false) String cityName, @RequestParam(value="latitude", required = false) String latitude , @RequestParam(value="longitude", required = false) String longitude) {
		LOGGER.info("CityController : findCitiesByNameAndType method starts");
		List<City> cityList =  new ArrayList<City>();
		City currentCity = null;
        if(cityName == null)
        	cityList = cityService.listAllCity(50);
        else
        {
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
        		
        		cityList = cityService.listAllCitiesByDistance(currentCity ,PlatoviConstants.EARTH_RADIUS_IN_KM, currentCityGeoLocation, PlatoviConstants.DEFAUL_DISTANCE_IN_KM, PlatoviConstants.CITY_RESULT);
        		
        		for (City city : cityList) {
        			GeoLocation cityGeoLocation = GeoLocation.fromDegrees(city.getLatitude(), city.getLongitude());
        			double distance = cityGeoLocation.distanceTo(currentCityGeoLocation, PlatoviConstants.EARTH_RADIUS_IN_KM);
        			city.setDistanceFromCurrentCity(Math.round(distance));
				}
        	}
        	else{
        		cityList = cityService.listAllCity(50);
        	}
        }
        return new ResponseEntity<List>( cityList, HttpStatus.OK);
    }
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/detail", method = RequestMethod.GET)
    public ResponseEntity<City> findCityByName(@RequestParam(value="cityName", required = false) String cityName) {
		LOGGER.info("CityController : findCityByName method starts");
		City city = null;
        if(cityName != null){
        	 city = cityService.findCityByName(cityName);
        }
        if(city==null){
        	LOGGER.error("CityController :: findCityByName method : nothing found findCityByName HTTP status 404");
        	return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        	
        }
        return new ResponseEntity<City>( city, HttpStatus.OK);
    
    }
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/nearby", method = RequestMethod.GET)
    public ResponseEntity<List> findCitiesNearByAnotherCity(@RequestParam(value="cityName", required = false) String cityName, @RequestParam(value="latitude", required = false) String latitude , @RequestParam(value="longitude", required = false) String longitude) {
		LOGGER.info("CityController : findCitiesNearByAnotherCity method starts");
		List<City> cityList =  new ArrayList<City>();
		List<City> returnList= new ArrayList<City>();
		City currentCity = new City();
        if(cityName == null || (latitude==null && longitude==null))
        	cityList = cityService.listAllCity(50);
        else
        {
        	
        	//currentCity = cityService.findCityByNameAndType(cityName,latitude);
        	currentCity.setCityName(cityName);
        	currentCity.setLatitude(Double.valueOf(latitude));
        	currentCity.setLongitude(Double.valueOf(longitude));
        	GeoLocation currentCityGeoLocation = GeoLocation.fromDegrees(currentCity.getLatitude(), currentCity.getLongitude());
			
			cityList = cityService.listAllCitiesByDistance(currentCity ,PlatoviConstants.EARTH_RADIUS_IN_KM, currentCityGeoLocation, PlatoviConstants.NEARBY_DISTANCE_IN_KM,PlatoviConstants.CITY_NEARBY);
			
			for (City city : cityList) {
				GeoLocation cityGeoLocation = GeoLocation.fromDegrees(city.getLatitude(), city.getLongitude());
				double distance = cityGeoLocation.distanceTo(currentCityGeoLocation, PlatoviConstants.EARTH_RADIUS_IN_KM);
				city.setDistanceFromCurrentCity(Math.round(distance));
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
    		
    		
        }
        return new ResponseEntity<List>( returnList, HttpStatus.OK);
    }
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/category", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCityNamesByCategory(@RequestParam(value="categoryName", required = false) String categoryName) {
		
		LOGGER.info("CityController : getAllCityNamesByCategory method starts");
		List<City> cityList =  new ArrayList<City>();
		//City city = setCategoryInCity(categoryName);
		
			cityList = cityService.getAllCityNamesByCategory(categoryName);
		
		
        return new ResponseEntity<List>( cityList, HttpStatus.OK);
    
    }
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/state", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCityNamesByState(@RequestParam(value="stateId", required = false) int stateId) {
		
		LOGGER.info("CityController : getAllCityNamesByState method starts");
		List<City> cityList =  new ArrayList<City>();
		
			cityList = cityService.getAllCityNamesByState(stateId);
		
		
        return new ResponseEntity<List>( cityList, HttpStatus.OK);
    
    }
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/country", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCityNamesByCountry(@RequestParam(value="countryId", required = false) int countryId) {
		
		LOGGER.info("CityController : getAllCityNamesByCountry method starts");
		List<City> cityList =  new ArrayList<City>();
		List<City> returnList =  new ArrayList<City>();
		
			cityList = cityService.getAllCityNamesByCountry(countryId);
			
			int size = cityList.size();
        	
    		if(cityList!=null && size>0){
    			if(size >=15)
    			{
    				returnList=cityList.subList(0, 15);
    			}
    			else
    			{
    				returnList=cityList.subList(0, size);
    			}
    		}
		
        return new ResponseEntity<List>( returnList, HttpStatus.OK);
    
    }
	
	@RequestMapping(value="/top", method = RequestMethod.GET)
    public ResponseEntity<List> getAllTopCity(@RequestParam(value="row", required = false) int row) {
		
		LOGGER.info("CityController : getAllTopCity method starts");
		List<City> cityList =  new ArrayList<City>();
			
		cityList = cityService.listAllCity(row);
		
	    return new ResponseEntity<List>( cityList, HttpStatus.OK);
    
    }
	

	private City setCategoryInCity(String categoryName) {
		return null;/*
		City city = new City();
		
		switch (categoryName) {
			case "isReligious":
				city.setIsReligious("Y");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isBeachCity":
				city.setIsBeachCity("Y");
				city.setIsReligious("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isTrending":
				city.setIsTrending("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isMetropolitan":
				city.setIsMetropolitan("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isHillorMountain":
				city.setIsHillorMountain("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isHeritage":
				city.setIsHeritage("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isAdventure":
				city.setIsAdventure("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isGreenCity":
				city.setIsGreenCity("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsDesert("N");
				city.setIsNightLife("N");
				break;
			case "isDesert":
				city.setIsDesert("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsNightLife("N");
				break;
			case "isNightLife":
				city.setIsNightLife("Y");
				city.setIsReligious("N");
				city.setIsBeachCity("N");
				city.setIsTrending("N");
				city.setIsMetropolitan("N");
				city.setIsHillorMountain("N");
				city.setIsHeritage("N");
				city.setIsAdventure("N");
				city.setIsGreenCity("N");
				city.setIsDesert("N");
				break;
			
			default:
				return null;
		}
		return city;
	*/}

	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<City> saveCity(
	            @RequestBody City city
	    ) {
		   LOGGER.info("CityController : saveCity method starts");
	    		cityService.saveCity(city);
	            return new ResponseEntity<City>(city, HttpStatus.CREATED);
	        
	    }

}
