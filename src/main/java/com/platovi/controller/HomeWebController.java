package com.platovi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;
import com.platovi.util.PlatoviUtility;

@Controller 
public class HomeWebController {
	
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	
	
	private static final Logger LOGGER = Logger.getLogger(HomeWebController.class);
	
	@RequestMapping("/index.html")
	public ModelAndView homePage() {
		LOGGER.info("In HomeWebController -> homePage method");
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("metropolitanCities", cityService.listAllMetropolitanCities());
		modelAndView.addObject("cities", cityService.listAllCityNames(PlatoviConstants.CITY_DISPLAYED_IN_HOME));
		modelAndView.addObject("categories",PlatoviUtility.getAllCategoryAndPath());
		modelAndView.addObject("states", stateService.listAllStatesNames(PlatoviConstants.STATE_DISPLAYED_IN_HOME));
		//modelAndView.addObject("countries", countryService.listAllCountrysNames(PlatoviConstants.COUNTRY_DISPLAYED_IN_HOME));
	    
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	
	
	
}
