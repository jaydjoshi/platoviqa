<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="places to visit in ${categoryName}, explore places to visit in ${categoryName} category">
    <meta name="keywords" content="platovi, places to visit in ${categoryName} , ${categoryName} travel, ${categoryName} tourism, trip in ${categoryName}">
    <meta name="author" content="platovi">
    <title>${categoryName} Tourism| places to visit in ${categoryName} | Platovi - places to visit</title>
    <meta content='${categoryName} Tourism| places to visit in ${categoryName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
</head>
<body data-ng-app="platoviWebApp">
	<%@ include file="header.jsp" %>


	<!-- Page Content  -->
	<div class="bg-light-gray">
		<header class="sb-page-header-detail" style="background-image: url(../app/${categoryImgPath})">
			<div class="container city-detail-head">
				<div class="city-detail_text">
					<div class="row">
						<h1>${categoryName}</h1>
					</div>
				</div>
			</div>
		</header>
	</div>
	
	
	<!-- Portfolio Grid Section -->
    <section id="portfolio" class="bg-light-gray large-padding" >
        <div class="container">
        	<div class="col-lg-12 text-center large-padding article-para">
				<h1 class="page-header">Places to visit in ${categoryName} category</h1> 
			</div>
	        <div class="row">	
	        	<c:if test="${not empty cities}">
	        		<c:forEach var="city" items="${cities}">
	        		
	                		<div class="col-md-4 col-sm-4 col-xs-6 portfolio-item" >
			                   
			                    <md-card class="place-card">
			                    	<div>
			                    	 <a href="/city/${fn:replace(city.cityName, ' ', '-')}" class="portfolio-link" md-ink-ripple>
			                    		 
			                        	<span class="rating" data-ng-class="getBackgroudClass(${city.rating})" data-ng-show="${city.rating} > 0">${city.rating}</span> 
			                        	<span class="center-text">
			                        		<h2 class="header-on-image">${city.cityName}</h2>
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