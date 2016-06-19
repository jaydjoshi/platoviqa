package com.platovi.controller;

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

import com.platovi.model.Country;
import com.platovi.service.CountryService;

@Controller 
@RequestMapping("/rest/country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	private static final Logger LOGGER = Logger.getLogger(CountryController.class);
	
	
	
	@RequestMapping(value="/id", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@RequestParam(value="countryId", required = false) int countryId) {
		LOGGER.info("countryController : getCountry method starts");
		Country country = countryService.getCountry(countryId);
        
        return new ResponseEntity<Country>( country, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="countryName", required = false) String countryName) {
		LOGGER.info("countryController : getCountry method starts");
		Country country = countryService.findCountryByName(countryName);
        
        return new ResponseEntity<Country>( country, HttpStatus.OK);
    }
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get all the countrys
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCountrys() {
		
		LOGGER.info("countryController : getAllCountrys method starts");
        List<Country> countrys = countryService.listAllCountrys(5);
        
        return new ResponseEntity<List>( countrys, HttpStatus.OK);
    
    }
		
	


	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Country> createCountry(
	            @RequestBody Country country) {
		LOGGER.info("countryController : createCountry method starts");
				countryService.saveCountry(country);
	            return new ResponseEntity<Country>(country, HttpStatus.CREATED);
	        
	    }

}
