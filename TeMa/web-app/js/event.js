function EventCtrl($scope, $location, Grails, Flash) {

	$scope.addLanguage = function(language) {
		var locale = $scope.getI18n(language);
		alert('locale.');
	}
	
	$scope.getI18n = function(locale) {
		Grails.newI18n({locale: locale}, function(response) {
	        $scope[locale] = response;
	    }, errorHandler.curry($scope, $location, Flash));
	}
    
}