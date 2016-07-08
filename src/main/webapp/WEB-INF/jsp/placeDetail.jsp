<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${place.placeName}, explore places to visit and how to reach ${place.placeName} ">
    <meta name="keywords" content="${place.placeName} tourism | Places to visit in ${place.placeName} | ${place.placeName} overview , ${place.placeName} tourism,${place.placeName} travel, weekend getaways near ${place.placeName}">
    <meta name="author" content="platovi">
    <title>${place.placeName} tourism | Places to visit in ${place.placeName} | ${place.placeName} overview | Platovi - places to visit</title>
    <meta content='${place.placeName} tourism | Places to visit in ${place.placeName} | ${place.placeName} overview | Platovi - places to visit' name='title'>
    
    <%@ include file="include.jsp" %>
    
    <c:if test="${place.latitude != 0 &&  place.longitude != 0 }">
    <script>
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 12,
	    center: new google.maps.LatLng(  <c:out value="${place.latitude}" /> ,<c:out value="${place.longitude}" />   ),
	    
	  };
	  map = new google.maps.Map(document.getElementById('map-canvas'),
	      mapOptions);
	  
	  var marker = new google.maps.Marker({
	      position: new google.maps.LatLng(  <c:out value="${place.latitude}" /> ,<c:out value="${place.longitude}" />   ), 
	      map: map, 
	      title:"<c:out value="${place.placeName}" />"
	    });
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	</c:if>
</head>
<body data-ng-app="platoviWebApp">
	<%@ include file="header.jsp" %>	
	
	<div class="bg-light-gray" data-ng-controller="cityDetailController">
		<header class="sb-page-header-detail-small" style="background-image: url(../app/${city.imageLargePath})">
			<div class="container city-detail-head">
				<div class="city-detail_text-small">
					<div class="row">
						<h1>${place.placeName }, <a data-ng-click="formatAndGotoUrl('/city/${city.cityName}')" >${city.cityName}</a>, <a data-ng-click="formatAndGotoUrl('/state/${city.state.stateName}')" >${city.state.stateName}</a> </h1>
						
					</div>
					
				</div>
			</div>
		</header>
		
		
		<div class="container" id="detailTabId">
			<div class="row">
				<div class="col-lg-3 col-md-3">
					<div class="row large-padding" >
						<div class="col-lg-12 text-center large-padding article-para">
						<c:if test="${not empty placeTypes}">
						    <ul class="nav nav-tabs tabs-left sideways" role="tablist">
						    	<c:forEach items="${placeTypes}" var="placeT">
							        <li ng-class="{'active': getActiveTab('${placeT[0]}')}">
							            <a class="md-button" data-ng-click="formatAndGotoUrl('/city/${city.cityName}/${placeT[0]}')" >${placeT[0]} (${placeT[1]})</a>
							        </li>
						        </c:forEach>
						    </ul>
					    </c:if>
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
				</div>
		        <div class="col-lg-9 col-md-9 city-content">
		        	<div class="row large-padding" >
					     <div class="col-lg-12 text-center large-padding article-para">
					     	<h1 class="page-header">${place.placeName} at a glance</h1> 
					        	<p class="small-padding">${place.placeOverview}</p>
					     </div>
					</div>
					<c:if test="${place.latitude != 0 &&  place.longitude != 0 }">
						<div class="col-lg-12 medium-padding text-center article-para">
							<div class="col-lg-12 col-md-12 medium-padding">
								  <div>
		    						  <div id="map-canvas" style="height:400px"></div>
	    						  </div>
							</div>
						</div>
					</c:if>
		        </div>
		    </div>
	    </div>
	</div>
	
	
	<%@ include file="footer.jsp" %>
</body>
</html>