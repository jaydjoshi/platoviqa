<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${state.stateName}, explore places to visit, places to eat and sleep and things to buy in ${state.stateName} and how to reach ${state.stateName} ">
    <meta name="keywords" content="platovi, places to visit in ${state.stateName},${state.stateName} tourism,${state.stateName} travel, weekend getaways near ${state.stateName}">
    <meta name="author" content="platovi">
    <title>${state.stateName} tourism | Places to visit in ${state.stateName} | Platovi - places to visit</title>
    <meta content='${state.stateName} tourism | Places to visit in ${state.stateName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
    
</head>
<body data-ng-app="platoviWebApp">

	<%@ include file="header.jsp" %>

	<!-- Page Content -->
	<div  class="bg-light-gray">
		<header class="sb-page-header">
			<div class="container">
				<h1>${state.stateName}, <a data-ng-click="formatAndGotoUrl('/country/${state.country.countryName}')">${state.country.countryName}</a><small>${state.title}</small></h1>
			</div>
		</header>
	</div>
	
	<c:if test="${not empty cities}">
	<!-- Portfolio Grid Section -->
    <section id="portfolio" >
        <div class="container">
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
	        		
	                
	                <!-- <div class="text-center" data-ng-show="!(filteredCity  | filter:query  | orderBy:sortType:sortReverse ).length">
	                	<h3>
						  	<span class="fa-broken-heart fa-stack">
								<i class="fa fa-heart fa-stack-2x"></i>
								<i class="fa fa-flash fa-stack-1x"></i>
							</span>																					
						  	<br/>
						  	<span> No places found </span>
						  </h3>
	                	<p>Would you like to go back to <a data-ui-sref="home">home</a> page</p>
	                </div> -->
	            </div>
        </div>
    </section>
    </c:if>
		
	<%@ include file="footer.jsp" %>
</body>
</html>