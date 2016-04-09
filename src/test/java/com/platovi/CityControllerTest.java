 package com.platovi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.platovi.dao.CityDao;
import com.platovi.model.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:business-config.xml")
public class CityControllerTest {
	
	@Autowired
	CityDao cityDao;
	
	@Transactional
	@Rollback(value=false)
	@Before
	public void setup(){
		City c = new City();
		c.setCityName("test");
		c.setCityId(01);
		cityDao.saveCity(c);
				
	}
	
	@Test
	@Transactional
	public void test(){
		assertNotNull(cityDao.getCity(0));
	}
}
