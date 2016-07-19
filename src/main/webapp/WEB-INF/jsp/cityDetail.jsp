<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${city.cityName}, explore places to visit, places to eat and sleep and things to buy in ${city.cityName} and how to reach ${city.cityName} ">
    <meta name="keywords" content="platovi, places to visit in ${city.cityName},${city.cityName} tourism,${city.cityName} travel, weekend getaways near ${city.cityName}">
    <meta name="author" content="platovi">
    <title>${city.cityName} tourism | Places to visit in ${city.cityName} | Platovi - places to visit</title>
    <meta content='${city.cityName} tourism | Places to visit in ${city.cityName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
    
    <script>
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
	      title:"<c:out value="${city.cityName}" />"
	    });
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
	</script>
    
</head>
<body data-ng-app="platoviWebApp">
	
	<%@ include file="header.jsp" %>	
	
	<!-- Page Content -->
	<div class="bg-light-gray" data-ng-controller="cityDetailController">
		
		<header class="sb-page-header-detail" style="background-image: url(../app/${city.imageLargePath})">
			<div class="container city-detail-head">
				<div class="city-detail_text">
					<div class="row">
						<h1>${city.cityName}, <a data-ng-click="formatAndGotoUrl('/state/${city.state.stateName}')">${city.state.stateName}</a> </h1>
						<small class="city-detail-subtext">${city.title}</small>
					</div>
					
					<div class="row city-detail-category">
					
						<!-- <span class="category " data-ng-repeat="category in categories">
					       <a data-ui-sref="resultOnCategory({categoryType : category})">{{category | removeIsFilter}}</a>
					    </span> -->
				    
					</div>
				</div>
			</div>
		</header>
		
		<div class="container" id="detailTabId">
		
			<div class="row">
	        	<div class="col-lg-12 city-content">
	        		<!-- Overview starts -->
					        <div class="row large-padding" >
					            <div class="col-lg-12 text-center large-padding article-para">
					                <h1 class="page-header">${city.cityName} at a glance</h1> 
					                
					               <%-- <p class="small-padding">${city.description}
					              <!--  <span data-ng-show='currentCity.description.length>500'>
										<a class="md-raised" data-ng-click="showContentMoreOrLess()">read {{descriptionLength === 500 ? 'more' : 'less'}}</a>
									</span> -->
					               </p> --%>
					               <p class="small-padding" data-ng-init="isShowMore=true" data-ng-class="{'limited-text':isShowMore}">${city.description}
					              
					               </p>
					               <c:if test="${fn:length(city.description) gt 350}"><md-button data-ng-click="isShowMore = !isShowMore" class="md-raised">Read <span data-ng-show="isShowMore">more</span><span data-ng-hide="isShowMore">less</span></md-button></c:if>
					               
					               
					             </div>
					        </div>
					          <div class="col-lg-12 medium-padding text-center article-para">
					          	<div class="col-lg-12 col-md-12">
					          		<p><i class="fa fa-clock-o"></i> Best time to visit: ${city.bestSeasonToVist} </p>
						          	<p><i class="fa fa-calendar"></i> Ideal duration: ${city.idealDuration} </p>
					          		
						         	<!-- <p><i class="fa fa-sun-o"></i> Current temperature: {{weather.main.temp}} &#8451;</p> -->
						        </div>
						         <div class="col-lg-12 col-md-12 medium-padding">
									  <div>
	    								  <div id="map-canvas" style="height:400px"></div>
    								  </div>
								</div>
							</div>
					<!-- overview ends -->
					
					
					<!-- places to see starts -->
					<c:if test="${not empty see}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Places to see in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToSee" items="${see}">
											
												<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
												<md-card class="detail-card-large"> 
													<div class="row">
														<img src="" class="md-card-image card-image-small" alt="${placeToSee.placeName}" >
													</div>
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/see/${placeToSee.placeName}')"><span class="md-headline">${placeToSee.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													<div class="row">
														<md-card-content>
														<p data-ng-model="placeOverview" class="limited-text-para">
															${placeToSee.placeOverview}
														</p>
														<c:if test="${not empty placeToSee.placeOverview}"><md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/see/${placeToSee.placeName}')" class="md-raised">Read more</md-button></c:if>
														</md-card-content> 
													</div>
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding">
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/see')" class="md-raised md-primary"><i class="fa fa-globe"></i> view all places</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to see ends -->
				      
				      
				      <!-- places to do starts -->
					<c:if test="${not empty thingsToDo}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Things to do in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToDo" items="${thingsToDo}">
											
												<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
												<md-card class="detail-card-large"> 
													<div class="row">
														<img src="" class="md-card-image card-image-small" alt="${placeToDo.placeName}" >
													</div>
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/do/${placeToDo.placeName}')"><span class="md-headline">${placeToDo.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													<div class="row">
														<md-card-content>
														<p data-ng-model="placeOverview" class="limited-text-para">
															${placeToDo.placeOverview}
														</p>
														<c:if test="${not empty placeToDo.placeOverview}"><md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/do/${placeToDo.placeName}')" class="md-raised">Read more</md-button></c:if>
														</md-card-content> 
													</div>
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding" >
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/do')" class="md-raised md-primary"><i class="fa fa-cogs"></i> view all things</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to do ends -->
				      
				     
				      
				      
				         <!-- places to eat starts -->
					<c:if test="${not empty eat}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Places to eat in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToEat" items="${eat}">
											
												<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
												<md-card class="detail-card-small"> 
													
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/eat/${placeToEat.placeName}')"><span class="md-headline">${placeToEat.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding" >
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/eat')" class="md-raised md-primary"><i class="fa fa-cutlery"></i> view all restaurants</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to eat ends -->
				      
				      
				         <!-- places to drink starts -->
					<c:if test="${not empty drink}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Places to drink in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToDrink" items="${drink}">
											
												<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
												<md-card class="detail-card-small"> 
													
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/drink/${placeToDrink.placeName}')"><span class="md-headline">${placeToDrink.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding" >
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/drink')" class="md-raised md-primary"><i class="fa fa-beer"></i> view all places</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to drink ends -->
				      
				      
				         <!-- places to buy starts -->
					<c:if test="${not empty buy}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Places to buy in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToBuy" items="${buy}">
											
												<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
												<md-card class="detail-card-large"> 
													<div class="row">
														<img src="" class="md-card-image card-image-small" alt="${placeToBuy.placeName}" >
													</div>
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/buy/${placeToBuy.placeName}')"><span class="md-headline">${placeToBuy.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													<div class="row">
														<md-card-content>
														<p data-ng-model="placeOverview" class="limited-text-para">
															${placeToBuy.placeOverview}
														</p>
														<c:if test="${not empty placeToBuy.placeOverview}"><md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/buy/${placeToBuy.placeName}')" class="md-raised">Read more</md-button></c:if>
														</md-card-content> 
													</div>
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding" >
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/buy')" class="md-raised md-primary"><i class="fa fa-suitcase"></i> view all things</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to buy ends -->
				      
				      
				          <!-- places to sleep starts -->
					<c:if test="${not empty sleep}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<h1>Places to sleep in ${city.cityName} </h1>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="placeToSleep" items="${sleep}">
											
												<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
												<md-card class="detail-card-medium"> 
													
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a data-ng-click="formatAndGotoUrl('/city/${city.cityName}/sleep/${placeToSleep.placeName}')"><span class="md-headline">${placeToSleep.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									<div class="row small-padding" >
										<md-button data-ng-click="formatAndGotoUrl('/city/${city.cityName}/sleep')" class="md-raised md-primary"><i class="fa fa-bed"></i> view all hotels</md-button>
									</div>
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to sleep ends -->
				      
				      
	        	</div>
        	</div>
        	
        	
        	<!-- how to reach -->
        <div class="row large-padding">
        	<div class="col-lg-12">
        		<h1 class="page-header text-center">How to reach ${city.cityName}</h1>
					<form name="routeForm" class="form" data-ng-init="init('${city.cityName}','${city.latitude}','${city.longitude}')" data-ng-submit="getRoutes(routeForm.$valid)" novalidate>
						<div class="row">
						
						  <div class="col-lg-offset-2 col-lg-4 col-sm-6">
						    <div class="form-group" data-ng-class="{ 'has-error' : routeForm.sourceCity.$invalid && !routeForm.sourceCity.$pristine }">
							     <div class="input-group">
							      <div class="input-group-addon"><i class="fa fa-hand-o-right"></i></div>
							      <input type="text" data-ng-model="sourceCity" data-uib-typeahead="address for address in getSourceLocation($viewValue)" data-typeahead-loading="loadingLocationsSource" data-typeahead-no-results="noResultsSource" class="form-control input-lg" required>
							     </div> 
							    
								    <i data-ng-show="loadingLocationsSource" class="glyphicon glyphicon-refresh"></i>
								    <div data-ng-show="noResultsSource">
								      <i class="glyphicon glyphicon-remove"></i> No Results Found
								    </div>
							   
							 </div>
							</div>
							<div class="col-lg-4 col-sm-6">
							 <div class="form-group" data-ng-class="{ 'has-error' : routeForm.destinationCity.$invalid && !routeForm.destinationCity.$pristine }">
							     <div class="input-group">
							      <!-- <div class="input-group-addon">TO</div> -->
							      <input type="text" data-ng-model="destinationCity" data-uib-typeahead="address for address in getDestinationLocation($viewValue)" data-typeahead-loading="loadingLocationsDestination" data-typeahead-no-results="noResultsDestination" class="form-control input-lg" required>
							      <div class="input-group-addon"><i class="fa fa-hand-o-left"></i></div>
							    </div> 
							    <i data-ng-show="loadingLocationsDestination" class="glyphicon glyphicon-refresh"></i>
								    <div data-ng-show="noResultsDestination">
								      <i class="glyphicon glyphicon-remove"></i> No Results Found
								    </div>
							 </div>
							</div>
							
						
						</div>
						<div class="row">
							<div class="text-center">
								<md-button type="submit" class="md-primary md-raised md-hue-2 btn-lg" data-ng-disabled="routeForm.$invalid" analytics-on="click" analytics-event="clickOnRomeToRioApi"><i class="fa fa-search fa-fw"></i> Search</md-button>
							</div>
						</div>
						
						<div class="row medium-padding">
							<div class="wrap-route">
								<div class="panel panel-default" data-ng-repeat="route in routeInfo.routes">
								  <div class="panel-heading" data-ng-class='getBorderColor(route.name)'>
								  
								    <h3 class="panel-title">{{route.name}}  <span class="icon-circular" data-ng-class='getClassColor(route.name)'><i class="fa" data-ng-class="getClassIcon(route.name)" ></i></span></h3>
								    <p class="">{{route.duration}} mins | {{route.distance}} km</p>
								  </div>
								  
								  <ul class="list-group" >
								    <li class="list-group-item" data-ng-repeat="segment in route.segments">
									    <span class="vertical-progress-full" data-ng-class='getClassColor(segment.kind)'></span>
									    <span class="icon-circular-pink-dot"></span>
									    <span class="icon-circular" data-ng-class='getClassColor(segment.kind)'><i class="fa" data-ng-class="getClassIcon(segment.kind)"></i></span>
									    	<h4 class="route-details-p"><span data-ng-bind="segment.kind"></span><span data-ng-show="segment.sName.length"> from {{segment.sName}} to {{segment.tName}}</span></h4>
									    	<span class="route-details-p">{{segment.distance}} km takes {{segment.duration}} mins</span>
									    	
									    	<p class="route-details-p">{{segment.indicativePrice.price}} {{segment.indicativePrice.currency}}</p>
									</li>
								    
								   <li class="list-group-item" >
								   		<span class="icon-circular-pink-dot"></span>
								   		<h4 class="route-details-p">${city.cityName}</h4>
								   		
								   </li>
								  </ul>
								</div>
							</div>
						</div>
					</form>
        	</div>
        </div>
        
        
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
        
        
    	<div class="row large-padding">
    		<div class="col-lg-12">
		        <div id="disqus_thread"></div>
				<script>
				    /**
				     *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
				     *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables
				     */
				    
				    var disqus_config = function () {
				        this.page.url = window.location.href;  // Replace PAGE_URL with your page's canonical URL variable
				        this.page.identifier = <c:out value="${city.cityId}" /> ; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
				        this.page.title = '<c:out value="${city.cityName}" />'+' Comments' ;
				    };
				    
				    (function() {  // REQUIRED CONFIGURATION VARIABLE: EDIT THE SHORTNAME BELOW
				        var d = document, s = d.createElement('script');
				        
				        s.src = '//platovi.disqus.com/embed.js';  // IMPORTANT: Replace EXAMPLE with your forum shortname!
				        
				        s.setAttribute('data-timestamp', +new Date());
				        (d.head || d.body).appendChild(s);
				    })();
				</script>
				<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>
			</div>
		</div>
        
		
			<c:if test="${not empty citiesNearby}">
				<div class="row large-padding">
		        	<div class="col-lg-12">
		        		<h1 class="page-header text-center" >Other places near ${city.cityName} you may like</h1>
					        <div class="row">
					        <c:forEach var="nearbyCity" items="${citiesNearby}">
					        	<div class="col-md-4 col-sm-6 portfolio-item" >
				                   
				                    <md-card class="place-card">
				                    	<div>
				                    	 <a data-ng-click="formatAndGotoUrl('/city/${nearbyCity.cityName}')" class="portfolio-link">
				                    		 
				                        	<span class="rating" data-ng-class="getBackgroudClass(${nearbyCity.rating})" data-ng-show="${nearbyCity.rating} > 0">${nearbyCity.rating}</span> 
				                        	<span class="center-text">
				                        		<h2>${nearbyCity.cityName}</h2>
				                        		<p class="center-text-muted" data-ng-show="${nearbyCity.distanceFromCurrentCity} > 0">${nearbyCity.distanceFromCurrentCity} km away from ${city.cityName}</p>
				                        	</span>
								         	<img src="../app/${nearbyCity.imageMediumPath}" err-SRC="http://placehold.it/360x240?text={{city.cityName}}" alt="Platovi - places to visit near ${nearbyCity.cityName} city" class="place-img img-responsive" >
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