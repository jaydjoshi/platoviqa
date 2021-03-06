/**
 * @author jdhirendrajoshi
 * hardcoded values of city and types
 */
var cityMetro = [
    "Mumbai",
    "Pune",
    "Bangalore",
    "Delhi",
    "Chennai",
    "Kolkata"
];


/**
 * @author jdhirendrajoshi
 * angular module named app with ui.router and ngResurce as dependencies
 */
var app = angular.module('platoviApp', ['ui.bootstrap','ui.router','ngResource','rzModule','ngAnimate','ncy-angular-breadcrumb','ngMaterial','ngMap','angulartics.google.analytics','angularUtils.directives.dirDisqus','feeds']);




/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city URL resource
 */
app.factory('cityFactory', function($resource){
	return $resource('/rest/city');
});

/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city URL resource
 */
app.factory('cityNameFactory', function($resource){
	return $resource('/rest/city/name');
});

/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city detail URL resource
 */
app.factory('cityDetailFactory', function($resource){
	return $resource('/rest/city/detail');
});

/**
 * @author jdhirendrajoshi
 * cityCategoryFactory factory method to return city detail URL resource
 */
app.factory('cityCategoryFactory', function($resource){
	return $resource('/rest/city/category');
});

/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city detail URL resource
 */
app.factory('citiesNearByFactory', function($resource){
	return $resource('/rest/city/nearby');
});

/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city detail URL resource
 */
app.factory('citiesInStateFactory', function($resource){
	return $resource('/rest/city/state');
});

/**
 * @author jdhirendrajoshi
 * topCitiesFactory factory method to return city detail URL resource
 */
app.factory('topCitiesFactory', function($resource){
	return $resource('/rest/city/top');
});

/**
 * @author jdhirendrajoshi
 * topStateFactory factory method to return city detail URL resource
 */
app.factory('topStateFactory', function($resource){
	return $resource('/rest/state/all');
});

/**
 * @author jdhirendrajoshi
 * stateFactory factory method to return state detail URL resource
 */
app.factory('stateFactory', function($resource){
	return $resource('/rest/state');
});

/**
 * @author jdhirendrajoshi
 * citiesInCountryFactory factory method to return city detail URL resource
 */
app.factory('citiesInCountryFactory', function($resource){
	return $resource('/rest/city/country');
});

/**
 * @author jdhirendrajoshi
 * statesInCountryFactory factory method to return state detail URL resource
 */
app.factory('statesInCountryFactory', function($resource){
	return $resource('/rest/state/country');
});
/**
 * @author jdhirendrajoshi
 * countryFactory factory method to return country detail URL resource
 */
app.factory('countryFactory', function($resource){
	return $resource('/rest/country');
});

/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city detail URL resource
 */
app.factory('placeFactory', function($resource){
	return $resource('/rest/place');
});

/**
 * @author jdhirendrajoshi
 * temperatureFactory factory method to return weather details URL resource
 */
app.factory('temperatureFactory', function($resource){
	return $resource('http://api.openweathermap.org/data/2.5/weather?appid=f59bdef073d54ff4235d881e39502a18&units=metric');
});

/**
 * @author jdhirendrajoshi
 * romeToRioFactory factory method to return routes
 * 
 */
app.factory('romeToRioFactory', function($resource){
	return $resource('http://free.rome2rio.com/api/1.2/json/Search?key=7LK8XgvC');
});

/**
 * @author jdhirendrajoshi
 * googleFactory factory method to return google map url
 * 
 */
app.factory('googleFactory', function($resource){
	return $resource('https://maps.googleapis.com/maps/api/geocode/json');
});

/**
 * @author jdhirendrajoshi
 * googleFactory factory method to return existing google map object
 * 
 */

app.factory('googleMaps', function() {
	  var maps = {};

	  function addMap(mapId) {
	    maps[mapId] = {};
	  }
	  function getMap(mapId) {
	    if (!maps[mapId]) addMap(mapId);
	    return maps[mapId];
	  }

	  return {
	    addMap: addMap,
	    getMap: getMap
	  }
	});

/**
 * capitalize filter to make first case uppercase
 */
app.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});



/**
 * @author jdhirendrajoshi
 * homeController controller method with a search method that changes the scope to result and passes cityName as $stateParam 
 */
app.controller('headerController', function($scope,$rootScope,cityNameFactory,$state) {
	
	$rootScope.error = '';
    var query=cityNameFactory.query();
	query.$promise.then(onSuccess);
	var cityNameList=[];
	
	function onSuccess(data){
		console.log("header: "+data.placeName);
		
		$scope.headerList=data;
		
		for( var i in data ) {
			cityNameList.push(data[i].placeName+' ('+data[i].placeType+')');
		}
		
		$scope.cityNameList=cityNameList;
		
	};
	
	$scope.detailCity = function(isValid){
		if(isValid){
			var subStringCommaPos=$scope.selectedCity.indexOf(",");
			var subStringOpenBracketPos=$scope.selectedCity.lastIndexOf("(");
			var subStringCloseBracketPos=$scope.selectedCity.lastIndexOf(")");
			
			//city , state or country
			var subStr = $scope.selectedCity.substring(subStringOpenBracketPos+1,subStringCloseBracketPos);
			
			if(subStr=='City'){
				console.log("header found city");
				// Mumbai, India (City)
				$state.go("detail", { 'cityName' :  $scope.selectedCity.substring(0,subStringCommaPos) });
			}
			else if(subStr=='State'){
				console.log("header found state");
				// Maharashtra, India (State) 
				$state.go("state", { 'stateName' :  $scope.selectedCity.substring(0,subStringCommaPos) });
			}
			else if(subStr=='Country'){
				console.log("header found Country");
				//Bracket position cause there is no comma in country like India (Country)
				$state.go("country", { 'countryName' :  $scope.selectedCity.substring(0,subStringOpenBracketPos-1) });
			}
			else{
				$state.go("detail", { 'cityName' :  $scope.selectedCity.substring(0,subStringCommaPos) });
			}
		}
	}
    
});

/**
 * @author jdhirendrajoshi
 * homePage2Controller controller  
 */
app.controller('homePage2Controller', function($scope,$rootScope,topCitiesFactory,topStateFactory,$state) {
	
	$rootScope.isHomeController=true;
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	$scope.categories = [
	                            "Adventure",
	                            "BeachCity",
	                            "GreenCity",
	                            "Heritage",
	                            "HillorMountain",
	                            "Metropolitan",
	                            "NightLife",
	                            "Religious",
	                            "Trending"
	                        ];
	
	/* city data */
	var query=topCitiesFactory.query({row:12});
	query.$promise.then(onSuccess);
	
	function onSuccess(data){
		$scope.topCitydata=data;
	};
	
	
	/* state data */
	var query=topStateFactory.query({row:9});
	
	query.$promise.then(function onSuccess(data){
		$scope.topStateData=data;
	});
	
});

/**
 * @author jdhirendrajoshi
 * homeController controller method with a search method that changes the scope to result and passes cityName as $stateParam 
 */
app.controller('homeController', function($scope,$rootScope,cityFactory,cityNameFactory,$state) {

	$rootScope.error = '';
	$rootScope.pageTitle = 'Home | Platovi - places to visit';
	$rootScope.keywords = ['places to visit','travel','tourism','weekend getaways','trip', 'travel tips'];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//set the object to the scope
    $scope.cities = cityMetro;
    $scope.cityDropdown={};
    $scope.cityDropdown.cityName = "You";
    $rootScope.isHomeController=true;
    //$scope.my = { isHomeController: true };
    	
    var query=cityNameFactory.query();
	query.$promise.then(function(data){
		$scope.cityNameList=(data);
	});

    if($scope.cityDropdown.cityName == "You"){    	
    	if($rootScope.latitude==='' || $rootScope.latitude==null || $rootScope.longitude==='' || $rootScope.longitude==null ){
	    	if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(function(position){
			    	$rootScope.$apply(function(){
			    		$rootScope.latitude = position.coords.latitude;
			    		$rootScope.longitude = position.coords.longitude;
			      });
			    });
			}
    	}
	}
    
    //set the geolocation details if You is selected from the dropdown
    $scope.getLocation = function(){
    	if($scope.cityDropdown.cityName == "You"){    	
    		if($rootScope.latitude==='' || $rootScope.latitude==null || $rootScope.longitude==='' || $rootScope.longitude==null ){
	    		if (navigator.geolocation) {
				    navigator.geolocation.getCurrentPosition(function(position){
				    	$rootScope.$apply(function(){
				    		$rootScope.latitude = position.coords.latitude;
				    		$rootScope.longitude = position.coords.longitude;
				      });
				    });
				}
    		}
		}
    	else{
    		$rootScope.latitude='';
    		$rootScope.longitude='';
    	}
	};
    
    $scope.search = function(){
    	$state.go("result", { 'cityName' :  $scope.cityDropdown.cityName , 'latitude' :  $rootScope.latitude , 'longitude' :  $rootScope.longitude }); 	
    };
    
    
    
    
});

/**
 * @author jdhirendrajoshi
 * resultController controller method which retrives the result data of cities and sets $cityData to the list of cities returned. 
 * showdetailCity function takes city as an argument and stores the value in dataService which shares the data with detailController
 */
app.controller('resultController',function($scope,$rootScope,cityFactory,$state,$stateParams){
	$rootScope.error = '';
	//to display the footer
	$rootScope.isHomeController=false;
	$rootScope.pageTitle = 'Places to visit near '+$stateParams.cityName+' | Platovi - places to visit';
	$rootScope.keywords = ['places to visit near '+$stateParams.cityName,'travelnear '+$stateParams.cityName,$stateParams.cityName+' tourism','weekend getaways near '+$stateParams.cityName,'trip near '+$stateParams.cityName, 'travel tips near '+$stateParams.cityName];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//show loader
	$scope.showLoader = true;
	
	$scope.cityName = $stateParams.cityName;
	$scope.latitude = $stateParams.latitude;
	$scope.longitude = $stateParams.longitude;
	
	
	
	
	var query=cityFactory.query({cityName:$scope.cityName , latitude:$scope.latitude, longitude:$scope.longitude});
	query.$promise.then(function(data){
		$scope.cityData=(data);
		//do not show loader
		$scope.showLoader = false;
		
	});
	
	/*==========================  filter ================================*/
	filter($scope,true);
	
    /*==========================  filter ================================end*/
	
	$scope.getBackgroudClass = function(rating){
		if(rating>=4)
			return "greenFive";
		else if(rating>=3)
			return "greenFour";
		else if(rating>=2)
			return "greenThree";
		else if(rating>=1)
			return "greenTwo";
		else
			return "greenOne";
	}
	    
	    
});

/**
 * filter function
 * filter based on
 * isReligious;
	isTrending;
	isMetropolitan;
	isHillorMountain;
	isBeachCity;
	isHeritage;
	isAdventure;
	isGreenCity;
	isDesert;
	isNightLife;
 */

var filter = function($scope,displaySliderAndDistanceSort){

	if(displaySliderAndDistanceSort){
		// default values for slider
		$scope.slider = {
			    minValue: 0,
			    maxValue: 1000,
			    options: {
			        floor: 0,
			        ceil: 1000,
			        translate: function(value) {
			            return value+' km';
			          }
			    }
			};
		
		//order by sorting
		$scope.sortType     = 'distanceFromCurrentCity'; // set the default sort type
		$scope.sortReverse  = false;  // set the default sort order
	}else{
			
			//order by sorting
			$scope.sortType     = 'rating'; // set the default sort type
			$scope.sortReverse  = true;  // set the default sort order
		
	}
	
	//filter for checkboxs
	$scope.useBeaches = {};
	$scope.useReligious ={};
	$scope.useTrending ={};
	$scope.useMetropolitan ={};
	$scope.useHillorMountain ={};
	$scope.useHeritage ={};
	$scope.useAdventure ={};
	$scope.useGreenCity ={};
	$scope.useDesert ={};
	$scope.useNightLife ={};

	
	// Watch the pants that are selected
    $scope.$watch(function () {
        return {
            cityData: $scope.cityData,
            useBeaches: $scope.useBeaches,
            useReligious: $scope.useReligious,
            useTrending: $scope.useTrending,
            useMetropolitan: $scope.useMetropolitan,
            useHillorMountain: $scope.useHillorMountain,
            useHeritage: $scope.useHeritage,
            useAdventure: $scope.useAdventure,
            useGreenCity: $scope.useGreenCity,
            useDesert: $scope.useDesert,
            useNightLife: $scope.useNightLife,
            slider: $scope.slider
        }
    }, function (value) {
        var selected;
        
    $scope.count = function (prop, value) {
         return function (el) {
             return el[prop] == value;
         };
    };
    
    $scope.beachGroup = uniqueItems($scope.cityData, 'isBeachCity');
    var filterAfterBeach = [];        
    selected = false;
    for (var j in $scope.cityData) {
        var p = $scope.cityData[j];
        for (var i in $scope.useBeaches) {
            if ($scope.useBeaches[i]) {
                selected = true;
                if (i == p.isBeachCity) {
                	filterAfterBeach.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterBeach = $scope.cityData;
    }
    
    $scope.religiousGroup = uniqueItems($scope.cityData, 'isReligious');
    var filterAfterReligious = [];        
    selected = false;
    for (var j in filterAfterBeach) {
        var p = filterAfterBeach[j];
        for (var i in $scope.useReligious) {
            if ($scope.useReligious[i]) {
                selected = true;
                if (i == p.isReligious) {
                	filterAfterReligious.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterReligious = filterAfterBeach;
    }
    
    $scope.trendingGroup = uniqueItems($scope.cityData, 'isTrending');
    var filterAfterTrending = [];        
    selected = false;
    for (var j in filterAfterReligious) {
        var p = filterAfterReligious[j];
        for (var i in $scope.useTrending) {
            if ($scope.useTrending[i]) {
                selected = true;
                if (i == p.isTrending) {
                	filterAfterTrending.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterTrending = filterAfterReligious;
    }
    
    
    $scope.metropolitanGroup = uniqueItems($scope.cityData, 'isMetropolitan');
    var filterAfterMetropolitan = [];        
    selected = false;
    for (var j in filterAfterTrending) {
        var p = filterAfterTrending[j];
        for (var i in $scope.useMetropolitan) {
            if ($scope.useMetropolitan[i]) {
                selected = true;
                if (i == p.isMetropolitan) {
                	filterAfterMetropolitan.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterMetropolitan = filterAfterTrending;
    }
    
    
    $scope.hillorMountainGroup = uniqueItems($scope.cityData, 'isHillorMountain');
    var filterAfterHillorMountain = [];        
    selected = false;
    for (var j in filterAfterMetropolitan) {
        var p = filterAfterMetropolitan[j];
        for (var i in $scope.useHillorMountain) {
            if ($scope.useHillorMountain[i]) {
                selected = true;
                if (i == p.isHillorMountain) {
                	filterAfterHillorMountain.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterHillorMountain = filterAfterMetropolitan;
    }
    
    
    $scope.heritageGroup = uniqueItems($scope.cityData, 'isHeritage');
    var filterAfterHeritage = [];        
    selected = false;
    for (var j in filterAfterHillorMountain) {
        var p = filterAfterHillorMountain[j];
        for (var i in $scope.useHeritage) {
            if ($scope.useHeritage[i]) {
                selected = true;
                if (i == p.isHeritage) {
                	filterAfterHeritage.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterHeritage = filterAfterHillorMountain;
    }
    
    $scope.adventureGroup = uniqueItems($scope.cityData, 'isAdventure');
    var filterAfterAdventure = [];        
    selected = false;
    for (var j in filterAfterHeritage) {
        var p = filterAfterHeritage[j];
        for (var i in $scope.useAdventure) {
            if ($scope.useAdventure[i]) {
                selected = true;
                if (i == p.isAdventure) {
                	filterAfterAdventure.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterAdventure = filterAfterHeritage;
    }
    
    $scope.greenCityGroup = uniqueItems($scope.cityData, 'isGreenCity');
    var filterAfterGreenCity = [];        
    selected = false;
    for (var j in filterAfterAdventure) {
        var p = filterAfterAdventure[j];
        for (var i in $scope.useGreenCity) {
            if ($scope.useGreenCity[i]) {
                selected = true;
                if (i == p.isGreenCity) {
                	filterAfterGreenCity.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterGreenCity = filterAfterAdventure;
    }
    
    $scope.desertGroup = uniqueItems($scope.cityData, 'isDesert');
    var filterAfterDesert = [];        
    selected = false;
    for (var j in filterAfterGreenCity) {
        var p = filterAfterGreenCity[j];
        for (var i in $scope.useDesert) {
            if ($scope.useDesert[i]) {
                selected = true;
                if (i == p.isDesert) {
                	filterAfterDesert.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterDesert = filterAfterGreenCity;
    }
    
    
    $scope.nightLifeGroup = uniqueItems($scope.cityData, 'isNightLife');
    var filterAfterNightLife = [];        
    selected = false;
    for (var j in filterAfterDesert) {
        var p = filterAfterDesert[j];
        for (var i in $scope.useNightLife) {
            if ($scope.useNightLife[i]) {
                selected = true;
                if (i == p.isNightLife) {
                	filterAfterNightLife.push(p);
                    break;
                }
            }
        }        
    }
    if (!selected) {
    	filterAfterNightLife = filterAfterDesert;
    }
    
    if(displaySliderAndDistanceSort){
	    var filterAfterDistance=[];
	    
	    filterAfterDistance = distanceFilter(filterAfterNightLife,$scope.slider.minValue,$scope.slider.maxValue);
	    
	    $scope.filteredCity = filterAfterDistance;
    }else{
    	$scope.filteredCity = filterAfterNightLife;
    }
    
    }, true);
    
    $scope.$watch('filtered', function (newValue) {
        if (angular.isArray(newValue)) {
            console.log(newValue.length);
        }
    }, true);

}

var uniqueItems = function (data, key) {
    var result = [];
    if (!data || !data.length) { return; }
    for (var i = 0; i < data.length; i++) {
        var value = data[i][key];
 
        if (result.indexOf(value) == -1) {
            result.push(value);
        }
    
    }
    return result;
};

var distanceFilter =function(items, greaterThan, lowerThan) { 
    //then we filter the array with dedicated ES5 method
	if (!items || !items.length) { return; }
	
    items = items.filter(function(item){
        //if item price is included between the two boundaries we return it
        return item.distanceFromCurrentCity >= greaterThan && item.distanceFromCurrentCity <= lowerThan;
    });
    //then we return the filtered items array
    return items;
};

app.filter('count', function() {
    return function(collection, key) {
      var out = "test";
      for (var i = 0; i < collection.length; i++) {
        
      }
      return out;
    }
});
/*========================== checkbox filter ================================end*/


/**
 * @author jdhirendrajoshi
 * resultCategoryController controller method which retrives the result data of cities and sets $cityData to the list of cities returned. 
 * showdetailCity function takes city as an argument and stores the value in dataService which shares the data with detailController
 */
app.controller('resultCategoryController',function($scope,$rootScope,cityCategoryFactory,$state,$stateParams){
	$rootScope.error = '';
	//to display the footer
	$rootScope.isHomeController=false;
	$rootScope.pageTitle = $stateParams.categoryType+' tourism | places to visit in '+$stateParams.categoryType+' | Platovi - places to visit';
	$rootScope.keywords = ['places to visit in '+$stateParams.categoryType,'travel '+$stateParams.categoryType,$stateParams.categoryType+' tourism','weekend getaways for '+$stateParams.categoryType,'trips in category '+$stateParams.categoryType, 'travel tips for '+$stateParams.categoryType];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//show loader
	$scope.showLoader = true;
	
	$scope.category = $stateParams.categoryType;
	
	var query=cityCategoryFactory.query({categoryName:$scope.category});
	query.$promise.then(function(data){
		$scope.cityData=(data);
		
		//do not show loader
		$scope.showLoader = false;
		
	});
	
	/*==========================  filter ================================*/
	filter($scope,false); //do not display distance slider and distance order by
	
    /*==========================  filter ================================*/
	
	

});



/**
 * @author jdhirendrajoshi
 * stateResultController controller method which retrives the result data of state and sets $cityData to the list of cities returned. 
 * showdetailCity function takes city as an argument and stores the value in dataService which shares the data with detailController
 */
app.controller('stateResultController',function($scope,$rootScope,stateFactory,citiesInStateFactory,$state,$stateParams){
	$rootScope.error = '';
	//to display the footer
	$rootScope.isHomeController=false;
	$rootScope.pageTitle = $stateParams.stateName+' tourism | places to visit in '+$stateParams.stateName+' | Platovi - places to visit';
	$rootScope.keywords = ['places to visit in '+$stateParams.stateName,'travel in '+$stateParams.stateName,$stateParams.stateName+' tourism','weekend getaways in '+$stateParams.stateName,'trips in '+$stateParams.stateName, 'travel tips in '+$stateParams.stateName];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//show loader
	$scope.showLoader = true;
	
	$scope.stateName = $stateParams.stateName;
	
	var query=stateFactory.get({stateName:$scope.stateName});
	query.$promise.then(function(data){
		$scope.stateData=(data);
		console.log("state: "+$scope.stateData.stateName);
		console.log("state id: "+$scope.stateData.country.countryId);
		
		var citiesInState=citiesInStateFactory.query({stateId:$scope.stateData.stateId});
		citiesInState.$promise.then(function(data){
			$scope.cityData=data;
			
			//do not show loader
			$scope.showLoader = false;
		});
		
	});
	
	/*==========================  filter ================================*/
	filter($scope,false); //do not display distance slider and distance order by
	
    /*==========================  filter ================================*/
	
	

});


/**
 * @author jdhirendrajoshi
 * countryResultController controller method which retrives the result data of country and sets $cityData to the list of cities returned. 
 * showdetailCity function takes city as an argument and stores the value in dataService which shares the data with detailController
 */
app.controller('countryResultController',function($scope,$rootScope,countryFactory,citiesInCountryFactory,statesInCountryFactory,$state,$stateParams){
	$rootScope.error = '';
	//to display the footer
	$rootScope.isHomeController=false;
	$rootScope.pageTitle = $stateParams.countryName+' tourism | places to visit in '+$stateParams.countryNam+' | Platovi - places to visit';
	$rootScope.keywords = ['places to visit in '+$stateParams.countryName,'travel in '+$stateParams.countryName,$stateParams.countryName+' tourism','weekend getaways in '+$stateParams.countryName,'trips in '+$stateParams.countryName, 'travel tips for '+$stateParams.countryName];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//show loader
	$scope.showLoader = true;
	
	$scope.countryName = $stateParams.countryName;
	
	$scope.descriptionLength=500;
	
	
	
	var query=countryFactory.get({countryName:$scope.countryName});
	query.$promise.then(function(data){
		$scope.countryData=(data);
		console.log("country: "+$scope.countryData.countryName);
		console.log("country id: "+$scope.countryData.countryId);
		
		var citiesInCountry=citiesInCountryFactory.query({countryId:$scope.countryData.countryId});
		citiesInCountry.$promise.then(function(data){
			$scope.cityData=data;
		});
		
		var statesInCountry=statesInCountryFactory.query({countryId:$scope.countryData.countryId});
		statesInCountry.$promise.then(function(data){
			$scope.stateData=data;
			//do not show loader
			$scope.showLoader = false;
		});
		
	});
	
	// more/less clicked on
	$scope.showContentMoreOrLess = function(){
    	if($scope.descriptionLength === 500){
    		$scope.descriptionLength = $scope.countryData.description.length;
      }else{
    	  $scope.descriptionLength = 500;
      }    	
    }  
	
	/*==========================  filter ================================*/
	/*filter($scope,false); *///do not display distance slider and distance order by
	
    /*==========================  filter ================================*/
	
	

});


/**
 * @author jdhirendrajoshi
 * resultDetailController controller method 
 * disqusConfig
 */
app.controller('resultDetailController',function($scope,$rootScope,$timeout,cityDetailFactory,placeFactory,citiesNearByFactory,temperatureFactory,romeToRioFactory,googleFactory,$http,$stateParams,NgMap,$window, $mdDialog, $mdMedia){
	$rootScope.error = '';	
	//to display the footer
	$rootScope.isHomeController=false;
	$rootScope.pageTitle = $stateParams.cityName+' tourism | places to visit in '+$stateParams.cityName+' | Platovi - places to visit';
	$rootScope.keywords = ['places to visit in '+$stateParams.cityName,'travel in '+$stateParams.cityName,$stateParams.cityName+' tourism','weekend getaways in '+$stateParams.cityName,'trips in '+$stateParams.cityName, 'travel tips for '+$stateParams.cityName];
	$rootScope.defaultMediumImagePath = 'img/city/default.jpg';
	
	//show loader
	$scope.showLoader = true;
	
	
	/*detail place overview and dialog*/	
	$scope.getIndexFromJson = function(obj, keyToFind) {
	    var i = 0, key;
	    for (key in obj) {
	        if (key == keyToFind) {
	            $scope.selectedIndex=i+1;
	            $window.scrollTo(0, 0); 
	        }
	        i++;
	    }
	};
	
	//Angular dialog box for detail places
	$scope.showAdvanced = function(ev,key,object) {
	    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
	    $scope.placeInfo = key;
	    $scope.allPlaces = object;
	    
	    $mdDialog.show({
	     // controller: DialogController,
	      scope: $scope.$new(),
	      templateUrl: 'partials/dialog.tmpl.html',
	      parent: angular.element(document.body),
	      targetEvent: ev,
	      clickOutsideToClose:true,
	      fullscreen: useFullScreen
	    });
	   $timeout(function(){
	    	
		   	
		   $scope.$on('mapInitialized', function (event, map){
			   $scope.placeMap = map;
			    window.setTimeout(function() {
			      window.google.maps.event.trigger(map, 'resize');
			      map.setCenter(new google.maps.LatLng($scope.placeInfo.latitude,$scope.placeInfo.longitude));
			    }, 500)
			});
	    },100);
	   
	    
	    $scope.$watch(function() {
	      return $mdMedia('xs') || $mdMedia('sm');
	    }, function(wantsFullScreen) {
	      $scope.customFullscreen = (wantsFullScreen === true);
	    });
	  };
	  
	$scope.cancel = function() {
		    $mdDialog.cancel();
	};
	
	//next navigation
	$scope.getNextPlaceDetail= function(index,object){
		
		var newIndex;
		if(index<object.length-1)
			newIndex=index+1;
		else
			newIndex= 0;
		
		$scope.placeInfo= object[newIndex];
	};
	
	//previous navigation
	$scope.getPreviousPlaceDetail= function(index,object){
		
		var newIndex;
		
		if(index==0)
			newIndex=object.length-1;
		else
			newIndex=index-1;
		
		$scope.placeInfo= object[newIndex];
	};
	
	
	
	
	//Angular dialog box ends
 
 
 	
	
	//get background classes for green color for rating
	$scope.getBackgroudClass = function(rating){
		if(rating>=4)
			return "greenFive";
		else if(rating>=3)
			return "greenFour";
		else if(rating>=2)
			return "greenThree";
		else if(rating>=1)
			return "greenTwo";
		else
			return "greenOne";
	};
	
	$scope.descriptionLength=500;
	
	// more/less clicked on
	$scope.showContentMoreOrLess = function(){
    	if($scope.descriptionLength === 500){
    		$scope.descriptionLength = $scope.currentCity.description.length;
      }else{
    	  $scope.descriptionLength = 500;
      }    	
    }  
	
	var vm=this;
	
	$scope.sourceCityGeo={};
	$scope.destinationCityGeo={};
	var currentCity=cityDetailFactory.get({cityName:$stateParams.cityName});
	currentCity.$promise.then(function(data){
		$scope.currentCity=data;
	
		
		var placesInCity=placeFactory.get({cityId:$scope.currentCity.cityId});
		placesInCity.$promise.then(function(data){
			$scope.placesInCity=data;
			//do not show loader
	        $scope.showLoader = false;
	        	
		});
		
		var citiesNearCity = citiesNearByFactory.query({cityName:$scope.currentCity.cityName,latitude:$scope.currentCity.latitude,longitude:$scope.currentCity.longitude});
		citiesNearCity.$promise.then(function(data){
			$scope.citiesNearCity=data;
		});
		
		var cityForWeather = {
                q: $scope.currentCity.cityName
            };

        temperatureFactory.get(cityForWeather, function(successResult) {
                $scope.weather = successResult;
        }, function(errorResult) {
                console.log('Error in weather: ' + errorResult);
        }); 
        
        $scope.categories = [];  
        filterCategory($scope,$scope.currentCity);
        //console.log($scope.categories);
        
        $scope.destinationCity = $scope.currentCity.cityName+', '+$scope.currentCity.country.countryName;
        $scope.destinationCityGeo = {'lat':$scope.currentCity.latitude,'lng':$scope.currentCity.longitude};
        
      
		
      //disqus config
    	$scope.disqusConfig = {
    		    disqus_shortname: 'platovi',
    		    disqus_identifier: $scope.currentCity.cityId,
    		    disqus_url: window.location.href,
    		    disqus_title: $scope.currentCity.cityName+' Comments'
    	};
    	
    	
	});
	
	//google maps resize and set the center again
	$scope.$on('mapInitialized', function (event, map){
		      window.setTimeout(function() {
		        window.google.maps.event.trigger(map, 'resize');
		        map.setCenter(new google.maps.LatLng($scope.currentCity.latitude,$scope.currentCity.longitude));
		      
		      }, 1000)
	});
	
	$scope.getRoutes = function(isValid){
		console.log($scope.sourceCityGeo.lat);
		if(isValid){
			var sourceAndDestination = {
	        		//oName:$scope.sourceCity,
	        		//dName:$scope.destinationCity	,
	        		oPos:$scope.sourceCityGeo.lat+','+$scope.sourceCityGeo.lng,
	        		dPos:$scope.destinationCityGeo.lat+','+$scope.destinationCityGeo.lng,
	        		currencyCode:'INR'
	        };
			
			romeToRioFactory.get(sourceAndDestination,function(successResult){
	        	$scope.routeInfo = successResult;
	        	console.log("rome2rio "+successResult);
	        }, function(errorResult){
	        	console.log('Error while getting route information: ' + errorResult);
	        });
		}
	};
	
	
	/* If latitude and longitude not present in rootscope from home controller the set it */
	//use google reverse geocode api to set sourceCity
	if($rootScope.latitude==='' || $rootScope.latitude==null || $rootScope.longitude==='' || $rootScope.longitude==null ){
		if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position){
		    	$rootScope.$apply(function(){
		    		$rootScope.latitude = position.coords.latitude;
		    		$rootScope.longitude = position.coords.longitude;
		        
		        var latAndLon = {'latlng': $rootScope.latitude+','+$rootScope.longitude};
		        googleFactory.get(latAndLon,function(data){
			        	if (data.status == 'OK') {
				                if (data.results[0]) {
				                    var add= data.results[0].formatted_address ;
				                    var  value=add.split(",");
				                    count=value.length;
				                    country=value[count-1];
				                    //state=value[count-2];
				                    city=value[count-3];
				                    $scope.sourceCity=city+','+country;
				                    $scope.sourceCityGeo = {'lat':$rootScope.latitude,'lng':$rootScope.longitude};
				                }
				        }
		        },function(errorResult){
		        	console.log('Error while getting google city information: ' + errorResult);
		        });
		      });
		    });
		};
	}
	/*
	 * Else set from the existing rootScope
	 */
	else{
		var latAndLon = {'latlng': $rootScope.latitude+','+$rootScope.longitude};
        googleFactory.get(latAndLon,function(data){
	        	if (data.status == 'OK') {
		                if (data.results[0]) {
		                    var add= data.results[0].formatted_address ;
		                    var  value=add.split(",");
		                    count=value.length;
		                    country=value[count-1];
		                    //state=value[count-2];
		                    city=value[count-3];
		                    $scope.sourceCity=city+','+country;
		                    $scope.sourceCityGeo = {'lat':$rootScope.latitude,'lng':$rootScope.longitude};
		                }
		        }
        },function(errorResult){
        	console.log('Error while getting google city information: ' + errorResult);
        });
	}
	
	// asynchronously get the city name
	  $scope.getSourceLocation = function(val) {
	    return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
	      params: {
	        address: val,
	        sensor: false
	      }
	    }).then(function(response){
	      return response.data.results.map(function(item){
	    	  console.log(item);
	    	  $scope.sourceCityGeo = {'lat':item.geometry.location.lat,'lng':item.geometry.location.lng};
	        return item.formatted_address;
	      });
	    });
	  };
	  
	  
	// asynchronously get the city name
	  $scope.getDestinationLocation = function(val) {
	    return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
	      params: {
	        address: val,
	        sensor: false
	      }
	    }).then(function(response){
	      return response.data.results.map(function(item){
	    	  console.log(item);
	    	  $scope.destinationCityGeo = {'lat':item.geometry.location.lat,'lng':item.geometry.location.lng};
	        return item.formatted_address;
	      });
	    });
	  };
	  
	  $scope.getClassColor= function(someValue){
		     if(someValue=="Fly" || someValue=="flight")
		            return "icon-circular-purple";
		     else if(someValue=="Drive" || someValue=="car")
		         return "icon-circular-red";
		     else if(someValue=="Train" || someValue=="train")
		         return "icon-circular-blue";
		     else if(someValue=="Bus" || someValue=="bus")
		         return "icon-circular-green";
		     else
		    	 return "icon-circular-yellow";
		     
		    };
		    
		    

	$scope.getBorderColor = function(someValue) {
		if (someValue == "Fly" || someValue == "flight")
			return "left-border-purple";
		else if (someValue == "Drive" || someValue == "car")
			return "left-border-red";
		else if (someValue == "Train" || someValue == "train")
			return "left-border-blue";
		else if (someValue == "Bus" || someValue == "bus")
			return "left-border-green";
		else
			return "left-border-yellow";

	};
	
	$scope.getClassIcon = function(someValue) {
		if (someValue == "Fly" || someValue == "flight")
			return "fa-plane";
		else if (someValue == "Drive" || someValue == "car")
			return "fa-car";
		 else if(someValue == "Train" || someValue == "train")
	         return "fa-train";
	     else if(someValue == "Bus" || someValue == "bus")
	         return "fa-bus";
	     else
	    	 return "fa-arrow-right";
		};
 
	
	
});

function DialogController($scope, $mdDialog) {
	
    
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
	    $mdDialog.hide(answer);
	  };
	}

var filterCategory = function($scope,items){
	
	for (i in items) {
        if(items[i]=='Y' || items[i]=='Yes' ){
        	$scope.categories.push(i);
        }
    }
	
}



/**
 * @author jdhirendrajoshi
 * filter to remove is from text and transform to uppercase
 */
app.filter('removeIsFilter', function() {
	    return function(text) {
		var str = text.replace('is', '');
		var res = str.replace(/or/gi, "/");
		return res.toUpperCase();
	};
    
});

/**
 * @author jdhirendrajoshi
 * filter to remove is from text and transform to uppercase
 */
app.filter('replaceIsFilter', function() {
	    return function(text) {
		var str = text.replace('is', '');
		return str.toUpperCase();
	};
    
});

app.config(function($breadcrumbProvider) {
    $breadcrumbProvider.setOptions({
      prefixStateName: 'home'
    });
  });


/**
 * directive to replace the image if error if found in ng-src of an image tag
 */
app.directive('errSrc', function() {
  return {
    link: function(scope, element, attrs) {
      element.bind('error', function() {
        if (attrs.src != attrs.errSrc) {
          attrs.$set('src', attrs.errSrc);
        }
      });
    }
  }
});



/**
 * @author jdhirendrajoshi
 * myAppConfig config configures all the states in the application 
 * 
 */
app.config( function myAppConfig ( $stateProvider, $urlRouterProvider,$locationProvider) {
	
	
	  $stateProvider
    // Home state
    .state('home', {
        url:'/home',
        templateUrl:'partials/home.html',
        controller :'homeController',
        data : { pageTitle : "Home" },
        ncyBreadcrumb: {
            label: 'Home'
          }
        })
    
    // Result state
    .state('result', {
        url:'/near/:cityName?latitude&longitude',
        templateUrl:'partials/result.html',
        controller: 'resultController',
        data : { pageTitle : "Result" },
        ncyBreadcrumb: {
            label: 'Result'
          }
        })
        
     // Result state
    .state('state', {
        url:'/state/:stateName',
        templateUrl:'partials/stateResult.html',
        controller: 'stateResultController',
        data : { pageTitle : "State result" },
        ncyBreadcrumb: {
            label: 'State'
          }
        })
        
     // Result country
    .state('country', {
        url:'/country/:countryName',
        templateUrl:'partials/countryResult.html',
        controller: 'countryResultController',
        data : { pageTitle : "Country result" },
        ncyBreadcrumb: {
            label: 'Country'
          }
        })
        
    // Result on category state
    .state('resultOnCategory', {
        url:'/category/:categoryType',
        templateUrl:'partials/resultOnCategory.html',
        controller: 'resultCategoryController',
        data : { pageTitle : "Result" },
        ncyBreadcrumb: {
            label: 'Category'
          }
        })
	  
	 // detail state
    .state('detail', {
        url:'/detail/:cityName',
        templateUrl:'partials/detail.html',
        controller: 'resultDetailController',
        data : { pageTitle : "Detail" },
        ncyBreadcrumb: {
            label: 'Detail',
            parent: 'result'
          }
        });
	  
	  /*$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		}).hashPrefix('!');*/
	  
	 $urlRouterProvider.otherwise( '/home' );
	 
	 //hashbang url
	 $locationProvider.hashPrefix('!');
});

/*app.config( function ( $locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
});*/


/**
 * @author jdhirendrajoshi
 * ErrorInterceptor to handle errors received from $http i.e server side errors
 */
app.config(function ($provide, $httpProvider) {
    $provide.factory('ErrorInterceptor', function ($q,$injector) {
    	
    	var $rootScope = $injector.get('$rootScope');
		$rootScope.error = '';	
        return {
            responseError: function(rejection) {
            	if(rejection.status==404){
            		
            		$rootScope.error = "Nothing found"; //custom
            		console.log("Error: "+$rootScope.error);
            	}
            	else{
            		
            		$rootScope.errors=rejection.statusText;
            		console.log("Error: "+$rootScope.error);
            	}
                return $q.reject(rejection);
            }
        };
    });

    $httpProvider.interceptors.push('ErrorInterceptor');
});
