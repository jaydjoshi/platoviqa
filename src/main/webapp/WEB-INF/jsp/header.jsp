<nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" data-ng-controller="headerController">
		        <div class="container-fluid">
		            <!-- Brand and toggle get grouped for better mobile display -->
		            <div class="navbar-header page-scroll">
		                <button type="button" class="navbar-toggle" data-ng-init="navCollapsed = true" data-ng-click="navCollapsed = !navCollapsed">
		                    <span class="sr-only">Toggle navigation</span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </button>
		                <a class="navbar-brand" title="home page" href="http://www.platovi.com"><img src="<c:url value="/app/img/PlatoviLogo.png"/>" alt="Platovi logo" class="logo"></img></a>
		            </div>
		
		            <!-- Collect the nav links, forms, and other content for toggling -->
		            <div class="collapse navbar-collapse" data-uib-collapse="navCollapsed">
		                <form name="searchForm" class="navbar-form navbar-right" role="search" data-ng-submit="detailCity(searchForm.$valid)" novalidate>
				          <div class="input-group autocomplete">
				            <input type="text" name="selectedCity" class="form-control" placeholder="Search" data-ng-model="selectedCity" data-uib-typeahead="city for city in cityNameList | filter:$viewValue | limitTo:5" class="form-control" required>
				          	<div class="input-group-btn">
				                <button class="btn btn-default btn-header" type="submit" data-ng-disabled="searchForm.$invalid"><i class="glyphicon glyphicon-search"></i></button>
				            </div>
				          </div>
				        </form>
		            </div>
		            <!-- /.navbar-collapse -->
		        </div>
		        <!-- /.container-fluid -->
		    </nav>