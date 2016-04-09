package com.platovi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.platovi.dao.TagDao;
import com.platovi.model.Tag;

@Repository
public class TagDaoImpl implements TagDao {
	
	@PersistenceContext
    private EntityManager em;

	public void saveTag(Tag p) {
		em.persist(p);
	}

	public void editTag(Tag p) {
		em.merge(p);
	}

	public Tag getTag(int id) {
		return em.find(Tag.class, id);
	}
	

	public List<Tag> listAllTags() {
		List<Tag> placeList =  em.createQuery("SELECT a from Tag a").getResultList();
        return placeList;
	}
	

	@SuppressWarnings("unchecked")
	public Tag findTagByName(String name) {
		Query query = em.createQuery("SELECT a FROM Tag a WHERE a.tagName=?1");
        query.setParameter(1, name);
        List<Tag> tags = query.getResultList();
        if(tags.size() == 0) {
            return null;
        } else {
            return tags.get(0);
        }
	}
	
	

}
