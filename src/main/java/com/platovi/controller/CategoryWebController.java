
package com.platovi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.platovi.exception.PlatoviException;
import com.platovi.model.City;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;
import com.platovi.util.PlatoviUtility;

@Controller 
public class CategoryWebController {
	
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	
	private static final Logger LOGGER = Logger.getLogger(HomeWebController.class);
	
	@RequestMapping("/category/{categoryName}")
	public ModelAndView getCityDetail(@PathVariable(value="categoryName") String categoryName) {
		LOGGER.info("In CategoryWebController -> homePage method");
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<City> cities =  new ArrayList<City>();
		//City city = setCategoryInCity(categoryName);
		modelAndView.addObject("categoryName", categoryName);
		modelAndView.addObject("categoryImgPath", "img/category/"+categoryName);
		
		if(categoryName != null)
		{
			categoryName = "is"+PlatoviUtility.replaceHyphenWithSpace(categoryName);
			cities = cityService.getAllCityNamesByCategory(categoryName);
		}
		if(cities==null || cities.size() == 0){
        	LOGGER.error("CategoryWebController :: getStateDetailByStateName : nothing found findCityByName HTTP status 404");
        	throw new PlatoviException("404", PlatoviConstants.PAGE_NOT_FOUND_TEXT);
        }
		
		modelAndView.addObject("cities", cities);
		
	    
	    modelAndView.setViewName("categoryDetail");
	    return modelAndView;
	}
	
}
