package com.platovi.util;

import java.util.ArrayList;
import java.util.List;

import com.platovi.model.Category;

public class PlatoviUtility {
	
	
	public static List<Category> getAllCategoryAndPath() {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<Category>();
		
		Category category= null;
		
		category= new Category();
		category.setCategoryName("Adventure");
		category.setImgMediumPath("img/category/medium/ADVENTURE.jpg");
		categories.add(category);
		
		category= new Category();
		category.setCategoryName("BeachCity");
		category.setImgMediumPath("img/category/medium/BEACHCITY.jpg");
		categories.add(category);
		
		category= new Category();
		category.setCategoryName("GreenCity");
		category.setImgMediumPath("img/category/medium/GREENCITY.jpg");
		categories.add(category);
		
		category= new Category();
		category.setCategoryName("Heritage");
		category.setImgMediumPath("img/category/medium/HERITAGE.jpg");
		categories.add(category);

		category= new Category();
		category.setCategoryName("HillorMountain");
		category.setImgMediumPath("img/category/medium/HILLORMOUNTAIN.jpg");
		categories.add(category);
		
		category= new Category();
		category.setCategoryName("Metropolitan");
		category.setImgMediumPath("img/category/medium/METROPOLITAN.jpg");
		categories.add(category);
		
		category= new Category();
		category.setCategoryName("NightLife");
		category.setImgMediumPath("img/category/medium/NIGHTLIFE.jpg");
		categories.add(category);

		category= new Category();
		category.setCategoryName("Religious");
		category.setImgMediumPath("img/category/medium/RELIGIOUS.jpg");
		categories.add(category);

		category= new Category();
		category.setCategoryName("Trending");
		category.setImgMediumPath("img/category/medium/TRENDING.jpg");
		categories.add(category);

		category= new Category();
		category.setCategoryName("Desert");
		category.setImgMediumPath("img/category/medium/DESERT.jpg");
		categories.add(category);
		

		return categories;
	}

	public static String replaceHyphenWithSpace(String cityName) {
		// TODO Auto-generated method stub
		return cityName.replaceAll("-", " ");
	}
}
