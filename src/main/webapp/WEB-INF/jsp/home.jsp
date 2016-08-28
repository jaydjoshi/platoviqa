<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Platovi - places to visit, is the best travel platform, it helps you discover places to visit and gives you all the information needed to plan your trip.">
    <meta name="keywords" content="platovi,explore places to visit, tourism website in India, travel website, weekend getaways, trip planner in India, travel agent, best travel deals, places to visit in India, tourism website, tourism website in India, tourist attractions website, tourism website blog,best travel website india, travel website blog, tours and travel website in India ">
    <meta name="author" content="platovi">
    <meta name="google-site-verification" content="7lohucsd7mhYxq9HxUqKQdiQxJjwqkFEvSjap6sU9Gc" />
    
    <title>Platovi - places to visit</title>
    <meta content='Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
   	
</head>
<body data-ng-app="platoviWebApp">

	<%@ include file="header.jsp" %>

	<!-- Header -->
    <section class="content-section-background">
    <div class="" data-ng-controller="cityDetailController">
        <div class="container">
            <div class="row">
                <div class="col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 col-xs-offset-2 col-xs-8 hidden-xs">
                    <div class="intro-message centered">
                        <img class="img-responsive" alt="Platovi- places to visit logo" src="../app/img/PlatoviLogo_white.png">
                 </div>
                 </div>
             </div>
             <div class="row">
                 <div class="col-lg-offset-1 col-lg-10"> 
                        <div class="text-center">
                        	<form class="search-form" role="form" >
                        		<div class="row control-group">
  									<div class="form-group col-lg-12">
                        				<!-- <br> -->
                        				<h1 class="home-header">Explore places to visit</h1>
                        			</div>
                        		</div>
  								<div class="row control-group">
  									<div class="form-group">
	  									<div class="input-group col-lg-offset-1 col-lg-10">
	  										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-globe fa-fw"></i> <b>Near</b> </span>
	  										<select data-ng-model="homeDropdown" style="font-family: 'FontAwesome';" class="form-control input-lg">
								              <option data-ng-show="displayYou" value="You">You</option>
								              <c:forEach items="${metropolitanCities}" var="metCity">
											            <option value="${metCity}">${metCity}</option>
											  </c:forEach>
							          		</select>
	  									</div>
  									</div>
  									
  									<div class="form-group">
	  									<div class="col-lg-offset-1 col-lg-10 text-center animated tada">
	  										<md-button type="submit" data-ng-click="search()" class="md-raised md-primary btn-large"><i class="fa fa-search fa-fw"></i> Search</md-button>
	  									</div>
  									</div>
  								</div>
  							</form>
                        </div>
                    </div>
                </div>
            <!-- </div> -->

	</div>
        <!-- /.container -->
    </div>
    <!-- /.intro-header -->
    
    
    </section>
		
	<section class="bg-light-gray">
    	
    	<!-- Explore top states -->
    	<c:if test="${not empty states}">
	    	<section class="large-padding">
	    		<h1 class="page-header text-center">Explore top places by State</h1>
		    	<div class="container medium-padding">
		    		<div class="row">	
		    			<c:forEach var="state" items="${states}">
				    		<div class="col-md-4 col-sm-4 col-xs-6 portfolio-item animate-repeat low-vertical-padding">
								<md-card class="place-card" >
			                    	<div>
			                    	  <a href="/state/${fn:replace(state.stateName, ' ', '-')}" class="portfolio-link" md-ink-ripple>
			                        	<span class="center-text">
			                        		<h2 class="header-on-image">${state.stateName}</h2>
			                        	</span>
			                        	<img src="../app/${state.imageMediumPath}" alt="Places to visit in ${state.stateName} state | ${state.stateName} tourism" class="place-img img-responsive" >
							         </a>
							         </div>				        
							  </md-card>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>
		
		
    	
    	<!-- Explore places by category -->
    	<c:if test="${not empty categories}">
	    	<section class="no-padding large-padding">
	    		<h1 class="page-header text-center ">Explore places by Category</h1>
		        <div class="container-fluid medium-padding">
		            <div class="row no-gutter">
		            	<c:forEach var="category" items="${categories}">
			                <div class="col-lg-4 col-sm-6" >
			                    <a href="/category/${fn:replace(category.categoryName, ' ', '-')}" class="portfolio-box " md-ink-ripple>
			                    	<span class="center-text"><h2>${category.categoryName}</h2></span>
			                        <img src="../app/${category.imgMediumPath}" class="img-responsive" alt="Places to visit in ${category.categoryName}">
			                    </a>
			                </div>
		                </c:forEach>
		            </div>
		        </div>
		    </section> 
	    </c:if>
	    
	    <!-- Explore top cities -->
	    <c:if test="${not empty cities}">
	    	<section class="large-padding">
	    		<h1 class="page-header text-center">Explore top places to visit</h1>
		    	<div class="container medium-padding">
		    		<div class="row">	
		    			<c:forEach var="city" items="${cities}">
				    		<div class="col-md-4 col-sm-4 col-xs-6 portfolio-item animate-repeat low-vertical-padding">
								<md-card class="place-card" >
								<div>
					
					
									
									<a class="portfolio-link" href="/city/${fn:replace(city.cityName, ' ', '-')}" md-ink-ripple>
										<span class="center-text">
											<h2 class="header-on-image">${city.cityName}</h2>
										</span> 
										<img src="../app/${city.imageMediumPath}" alt="Places to visit in ${city.cityName} city | ${city.cityName} tourism" class="place-img img-responsive">
									</a>
								
								</div>
								</md-card>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>
		
		<!-- google ads code -->
		<div class="large-padding text-center">
			<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- PlatoviAds -->
				<ins class="adsbygoogle"
				     style="display:block"
				     data-ad-client="ca-pub-7127616125972801"
				     data-ad-slot="2281909774"
				     data-ad-format="auto">
				</ins>
			<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
		
		<!-- Blogs -->
		<section class="bg-dark large-padding blog-section">
			<div class="container text-center">
	            <div class="call-to-action">
	                <h2>Click below to view our blog posts!</h2>
	                <md-button class="md-raised md-primary btn-large"><a href="http://blog.platovi.com" class="md-raised md-primary btn-large white-color"><i class="fa fa-search"></i> Blog</a></md-button>
	               
	            </div>
       		</div>
		</section>
	</section>
		

	<%@ include file="footer.jsp" %>
	
</body>
</html>