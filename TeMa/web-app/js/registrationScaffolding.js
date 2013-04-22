/**
 * A service for storing one-time messages to be displayed after redirecting to
 * another view.
 */
angular.module('flashService', []).factory('Flash', function() {
    var flash = {};

    flash.getMessage = function() {
        var value = this.message;
        this.message = undefined;
        return value;
    };

    flash.error = function(text) {
        this.message = {level: 'error', text: text};
    };
    flash.success = function(text) {
        this.message = {level: 'success', text: text};
    };
    flash.info = function(text) {
        this.message = {level: 'info', text: text};
    };

    return flash;
});

/**
 * The main scaffolding module.
 */
var scaffoldingModule = angular.module('scaffolding', ['grailsService', 'flashService', 'ui', 'ui.bootstrap.dialog']);

/**
 * Route definitions connecting URL fragments to views and controllers.
 */
scaffoldingModule.config([
    '$routeProvider',
    function($routeProvider) {
        var baseUrl = $('body').data('template-url');
        $routeProvider.
            when('/registration', {templateUrl: baseUrl + '/registration.html', controller: RegistrationCtrl}).
            when('/welcome', {templateUrl: baseUrl + '/welcome.html', controller: RegistrationCtrl}).
            when('/personelData', {templateUrl: baseUrl + '/personelData.html', controller: RegistrationCtrl}).
            when('/chooseMeeting', {templateUrl: baseUrl + '/chooseMeeting.html', controller: MeetingCtrl}).
            when('/chooseOptions', {templateUrl: baseUrl + '/chooseOptions.html', controller: RegistrationCtrl}).
            when('/subscriptionDetails', {templateUrl: baseUrl + '/subscriptionDetails.html', controller: RegistrationCtrl}).
            when('/impressum', {templateUrl: baseUrl + '/impressum.html', controller: RegistrationCtrl}).
            when('/datenschutz', {templateUrl: baseUrl + '/datenschutz.html', controller: RegistrationCtrl}).
            when('/end', {templateUrl: baseUrl + '/end.html', controller: RegistrationCtrl}).
            otherwise({redirectTo: '/registration'});
    }
]).run( function($rootScope, $location, Grails) {
	var baseUrl = $('body').data('template-url');
    // register listener to watch route changes
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
      if ( $rootScope.participant == null ) {
        // no logged user, we should be going to #login
    	// lets see if somethin is in the session
    	  try {
			  if ( next.$route.templateUrl == baseUrl + "/registration.html" || next.$route.templateUrl == baseUrl + "/impressum.html" ||next.$route.templateUrl == baseUrl + "/datenschutz.html" ) {
		          // already going to #login, no redirect needed
		        } else {
		          // not going to #login, we should redirect now
		        	Grails.get({}, function(item) {
						$rootScope.participant = new Grails;
						angular.extend($rootScope.participant, item.participant);
						$rootScope.event = item.participant.event;
						$rootScope.subscription = item.subscription;
						$rootScope.meeting = item.meeting;
						$rootScope.event_i18n = item.event_i18n;
						$rootScope.salutations = item.salutations;
						
						for (salutation in $rootScope.salutations) {
							switch ($rootScope.salutations[salutation].name){
							case "MR": 
								$rootScope.salutations[salutation].value = "Herr"
									break;
							case "MS": 
								$rootScope.salutations[salutation].value = "Frau"
									break;
							default:
								$rootScope.salutations[salutation].value = "NN"
								
							}
						}
						
						$rootScope.vegetarienOptions = [
						                                {value:"Ich bin kein Vegetarier"},
						                                {value:"Ich esse kein Fleisch, aber Fisch"},
						                                {value:"Ich esse weder Fleisch noch Fisch"}
						                                ];
						$rootScope.veganOptions = [
						                                {value:"Ja"},
						                                {value:"Nein"}
						                                ]
				        
				    }, function(){
				    	 $location.path( "/registration" )
				    });
		         
		        }
    	  } catch (exeption) {
    		  $location.path( "/registration" )
    	  }
      }         
    });
 });

/**
 * A directive for including an alert message in the page.
 */
scaffoldingModule.directive('alert', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
        restrict: 'E', // can only be used as an element
        transclude: false, // the element should not contain any content so
							// there's no need to transclude
        scope: {
			level: '@level',
			text: '@text'
        },
        templateUrl: baseUrl + '/alert.html',
        replace: true
    }
});

/**
 * A directive for including a standard pagination block in the page.
 */
scaffoldingModule.directive('pagination', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
        restrict: 'A', // can only be used as an attribute
        transclude: false, // the element should not contain any content so
							// there's no need to transclude
        scope: {
            total: '=total' // inherit the total property from the controller
							// scope
        },
        controller: function($scope, $routeParams, $location) {
            $scope.max = parseInt($routeParams.max) || 10;
            $scope.offset = parseInt($routeParams.offset) || 0;
            $scope.currentPage = Math.ceil($scope.offset / $scope.max);

            $scope.pages = function() {
                var pages = [];
                for (var i = 0; i < Math.ceil($scope.total / $scope.max); i++)
                    pages.push(i);
                return pages;
            };

            $scope.lastPage = function() {
                return $scope.pages().slice(-1)[0];
            };

			$scope.previousPage = function() {
				var offset = $scope.offset - $scope.max;
				if (offset < 0) {
					offset = 0;
				}
				$location.search('offset', offset);
			};

			$scope.nextPage = function() {
				$location.search('offset', $scope.offset + $scope.max);
			};

			$scope.goToPage = function(n) {
				$location.search('offset', n * $scope.max);
			};
        },
        templateUrl: baseUrl + '/pagination.html',
        replace: false
    }
});

/**
 * A directive for making a table header sortable.
 */
scaffoldingModule.directive('sortable', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
		restrict: 'A',
		transclude: true,
		scope: {
			property: '@sortable'
		},
		controller: function($scope, $routeParams, $location) {
			$scope.isSortedBy = function() {
				return $routeParams.sort === $scope.property;
			};

			$scope.isSortedByAscending = function() {
				return $scope.isSortedBy() && $routeParams.order !== 'desc';
			};

			$scope.isSortedByDescending = function() {
				return $scope.isSortedBy() && $routeParams.order === 'desc';
			};

			$scope.sort = function() {
				var order;
				if ($scope.isSortedByAscending()) {
					order = 'desc';
				} else {
					order = 'asc';
				}
				$location.search('sort', $scope.property).search('order', order);
			};
		},
		link: function(scope, element) {
			element.bind('click', function() {
				scope.$apply(scope.sort);
			});
		},
		templateUrl: baseUrl + '/sortable.html',
		replace: false
	}
});

function toArray(element) {
    return Array.prototype.slice.call(element);
}

Function.prototype.curry = function() {
    if (arguments.length < 1) {
        return this; // nothing to curry with - return function
    }
    var __method = this;
    var args = toArray(arguments);
    return function() {
        return __method.apply(this, args.concat(toArray(arguments)));
    }
}

/**
 * Generic $resource error handler used by all controllers.
 */
function errorHandler($scope, $location, Flash, response) {
    switch (response.status) {
        case 404: // resource not found - return to the list and display
					// message returned by the controller
            Flash.error(response.data.message);
            $location.path('/registration');
            break;
        case 409: // optimistic locking failure - display error message on the
					// page
            $scope.message = {level: 'error', text: response.data.message};
            break;
        case 422: // validation error - display errors alongside form fields
            $scope.errors = response.data.errors;
            break;
        default: // TODO: general error handling
    }
}

function ListCtrl($scope, $routeParams, $location, Grails, Flash) {
    Grails.list($routeParams, function(list, headers) {
        $scope.list = list;
        $scope.total = parseInt(headers('X-Pagination-Total'));
        $scope.message = Flash.getMessage();
    }, errorHandler.curry($scope, $location, Flash));

	$scope.show = function(item) {
		$location.path('/show/' + item.id);
	};
}

function ShowCtrl($scope, $routeParams, $location, Grails, Flash) {
    $scope.message = Flash.getMessage();
    $scope.appUrl = $('body').data('app-url');
    $scope.templateUrl = $('body').data('template-url');

    Grails.get({id: $routeParams.id}, function(item) {
        $scope.item = item;
    }, errorHandler.curry($scope, $location, Flash));

    $scope.delete = function(item) {
        item.$delete(function(response) {
            Flash.success(response.message);
            $location.path('/list');
        }, errorHandler.curry($scope, $location, Flash));
    };
}

function CreateCtrl($scope, $location, Grails, Flash) {
	$scope.item = new Grails;
	$scope.templateUrl = $('body').data('template-url');
	
    $scope.save = function(item) {
        item.$save(function(response) {
            Flash.success(response.message);
            $location.path('/show/' + response.id);
        }, errorHandler.curry($scope, $location, Flash));
    };
}

function EditCtrl($scope, $routeParams, $location, Grails, Flash) {
	$scope.item = new Grails;
	$scope.templateUrl = $('body').data('template-url');
    Grails.get({id: $routeParams.id}, function(item) {
    	angular.extend($scope.item, item);
    }, errorHandler.curry($scope, $location, Flash));

    $scope.update = function(item) {
        item.$update(function(response) {
            Flash.success(response.message);
            $location.path('/show/' + response.id);
        }, errorHandler.curry($scope, $location, Flash));
    };

    $scope.delete = function(item) {
        item.$delete(function(response) {
            Flash.success(response.message);
            $location.path('/list');
        }, errorHandler.curry($scope, $location, Flash));
    };
}