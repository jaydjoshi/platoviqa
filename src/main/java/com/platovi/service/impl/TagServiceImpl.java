package com.platovi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platovi.dao.TagDao;
import com.platovi.model.Tag;
import com.platovi.service.TagService;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	TagDao tagDao;
	
	

	@Transactional
	public void saveTag(Tag p) {
		// TODO Auto-generated method stub
		tagDao.saveTag(p);
	}

	@Transactional
	public void editTag(Tag p) {
		// TODO Auto-generated method stub
		tagDao.editTag(p);
		
	}

	@Transactional
	public Tag getTag(int id) {
		// TODO Auto-generated method stub
		 return tagDao.getTag(id);
	}

	@Transactional
	public List<Tag> listAllTags() {
		// TODO Auto-generated method stub
		return tagDao.listAllTags();
	}

	@Transactional
	public Tag findTagByName(String name) {
		// TODO Auto-generated method stub
		return tagDao.findTagByName(name);
	}

	

}
