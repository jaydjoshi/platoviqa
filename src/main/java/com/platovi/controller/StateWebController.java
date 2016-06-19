package com.platovi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.platovi.exception.PlatoviException;
import com.platovi.model.City;
import com.platovi.model.State;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;
import com.platovi.util.PlatoviUtility;

@Controller 
public class StateWebController {
	
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	
	
	private static final Logger LOGGER = Logger.getLogger(HomeWebController.class);
	
	@RequestMapping("/state/{stateName}")
	public ModelAndView getStateDetailByStateName(@PathVariable(value="stateName") String stateName) {
		LOGGER.info("stateController : getStateDetailByStateName method starts");
		
		ModelAndView modelAndView = new ModelAndView();
		
		State state = null;
		
        if(stateName != null){
        	stateName= PlatoviUtility.replaceHyphenWithSpace(stateName);
        	state = stateService.findStateByName(stateName);
        }
        if(state==null){
        	LOGGER.error("stateController :: getStateDetailByStateName : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
        }
		
		modelAndView.addObject("state", state);
		modelAndView.addObject("cities",cityService.getAllCityNamesByState(state.getStateId()));
		
	    
	    modelAndView.setViewName("stateDetail");
	    return modelAndView;
	}
	
}
