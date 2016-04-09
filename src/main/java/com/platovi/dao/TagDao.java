package com.platovi.dao;

import java.util.List;

import com.platovi.model.Tag;

public interface TagDao {
	
    public void saveTag(Tag p);
    
    public void editTag(Tag p);
    
    public Tag getTag(int id);
     
    public List<Tag> listAllTags();
    
    public Tag findTagByName(String name);
    
    

}
