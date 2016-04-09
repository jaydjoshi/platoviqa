package com.platovi.service;

import java.util.List;

import com.platovi.model.Tag;

public interface TagService {
	
    public void saveTag(Tag p);
    
    public void editTag(Tag p);
    
    public Tag getTag(int id);
     
    public List<Tag> listAllTags();
    
    public Tag findTagByName(String name);
    
    
}
