package com.platovi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platovi.model.Country;
import com.platovi.model.Place;
import com.platovi.model.State;
import com.platovi.model.XmlUrl;
import com.platovi.model.XmlUrlSet;
import com.platovi.service.CityService;
import com.platovi.service.CountryService;
import com.platovi.service.PlaceService;
import com.platovi.service.StateService;
import com.platovi.util.PlatoviConstants;

@Controller 
public class SitemapController {
	
	@Autowired
	CountryService countryService;
	@Autowired
	StateService stateService;
	@Autowired
	CityService cityService;
	@Autowired
	PlaceService placeService;
	
	private static final Logger LOGGER = Logger.getLogger(SitemapController.class);
	
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
		LOGGER.info("SitemapController - > mian()");
		
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "", XmlUrl.Priority.HIGH, PlatoviConstants.DAILY);        
        
        List<String> countrys = countryService.getAllCountryNames();
        
        List<String> states = stateService.getAllStateNames();
        
        List<Object[]> cities = cityService.getAllCityIdName();
        
        List<String> categories = new ArrayList<String>();
        
        //hard coded :/
        categories.add("Adventure");        
        categories.add("BeachCity");        
        categories.add("Desert");        
        categories.add("GreenCity");        
        categories.add("Heritage");        
        categories.add("HillorMountain");        
        categories.add("Metropolitan");        
        categories.add("NightLife");        
        categories.add("Religious");        
        categories.add("Trending"); 
        
        
        
        
        for (String string : countrys) {
        	string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	create(xmlUrlSet, "/country/"+string.replaceAll(" ", "-"), XmlUrl.Priority.HIGH, PlatoviConstants.DAILY);
		}
        
        for (String string : states) {
        	string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	create(xmlUrlSet, "/state/"+string.replaceAll(" ", "-"), XmlUrl.Priority.HIGH , PlatoviConstants.DAILY);
		}
        
        for (String string : categories) {
        	create(xmlUrlSet, "/category/"+string.replaceAll(" ", "-"), XmlUrl.Priority.HIGH , PlatoviConstants.DAILY);
		}
        
        for (Object[] object : cities) {
        	//string= string.substring(0, string.indexOf(',')==-1?string.length():string.indexOf(','));
        	String city= (String) object[0];
        	int id = (int) object[1];
        	create(xmlUrlSet, "/city/"+city.replaceAll(" ", "-"), XmlUrl.Priority.HIGH , PlatoviConstants.DAILY);
        	
        	List<Place> placeList = placeService.findAllPlacesByCityId(id);
        	Map<String,List<String>> placeMap = null;
        	
        	for (Place place : placeList) {
				placeMap =  new HashMap<String,List<String>>();
				
				String placeType = place.getPlaceType();
				
				if(placeMap.containsKey(placeType)){
					List<String> list = placeMap.get(placeType);
					list.add(place.getPlaceName());
					placeMap.put(placeType, list);
				}
				else{
					List<String> list = new ArrayList<String>();
					list.add(place.getPlaceName());
					placeMap.put(placeType, list);
				}
			}
        	
        	for (Entry<String, List<String>> entry : placeMap.entrySet()){
        		String placeType = entry.getKey();
        		create(xmlUrlSet, "/city/"+city.replaceAll(" ", "-")+"/"+placeType, XmlUrl.Priority.HIGH , PlatoviConstants.DAILY);
        		List<String> placeNameList = entry.getValue();
        		
        		for (String placeName : placeNameList) {
        			create(xmlUrlSet, "/city/"+city.replaceAll(" ", "-")+"/"+placeType+"/"+placeName, XmlUrl.Priority.MEDIUM , PlatoviConstants.WEEKLY);
				}
			}
        
        
		}
        

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority , String changeFreq) {
        xmlUrlSet.addUrl(new XmlUrl("http://www.platovi.com" + link, priority, changeFreq));
        xmlUrlSet.addUrl(new XmlUrl("http://platovi.com" + link, priority, changeFreq));
    }
	
}
