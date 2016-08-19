<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Platovi - places to visit helps you discover places near you and gives you all the information needed for your trip and plan your travel.">
    <meta name="keywords" content="platovi, places to visit, tourism, travel, weekend getaways">
    <meta name="author" content="platovi">
    <title>Platovi - places to visit</title>
    <meta content='Platovi - places to visit' name='title'>
    <link rel="icon" type="image/png" href="../app/img/favicon.png"/>
    
    <%@ include file="include.jsp" %>
    
</head>
<body data-ng-app="platoviWebApp">

<%@ include file="header.jsp" %>
<div class="jumbotron jumbortron-error text-center" >
	  <h1>
	  	<span class="fa-broken-heart fa-stack">
			<i class="fa fa-heart fa-stack-2x"></i>
			<i class="fa fa-flash fa-stack-1x"></i>
		</span>																					
	  	<br/>
	  </h1>
	  <h2>
	  	<span>
	  		<c:if test="${not empty errMsg}">
				${errMsg}
			</c:if>
	  	 </span>
	  	 
	  </h2>
	 
	  <p>Maybe these will help you find your way...</p>
</div>

<!-- Explore top cities -->
<section class="bg-light-gray">
    	<section class="large-padding">
    		<h1 class="page-header text-center">Explore top places to visit</h1>
	    	<div class="container medium-padding">
	    		<div class="row">	
	    			<c:forEach var="city" items="${cities}">
			    		<div class="col-md-4 col-sm-4 col-xs-6 portfolio-item animate-repeat">
							<md-card class="place-card" >
							<div>
				
								<a href="/city/${fn:replace(city.cityName, ' ', '-')}" ></a>
				
								
								<a class="portfolio-link" href="/city/${fn:replace(city.cityName, ' ', '-')}" md-ink-ripple>
									<span class="center-text">
										<h2>${city.cityName}</h2>
									</span> 
									<img src="../app/${city.imageMediumPath}" alt="Platovi - places to visit in ${city.cityName} city" class="place-img img-responsive">
								</a>
							
							</div>
							</md-card>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
</section>

<%@ include file="footer.jsp" %>

</body>

</html>