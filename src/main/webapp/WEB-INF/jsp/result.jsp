<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${fn:length(cities)} amazing places to visit near ${cityName}, explore ${fn:length(cities)} places near ${cityName} and plan your travel ">
    <meta name="keywords" content="platovi, ${fn:length(cities)} places to visit near ${cityName} ,travel near ${cityName},  trip near ${cityName}, weekend getaways near ${cityName}">
    <meta name="author" content="platovi">
    <title>${cityName} Tourism | ${fn:length(cities)} places to visit near ${cityName} | Platovi - places to visit</title>
    <meta content='${cityName} Tourism | ${fn:length(cities)} places to visit near ${cityName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
</head>
<body data-ng-app="platoviWebApp">
	<%@ include file="header.jsp" %>


	<!-- Page Content -->
	<div  class="bg-light-gray">
		<header class="sb-page-header">
			<div class="container text-center">
			
				<h1>Places to visit near ${cityName}</h1>
				<c:if test="${not empty cities}"><h2 class="header-on-image">${fn:length(cities)} places found</h2></c:if>
			</div>
		</header>
	</div>
	
	
	<!-- Portfolio Grid Section -->
    <section id="portfolio" >
        <div class="container">
	        <div class="row">	
	        	<c:if test="${not empty cities}">
	        		<c:forEach var="city" items="${cities}">
	        		
	                		<div class="col-md-4 col-sm-4 col-xs-6 portfolio-item" >
			                   
			                    <md-card class="place-card" >
			                    	<div>
			                    	 <a href="/city/${fn:replace(city.cityName, ' ', '-')}" class="portfolio-link" md-ink-ripple>
			                    		 
			                        	<span class="rating" data-ng-class="getBackgroudClass(${city.rating})" data-ng-show="${city.rating} > 0">${city.rating}</span> 
			                        	<span class="center-text">
			                        		<h2 class="header-on-image">${city.cityName}</h2>
			                        		<p class="center-text-muted" data-ng-show="${city.distanceFromCurrentCity} > 0">${city.distanceFromCurrentCity} km away</p>
			                        	</span>
							         	<img src="../app/${city.imageMediumPath}" err-SRC="http://placehold.it/360x240?text={{city.cityName}}" alt="Platovi - places to visit near ${city.cityName} city" class="place-img img-responsive" >
							         </a>
							         </div>				        
							  </md-card>
			                </div>
	        		</c:forEach>
	        	</c:if>
	        		
	             <c:if test="${empty cities}">
	                 <div class="text-center">
	                	<h3>
						  	<span class="fa-broken-heart fa-stack">
								<i class="fa fa-heart fa-stack-2x"></i>
								<i class="fa fa-flash fa-stack-1x"></i>
							</span>																					
						  	<br/>
						  	<span> No places found :( </span>
						  </h3>
	                	<p>Would you like to go back to <a href="http://www.platovi.com">home</a> page</p>
	                </div> 
	             </c:if>
	            </div>
        </div>
    </section>
    
	
	<%@ include file="footer.jsp" %>
</body>
</html>