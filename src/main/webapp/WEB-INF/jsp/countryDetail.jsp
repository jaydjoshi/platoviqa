<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${country.countryName}, explore places to visit, places to eat and sleep and things to buy in ${country.countryName} and how to reach ${country.countryName} ">
    <meta name="keywords" content="platovi, places to visit in ${country.countryName},${country.countryName} tourism,${country.countryName} travel">
    <meta name="author" content="platovi">
    <title>${country.countryName} tourism | Places to visit in ${country.countryName} | Platovi - places to visit</title>
    <meta content='${country.countryName} tourism | Places to visit in ${country.countryName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
    
    <!-- <script>
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 8,
	    center: new google.maps.LatLng(  <c:out value="${city.latitude}" /> ,<c:out value="${city.longitude}" />   ),
	    
	  };
	  map = new google.maps.Map(document.getElementById('map-canvas'),
	      mapOptions);
	  
	  var marker = new google.maps.Marker({
	      position: new google.maps.LatLng(  <c:out value="${city.latitude}" /> ,<c:out value="${city.longitude}" />   ), 
	      map: map, 
	      title:"<c:out value="${country.countryName}" />"
	    });
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
	</script> -->
    
</head>
<body data-ng-app="platoviWebApp">
<%@ include file="header.jsp" %>	
	
	<!-- Page Content -->
	<div class="bg-light-gray">
		<header class="sb-page-header-detail" style="{'background-image': 'url('../app/'+${country.imageLargePath})'}">
			<div class="container city-detail-head">
				<div class="city-detail_text">
					<div class="row">
						<h1>${country.countryName}</a> </h1>
						<small class="city-detail-subtext">${country.title}</small>
					</div>
					
					<div class="row city-detail-category">
					
						<!-- <span class="category " data-ng-repeat="category in categories">
					       <a data-ui-sref="resultOnCategory({categoryType : category})">{{category | removeIsFilter}}</a>
					    </span> -->
				    
					</div>
				</div>
			</div>
		</header>
	</div>
	
	
	<div class="container" id="detailTabId">
		
			<div class="row">
	        	<div class="col-lg-12 city-content">
	        		<!-- Overview starts -->
					        <div class="row large-padding" >
					            <div class="col-lg-12 text-center large-padding article-para">
					                <h1 class="page-header">${country.countryName} at a glance</h1> 
					                
					               <p class="small-padding">${country.description}
					              <!--  <span data-ng-show='currentCity.description.length>500'>
										<a class="md-raised" data-ng-click="showContentMoreOrLess()">read {{descriptionLength === 500 ? 'more' : 'less'}}</a>
									</span> -->
					               </p>
					               
					             </div>
					        </div>
					          <!-- <div class="col-lg-12 medium-padding text-center article-para">
					          	
						         <div class="col-lg-12 col-md-12 medium-padding">
									  <div>
	    								  <div id="map-canvas" style="height:400px"></div>
    								  </div>
								</div>
							</div> -->
					<!-- overview ends -->
				</div>
				
				<c:if test="${not empty states}">
					<div class="row large-padding">
		        		<div class="col-lg-12">
		        			<h1 class="page-header text-center" >Explore top states in ${country.countryName}</h1>
					        	<div class="row">
					        	<c:forEach var="state" items="${states}">
					        		<div class="col-md-4 col-sm-6 portfolio-item" >
					                    <md-card class="place-card">
					                    	<div>
					                    	 <a data-ng-click="formatAndGotoUrl('/state/${state.stateName}')" class="portfolio-link">
					                    		 
					                        	<span class="rating" data-ng-class="getBackgroudClass(${state.rating})" data-ng-show="${state.rating} > 0">${state.rating}</span> 
					                        	<span class="center-text">
					                        		<h2>${state.stateName}</h2>
					                        	</span>
									         	<img src="../app/${state.imageMediumPath}" err-SRC="http://placehold.it/360x240?text={{state.stateName}}" alt="Platovi - places to visit near ${state.stateName} state" class="place-img img-responsive" >
									         </a>
									         </div>				        
									  </md-card>
				                </div>
					        </c:forEach>
				            </div>
				    	 </div>
					</div>
				</c:if>
				
				<c:if test="${not empty cities}">
					<div class="row large-padding">
		        		<div class="col-lg-12">
		        			<h1 class="page-header text-center" >Explore top cities in ${country.countryName}</h1>
					        	<div class="row">
					        	<c:forEach var="city" items="${cities}">
					        		<div class="col-md-4 col-sm-6 portfolio-item" >
					                    <md-card class="place-card">
					                    	<div>
					                    	 <a data-ng-click="formatAndGotoUrl('/city/${city.cityName}')" class="portfolio-link">
					                    		 
					                        	<span class="rating" data-ng-class="getBackgroudClass(${city.rating})" data-ng-show="${city.rating} > 0">${city.rating}</span> 
					                        	<span class="center-text">
					                        		<h2>${city.cityName}</h2>
					                        	</span>
									         	<img src="../app/${city.imageMediumPath}" err-SRC="http://placehold.it/360x240?text={{city.cityName}}" alt="Platovi - places to visit near ${city.cityName} city" class="place-img img-responsive" >
									         </a>
									         </div>				        
									  </md-card>
				                </div>
					        </c:forEach>
				                
				            </div>
				    	 </div>
					</div>
				</c:if>
				
			</div>
		</div>
	
	
<%@ include file="footer.jsp" %>
	
</body>
</html>