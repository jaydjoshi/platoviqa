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

import com.platovi.model.Tag;
import com.platovi.service.TagService;

@Controller 
@RequestMapping("/rest/tag")
public class TagController {
	
	@Autowired
	TagService tagService;
	
	private static final Logger LOGGER = Logger.getLogger(TagController.class);
	
	
	
	@RequestMapping(value="/id", method = RequestMethod.GET)
    public ResponseEntity<Tag> getTag(@RequestParam(value="tagId", required = false) int tagId) {
		LOGGER.info("tagController : getTag method starts");
		Tag tag = tagService.getTag(tagId);
        
        return new ResponseEntity<Tag>( tag, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Tag> getTagByName(@RequestParam(value="tagName", required = false) String tagName) {
		LOGGER.info("tagController : getTag method starts");
		Tag tag = tagService.findTagByName(tagName);
        
        return new ResponseEntity<Tag>( tag, HttpStatus.OK);
    }
	
	
	/**
	 * @author jdhirendrajoshi
	 * @param cityName
	 * @return ResponseEntity<City>
	 * method to get all the tags
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllTags() {
		
		LOGGER.info("tagController : getAllTags method starts");
        List<Tag> tags = tagService.listAllTags();
        
        return new ResponseEntity<List>( tags, HttpStatus.OK);
    
    }
		
	


	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Tag> createTag(
	            @RequestBody Tag tag) {
		LOGGER.info("tagController : createTag method starts");
				tagService.saveTag(tag);
	            return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
	        
	    }

}
