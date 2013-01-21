function EventCtrl($scope, $location, Grails, Flash) {

	$scope.availableLanguages = new Array();
	
	$scope.addLanguage = function(language) {
		var locale = $scope.getI18n(language);
	}
	
	$scope.getI18n = function(locale) {
		Grails.newI18n({locale: locale}, function(response) {
	        $scope.availableLanguages.push(response);
	    }, errorHandler.curry($scope, $location, Flash));
	}
    
}


scaffoldingModule.directive('i18ntabs', function(){
	var baseUrl = $('body').data('template-url');
    return {
        restrict: 'A',
        // This HTML will replace the zippy directive.
        replace: true,
        transclude: true,
        templateUrl: baseUrl + '/i18nTabs.html',
        // The linking function will add behavior to the template
        link: function(scope, element, attrs) {
              // Title element
        	var availableLanguages = scope.availableLanguages;
        },
        controller: function($scope, $element) {
            var panes = $scope.availableLanguages = [];

            $scope.select = function(pane) {
                angular.forEach(panes, function(pane) {
                    pane.selected = false;
                });
                pane.selected = true;
            }
            
            $scope.deleteLanguage = function(pane) {
                angular.forEach(panes, function(pane) {
                    pane.selected = false;
                });
                panes.splice(panes.indexOf(pane),1);
            }

            this.addPane = function(pane) {
                $scope.select(pane);
            }
        }
      }
    }).
    directive('i18npane', function() {
    	var baseUrl = $('body').data('template-url');
        return {
          require: '^i18ntabs',
          restrict: 'A',
          transclude: true,
          link: function(scope, element, attrs, tabsCtrl) {
            tabsCtrl.addPane(scope);
          },
          templateUrl: baseUrl + '/i18n.html',
          replace: true
        };
      });