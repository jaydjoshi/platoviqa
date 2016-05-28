package com.platovi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platovi.model.Country;
import com.platovi.model.State;
import com.platovi.model.XmlUrl;
import com.platovi.model.XmlUrlSet;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.StateService;

@Controller 
public class SitemapController {
	
	@Autowired
	CountryService countryService;
	@Autowired
	StateService stateService;
	@Autowired
	CityService cityService;
	
	private static final Logger LOGGER = Logger.getLogger(SitemapController.class);
	
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
		LOGGER.info("SitemapController - > mian()");
		
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/#!/home", XmlUrl.Priority.HIGH);
        
        
        List<String> countrys = countryService.getAllCountryNames();
        
        List<String> states = stateService.getAllStateNames();
        
        List<String> cities = cityService.getAllCityNames();
        
        List<String> categories = new ArrayList<String>();
        
        //hard coded :/
        categories.add("isAdventure");        
        categories.add("isBeachCity");        
        categories.add("isDesert");        
        categories.add("isGreenCity");        
        categories.add("isHeritage");        
        categories.add("isHillorMountain");        
        categories.add("isMetropolitan");        
        categories.add("isNightLife");        
        categories.add("isReligious");        
        categories.add("isTrending"); 
        
        
        
        
        for (String string : countrys) {
        	string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	create(xmlUrlSet, "/#!/country/"+string.replaceAll(" ", "%20"), XmlUrl.Priority.HIGH);
		}
        
        for (String string : states) {
        	string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	create(xmlUrlSet, "/#!/state/"+string.replaceAll(" ", "%20"), XmlUrl.Priority.HIGH);
		}
        
        for (String string : categories) {
        	create(xmlUrlSet, "/#!/category/"+string.replaceAll(" ", "%20"), XmlUrl.Priority.HIGH);
		}
        
        for (String string : cities) {
        	string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	create(xmlUrlSet, "/#!/detail/"+string.replaceAll(" ", "%20"), XmlUrl.Priority.HIGH);
		}
        

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("http://www.platovi.com" + link, priority));
    }
	
}
