package com.platovi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.platovi.exception.PlatoviException;
import com.platovi.model.Country;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;
import com.platovi.util.PlatoviUtility;

@Controller 
public class CountryWebController {
	
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	
	private static final Logger LOGGER = Logger.getLogger(CountryWebController.class);
	
	@RequestMapping("/country/{countryName}")
	public ModelAndView getCountryDetailByName(@PathVariable(value="countryName") String countryName) {
		LOGGER.info("CountryWebController : getCountryDetailByName method starts");
		
		ModelAndView modelAndView = new ModelAndView();
		
		Country country = null;
		if(null != countryName){
			countryName= PlatoviUtility.replaceHyphenWithSpace(countryName);
			country = countryService.findCountryByName(countryName);			
		}
		if(null == country ){
			LOGGER.error("CountryWebController :: getCountryDetailByName method : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
		}

		modelAndView.addObject("country", country);
		modelAndView.addObject("states", stateService.getAllStateNamesByCountry(country.getCountryId()));
		
		modelAndView.addObject("cities",cityService.getAllCityNamesByCountry(country.getCountryId(),PlatoviConstants.FIFTY) );		
	    
	    modelAndView.setViewName("countryDetail");
	    return modelAndView;
	}
	
}
