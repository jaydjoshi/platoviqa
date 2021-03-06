<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${fn:length(places)} places to ${placeType} in ${city.cityName}, explore places to ${placeType} in city ${city.cityName }">
    <meta name="keywords" content="platovi, ${fn:length(places)} places to ${placeType} in ${city.cityName}, ${placeType} in ${city.cityName}, ${city.cityName} ${placeType} travel">
    <meta name="author" content="platovi">
    <title>${fn:length(places)} places to ${placeType} in ${city.cityName} | Platovi - places to visit</title>
    <meta content='${fn:length(places)} places to ${placeType} in ${city.cityName} | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
</head>
<body data-ng-app="platoviWebApp">
	<%@ include file="header.jsp" %>	
	
	
	<div class="bg-light-gray" data-ng-controller="cityDetailController">
		<header class="sb-page-header-detail-small" style="{'background-image': 'url('../app/'+${city.imageLargePath})'}">
			<div class="container city-detail-head">
				<div class="city-detail_text-small">
					<div class="row">
						<h1><a href="/city/${fn:replace(city.cityName, ' ', '-')}" >${city.cityName}</a>, <a href="/state/${fn:replace(city.state.stateName, ' ', '-')}" >${city.state.stateName}</a> </h1>
						<small class="city-detail-subtext">${city.title}</small>
					</div>
					
				</div>
			</div>
		</header>
	
	<div class="row text-center">
	<h1 class="large-padding text-center">${fn:length(places)} places to ${placeType} in <a class="link-black" href="/city/${fn:replace(city.cityName, ' ', '-')}"  >${city.cityName} </a></h1>
		<c:forEach var="placeT" items="${placeTypes}">
			<span class="link-type-list medium-padding" data-ng-class="getActivePlaceType('${placeT[0]}')" >
				<a href="/city/${fn:replace(city.cityName, ' ', '-')}/${fn:replace(placeT[0], ' ', '-')}" >${placeT[0]} (${placeT[1]})</a>
	    	</span> 
		</c:forEach>
	</div>
	
	<!-- google ads code block -->
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
	
	 
	<div class="container" id="detailTabId">
		<div class="row">
	        <div class="col-lg-12 city-content">
				<!-- places to ${placeType} starts -->
					<c:if test="${not empty places}">
					  	 <div>  
							<div class="col-lg-12 col-md-12 text-center">
								<div class="large-padding">
									<div class="row">
										<%-- <h1>${placeType} in ${city.cityName} </h1> --%>
									</div>
									<div class="row small-padding">
										<div class="col-lg-12">
											<c:forEach var="place" items="${places}">
											
												<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
												<md-card class="detail-card-large"> 
													<!--  display:none added temporarryly till we dont have images -->
													<div class="row" style="display:none">
														<img src="" class="md-card-image card-image-small" alt="${place.placeName}" >
													</div>
													<div class="row">
														<md-card-title>
															<md-card-title-text class="card-headline"> 
																<a href="/city/${fn:replace(city.cityName, ' ', '-')}/${fn:replace(placeType, ' ', '-')}/${fn:replace(place.placeName, ' ', '-')}" ><span class="md-headline">${place.placeName}</span> </a>
															</md-card-title-text> 
														</md-card-title> 
													</div>
													<div class="row">
														<md-card-content>
														<p data-ng-model="placeOverview" class="limited-text-para">
															${place.placeOverview}
														</p>
														<c:if test="${not empty place.placeOverview}"><md-button href="/city/${fn:replace(city.cityName, ' ', '-')}/${fn:replace(placeType, ' ', '-')}/${fn:replace(place.placeName, ' ', '-')}"  class="md-raised">Read more</md-button></c:if>
														</md-card-content> 
													</div>
												</md-card>
											</div>
											
											</c:forEach>
											
										</div>
									</div>
									
								</div>
							</div>
				          </div>
				      </c:if>
				      <!-- places to ${placeType} ends -->
			</div>
		</div>
	</div>	
	
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>