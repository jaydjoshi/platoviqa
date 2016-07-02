
/**
 * @author jdhirendrajoshi
 * angular module named app with ui.router and ngResurce as dependencies
 */
var app = angular.module('platoviWebApp', ['ui.bootstrap','ngResource','ngAnimate','ngMaterial']);


/**
 * @author jdhirendrajoshi
 * cityFactory factory method to return city URL resource
 */
app.factory('cityNameFactory', function($resource){
	return $resource('/rest/city/name');
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


app.run(function($rootScope,$window) {
   
    $rootScope.formatAndGotoUrl = function(url){
		var newUrl = url.replace(/ /g,"-");
		var finalUrl = "http://" + $window.location.host + newUrl;
		
        $window.location.href = finalUrl;
	};
});

/**
 * @author jdhirendrajoshi
 * homeController controller method with a search method that changes the scope to result and passes cityName as $stateParam 
 */
app.controller('headerController', function($scope,$rootScope,cityNameFactory,$window) {
	
    var query=cityNameFactory.query();
	query.$promise.then(onSuccess);
	var cityNameList=[];
	
	function onSuccess(data){
		
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
				var url = "http://" + $window.location.host + '/city/'+$scope.selectedCity.substring(0,subStringCommaPos).replace(/ /g,"-");
		        $window.location.href = url;
				
			}
			else if(subStr=='State'){
				console.log("header found state");
				// Maharashtra, India (State) 
				//console.log("state", { 'stateName' :  $scope.selectedCity.substring(0,subStringCommaPos) });
				var url = "http://" + $window.location.host + '/state/'+$scope.selectedCity.substring(0,subStringCommaPos).replace(/ /g,"-");
		        $window.location.href = url;
			}
			else if(subStr=='Country'){
				console.log("header found Country");
				//Bracket position cause there is no comma in country like India (Country)
				var url = "http://" + $window.location.host + '/country/'+$scope.selectedCity.substring(0,subStringOpenBracketPos-1).replace(/ /g,"-");
		        $window.location.href = url;
			}
			else{
				//default route to city
				var url = "http://" + $window.location.host + '/city/'+$scope.selectedCity.substring(0,subStringCommaPos).replace(/ /g,"-");
		        $window.location.href = url;
			}
		}
	}
    
});


app.controller('cityDetailController',function($scope,$rootScope,$location,$window,$timeout,temperatureFactory,romeToRioFactory,$http){
	
	
	$scope.init = function(cityName, lat, lon){
		
		$scope.destinationCity = cityName;
		$scope.destinationCityGeo = {'lat':lat,'lng':lon};
	}
	
	$scope.getRoutes = function(isValid){
		
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
	        	
	        }, function(errorResult){
	        	console.log('Error while getting route information: ' + errorResult);
	        });
		}
	};
	
	/*var cityForWeather = {
            q: $scope.currentCity.cityName
        };*/

   /* temperatureFactory.get(cityForWeather, function(successResult) {
            $scope.weather = successResult;
    }, function(errorResult) {
            console.log('Error in weather: ' + errorResult);
    }); */
    
    // asynchronously get the city name
	  $scope.getSourceLocation = function(val) {
	    return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
	      params: {
	        address: val,
	        sensor: false
	      }
	    }).then(function(response){
	      return response.data.results.map(function(item){
	    	  
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
		  if (someValue == "Fly" || someValue == "flight" || someValue.indexOf("Fly") > -1 || someValue.indexOf("flight") > -1)
		            return "icon-circular-purple";
		     else if (someValue == "Drive" || someValue == "car" || someValue.indexOf("Drive") > -1 || someValue.indexOf("car") > -1)
		         return "icon-circular-red";
		     else if(someValue == "Train" || someValue == "train" || someValue.indexOf("Train") > -1 || someValue.indexOf("train") > -1)
		         return "icon-circular-blue";
		     else if(someValue == "Bus" || someValue == "bus" || someValue.indexOf("Bus") > -1 || someValue.indexOf("bus") > -1)
		         return "icon-circular-green";
		     else
		    	 return "icon-circular-yellow";
		     
		    };
		    
		    

	$scope.getBorderColor = function(someValue) {
		if (someValue == "Fly" || someValue == "flight" || someValue.indexOf("Fly") > -1 || someValue.indexOf("flight") > -1)
			return "left-border-purple";
		else if (someValue == "Drive" || someValue == "car" || someValue.indexOf("Drive") > -1 || someValue.indexOf("car") > -1)
			return "left-border-red";
		else if(someValue == "Train" || someValue == "train" || someValue.indexOf("Train") > -1 || someValue.indexOf("train") > -1)
			return "left-border-blue";
		else if(someValue == "Bus" || someValue == "bus" || someValue.indexOf("Bus") > -1 || someValue.indexOf("bus") > -1)
			return "left-border-green";
		else
			return "left-border-yellow";

	};
	
	$scope.getClassIcon = function(someValue) { 
		if (someValue == "Fly" || someValue == "flight" || someValue.indexOf("Fly") > -1 || someValue.indexOf("flight") > -1)
			return "fa-plane";
		else if (someValue == "Drive" || someValue == "car" || someValue.indexOf("Drive") > -1 || someValue.indexOf("car") > -1)
			return "fa-car";
		 else if(someValue == "Train" || someValue == "train" || someValue.indexOf("Train") > -1 || someValue.indexOf("train") > -1)
	         return "fa-train";
	     else if(someValue == "Bus" || someValue == "bus" || someValue.indexOf("Bus") > -1 || someValue.indexOf("bus") > -1)
	         return "fa-bus";
	     else
	    	 return "fa-arrow-right";
		};
		
		
	$scope.getActivePlaceType = function(placeType){
		var url = $location.absUrl();
		
		var type = url.substring(url.lastIndexOf("/")+1,url.length);
		
		if(placeType == type){
			return "active-link";
		}
		else{
			return "";
		}
	};
	
	$scope.getActiveTab = function(tab){
		var url = $location.absUrl();
		if(url.indexOf(tab)> -1){
			return true;
		}
		else{
			return false;
		}
	};
	
	
	$scope.homeDropdown = "Mumbai";
	
	//set timeout and max age for cached geolocation
	var geoOptions = {
		     timeout: 10 * 1000,
		     maximumAge: 5*60*1000
		  }
	
	//if($scope.homeDropdown == "You"){    	
    	//if($scope.latitude==='' || $scope.latitude==null || $scope.longitude==='' || $scope.longitude==null ){
	//if($scope.latitude!=0 )
	    	if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(function(position){
			    	$scope.$apply(function(){
			    		$scope.homeDropdown = "You";
			    		$scope.displayYou=true;
			    		$scope.latitude = position.coords.latitude;
			    		$scope.longitude = position.coords.longitude;
			    		console.log("blcok 1: "+$scope.latitude +" "+ $scope.longitude);
			      });
			    },function(err){
			    	//user said no
			    	if (err.code == 1) {
			    	    // user said no!
			    	  }
			    }, geoOptions);
			}
	    	else{
	    		$scope.latitude='';
	    		$scope.longitude='';
	    		console.log("blcok 3: "+$scope.latitude +" "+ $scope.longitude);
	    	}
    	//}
	//}
    
    //set the geolocation details if You is selected from the dropdown
   /* $scope.getLocation = function(){
    	if($scope.homeDropdown == "You"){    	
    		if($scope.latitude==='' || $scope.latitude==null || $scope.longitude==='' || $scope.longitude==null ){
	    		if (navigator.geolocation) {
				    navigator.geolocation.getCurrentPosition(function(position){
				    	$scope.$apply(function(){
				    		$scope.latitude = position.coords.latitude;
				    		$scope.longitude = position.coords.longitude;
				    		console.log("blcok 2: "+$scope.latitude +" "+ $scope.longitude);
				      });
				    });
				}
    		}
		}
    	else{
    		$scope.latitude='';
    		$scope.longitude='';
    		console.log("blcok 3: "+$scope.latitude +" "+ $scope.longitude);
    	}
	};*/
	
	$scope.search = function(){
		console.log('search()');
		if($scope.homeDropdown == 'You' && !($scope.latitude === undefined) && !($scope.longitude === undefined))
			var url = "http://" + $window.location.host + '/PlaToVi/near/'+$scope.homeDropdown+'?latitude='+$scope.latitude+'&longitude='+$scope.longitude;
		else
			var url = "http://" + $window.location.host + '/PlaToVi/near/'+$scope.homeDropdown;
        $window.location.href = url;
		
	}
	
	
	
	
});

