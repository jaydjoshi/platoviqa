package com.platovi.controller;




import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.platovi.exception.PlatoviException;
import com.platovi.service.CityService;
import com.platovi.util.PlatoviConstants;


@ControllerAdvice
public class GlobalExceptionController {
	
	@Autowired
	CityService cityService;
	
	private static String DEFAULT_ERROR_VIEW = "errorPage"; 
	
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(PlatoviException.class)
	public ModelAndView handleCustomException(PlatoviException ex) {
		LOGGER.error("GlobalExceptionController --> handleCustomException method starts");
		ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR_VIEW);
		modelAndView.addObject("errMsg", ex.getErrMsg());
		modelAndView.addObject("cities", cityService.listAllCityNames(PlatoviConstants.CITY_DISPLAYED_IN_HOME));
		
		return modelAndView;

	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
		LOGGER.error("GlobalExceptionController --> handleError404 method starts");
	     ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR_VIEW);
	     modelAndView.addObject("errMsg", PlatoviConstants.PAGE_NOT_FOUND_TEXT);  
	     modelAndView.addObject("cities", cityService.listAllCityNames(PlatoviConstants.CITY_DISPLAYED_IN_HOME));
	         
	     return modelAndView;
	}

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public ModelAndView handleAllException(Exception ex) {
		LOGGER.error("GlobalExceptionController --> handleAllException method starts");
		ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR_VIEW);
		modelAndView.addObject("cities", cityService.listAllCityNames(PlatoviConstants.CITY_DISPLAYED_IN_HOME));
		modelAndView.addObject("errMsg", PlatoviConstants.SOMETHING_WENT_WRONG);

		return modelAndView;

	}
	
}