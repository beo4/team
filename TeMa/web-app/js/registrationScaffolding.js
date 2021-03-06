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
		this.message = {
			level : 'error',
			text : text
		};
	};
	flash.success = function(text) {
		this.message = {
			level : 'success',
			text : text
		};
	};
	flash.info = function(text) {
		this.message = {
			level : 'info',
			text : text
		};
	};

	return flash;
});

/**
 * The main scaffolding module.
 */
var scaffoldingModule = angular.module('scaffolding', [ 'grailsService',
		'flashService', 'ui', 'ui.bootstrap.dialog' ]);

/**
 * Route definitions connecting URL fragments to views and controllers.
 */
scaffoldingModule
		.config([ '$routeProvider', function($routeProvider) {
			var baseUrl = $('body').data('template-url');

			$routeProvider.when('/registration', {
				templateUrl : baseUrl + '/registration.html',
				controller : 'RegistrationCtrl'
			}).when('/welcome', {
				templateUrl : baseUrl + '/welcome.html',
				controller : 'RegistrationCtrl'
			}).when('/personelData', {
				templateUrl : baseUrl + '/personelData.html',
				controller : 'RegistrationCtrl'
			}).when('/chooseMarketplace', {
				templateUrl : baseUrl + '/chooseMarketplace.html',
				controller : 'RegistrationCtrl'
			}).when('/subscriptionDetails', {
				templateUrl : baseUrl + '/subscriptionDetails.html',
				controller : 'RegistrationCtrl'
			}).when('/impressum', {
				templateUrl : baseUrl + '/impressum.html',
				controller : 'RegistrationCtrl'
			}).when('/datenschutz', {
				templateUrl : baseUrl + '/datenschutz.html',
				controller :'RegistrationCtrl'
			}).when('/end', {
				templateUrl : baseUrl + '/end.html',
				controller : 'RegistrationCtrl'
			}).when('/registrationEnd', {
				templateUrl : baseUrl + '/registrationEnd.html',
				controller : 'RegistrationCtrl'
			}).when('/chooseRepresentative', {
				templateUrl : baseUrl + '/chooseRepresentative.html',
				controller : 'RegistrationCtrl'
			}).when('/chooseRepresentativeEnd', {
				templateUrl : baseUrl + '/chooseRepresentativeEnd.html',
				controller : 'RegistrationCtrl'
			}).when('/representativ', {
				templateUrl : baseUrl + '/representativ.html',
				controller : 'RegistrationCtrl'
			}).when('/survey', {
				templateUrl : baseUrl + '/survey.html',
				controller : 'RegistrationCtrl'
			}).when('/thankYou', {
				templateUrl : baseUrl + '/thankYou.html',
				controller : 'RegistrationCtrl'
			}).otherwise({
				redirectTo : '/registration'
			});

		} ])
		.run(
				function($rootScope, $location, Grails) {
					var baseUrl = $('body').data('template-url');
					// register listener to watch route changes

					$rootScope.steps = [ {
						active : true,
						title : 'Home'
					}, {
						active : false,
						title : 'Anmeldung'
					}, {
						active : false,
						title : 'Registrierung'
					}, {
						active : false,
						title : 'Servicethemen'
					}, {
						active : false,
						title : 'Bestätigung'
					} ];

					$rootScope.travelDetail = new Grails();

					$rootScope.setStep = function(activatestep) {
						for (var step in $rootScope.steps) {
							if ($rootScope.steps[step].title == activatestep) {
								$rootScope.steps[step].active = true;
							} else {
								$rootScope.steps[step].active = false;
							}
						}
					};

					$rootScope
							.$on(
									"$routeChangeStart",
									function(event, next, current) {
										if ($rootScope.participant === null) {
											// no logged user, we should be
											// going to #login
											// lets see if somethin is in the
											// session
											try {
												if (next.$$route.templateUrl == baseUrl+ "/registration.html" || next.$$route.templateUrl == baseUrl + "/impressum.html" || next.$$route.templateUrl == baseUrl + "/datenschutz.html") {
													// already going to #login,
													// no redirect needed
												} else {
													// not going to #login, we
													// should redirect now
													Grails
															.get(
																	{},
																	function(
																			item) {
																		$rootScope.participant = new Grails();
																		angular
																				.extend(
																						$rootScope.participant,
																						item.participant);
																		$rootScope.event = item.participant.event;
																		$rootScope.subscription = item.subscription;
																		$rootScope.meeting = item.meeting;
																		$rootScope.event_i18n = item.event_i18n;
																		$rootScope.salutations = item.salutations;
																		$rootScope.marketplace = item.marketplace;

																		for ( var salutation in $rootScope.salutations) {
																			switch ($rootScope.salutations[salutation].name) {
																			case "MR":
																				$rootScope.salutations[salutation].value = "Herr";
																				break;
																			case "MS":
																				$rootScope.salutations[salutation].value = "Frau";
																				break;
																			default:
																				$rootScope.salutations[salutation].value = "NN";

																			}
																		}

																	},
																	function() {
																		$location
																				.path("/registration");
																	});

												}
											} catch (exeption) {
												$location.path("/registration");
											}
										}

										switch ($location.path()) {
										case "/registration":
											$rootScope.setStep('Home');
											break;
										case "/end":
											$rootScope.setStep('Bestätigung');
											break;
										case "/chooseMeeting":
										case "/subscriptionDetails":
											$rootScope.setStep('Anmeldung');
											break;
										case "/personelData":
											$rootScope
													.setStep('Teilnehmerdaten');
											break;
										case "/chooseMarketplace":
											$rootScope.setStep('Servicethemen');
											break;

										}
									});
				});

/**
 * A directive for including an alert message in the page.
 */
scaffoldingModule.directive('alert', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
		restrict : 'E', // can only be used as an element
		transclude : false, // the element should not contain any content so
		// there's no need to transclude
		scope : {
			level : '@level',
			text : '@text'
		},
		templateUrl : baseUrl + '/alert.html',
		replace : true
	};
});

/**
 * A directive for including a standard pagination block in the page.
 */
scaffoldingModule.directive('pagination', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
		restrict : 'A', // can only be used as an attribute
		transclude : false, // the element should not contain any content so
		// there's no need to transclude
		scope : {
			total : '=total' // inherit the total property from the
								// controller
		// scope
		},
		controller : function($scope, $routeParams, $location) {
			$scope.max = parseInt($routeParams.max,10) || 10;
			$scope.offset = parseInt($routeParams.offset,10) || 0;
			$scope.currentPage = Math.ceil($scope.offset / $scope.max);

			$scope.pages = function() {
				var pages = [];
				for ( var i = 0; i < Math.ceil($scope.total / $scope.max); i++)
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
		templateUrl : baseUrl + '/pagination.html',
		replace : false
	};
});

/**
 * A directive for making a table header sortable.
 */
scaffoldingModule.directive('sortable', function() {
	var baseUrl = $('body').data('common-template-url');
	return {
		restrict : 'A',
		transclude : true,
		scope : {
			property : '@sortable'
		},
		controller : function($scope, $routeParams, $location) {
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
				$location.search('sort', $scope.property)
						.search('order', order);
			};
		},
		link : function(scope, element) {
			element.bind('click', function() {
				scope.$apply(scope.sort);
			});
		},
		templateUrl : baseUrl + '/sortable.html',
		replace : false
	};
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
	};
};

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
		$scope.message = {
			level : 'error',
			text : response.data.message
		};
		break;
	case 422: // validation error - display errors alongside form fields
		$scope.errors = response.data.errors;
		break;
	default: // TODO: general error handling
	}
}