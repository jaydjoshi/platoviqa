package com.platovi.controller;

import java.util.ArrayList;
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
import com.platovi.model.State;
import com.platovi.service.StateService;

@Controller 
@RequestMapping("/rest/state")
public class StateController {
	
	@Autowired
	StateService stateService;
	
	private static final Logger LOGGER = Logger.getLogger(StateController.class);
	
	
	
	@RequestMapping(value="/id", method = RequestMethod.GET)
    public ResponseEntity<State> getState(@RequestParam(value="stateId", required = false) int stateId) {
		LOGGER.info("stateController : getState method starts");
		State state = stateService.getState(stateId);
        
        return new ResponseEntity<State>( state, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<State> getStateByName(@RequestParam(value="stateName", required = false) String stateName) {
		LOGGER.info("stateController : getState method starts");
		State state = stateService.findStateByName(stateName);
        
        return new ResponseEntity<State>( state, HttpStatus.OK);
    }
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get all the states
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllStates(@RequestParam(value="row", required = false) int row) {
		
		LOGGER.info("stateController : getAllStates method starts");
		List<State> states = null;
		if(row>0)
		{
			states = stateService.listAllStates(row);
		}
		else
		{
			//list all
			states = stateService.listAllStates();
		}
        
        return new ResponseEntity<List>( states, HttpStatus.OK);
    
    }
		
	


	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<State> createState(
	            @RequestBody State state) {
		LOGGER.info("stateController : createState method starts");
				stateService.saveState(state);
	            return new ResponseEntity<State>(state, HttpStatus.CREATED);
	        
	    }
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get the detail of the city
	 */
	@RequestMapping(value="/country", method = RequestMethod.GET)
    public ResponseEntity<List> getAllStateNamesByCountry(@RequestParam(value="countryId", required = false) int countryId) {
		
		LOGGER.info("CityController : getAllCityNamesByCountry method starts");
		List<City> cityList =  new ArrayList<City>();
		
			cityList = stateService.getAllStateNamesByCountry(countryId);
		
		
        return new ResponseEntity<List>( cityList, HttpStatus.OK);
    
    }

}
