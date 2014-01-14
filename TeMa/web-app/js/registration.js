scaffoldingModule.controller('RegistrationCtrl', function($scope, $location, $rootScope, Grails, Flash) {
	$scope.message = Flash.getMessage();
	$scope.isEmailChanged = false;
	
	$scope.differenceInDays = function (firstdate, seconddate) {
        
        var dt1 = firstdate.split('.'),
            dt2 = seconddate.split('.'),
            one = new Date(dt1[2], dt1[1], dt1[0]),
            two = new Date(dt2[2], dt2[1], dt2[0]);
        
        var millisecondsPerDay = 1000 * 60 * 60 * 24;
        var millisBetween = two.getTime() - one.getTime();
        var days = millisBetween / millisecondsPerDay;
    
        return Math.floor(days);      
    };

	$scope.doTheBack = function() {
		if ($location.$$path === "/chooseMarketplace") {
			$location.path('/personelData');
		} else if ($location.$$path === "/personelData") {
			$location.path('/subscriptionDetails');
		} else if ($location.$$path === "/chooseRepresentative") {
			$location.path('/subscriptionDetails');
		} else if ($location.$$path === "/end") {
			$location.path('/chooseMarketplace');
		}

	};

	$rootScope.getLoggedInUser = function() {
		// try to load a logged in user
		return Grails.get({}, function(item) {
			$rootScope.participant = new Grails();
			angular.extend($rootScope.participant, item.participant);
			$rootScope.event = item.participant.event;
			$rootScope.subscription = item.subscription;
			$rootScope.meeting = item.meeting;
			$rootScope.event_i18n = item.event_i18n;
			$rootScope.salutations = item.salutations;

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

			if (!item.participant.language)
				$location.path('/chooseLanguage');
			else if ($rootScope.subscription) {
				$location.path('/subscriptionDetails');
			} else {
				$location.path('/chooseMeeting');
			}

		}, errorHandler.curry($scope, $location, Flash));
	};

	$scope.loadParticipant = function() {
		Grails.get({
			loginToken : $scope.loginToken
		}, function(item) {
			$rootScope.participant = new Grails();
			angular.extend($rootScope.participant, item.participant);
			angular.extend($rootScope.participant.marketplaceOptions, item.marketplaceOptions);
			$rootScope.participantTravelOptions = item.travelOptions;
			$rootScope.participantOtherOptions = item.otherOptions;
			$rootScope.event = item.participant.event;
			$rootScope.subscription = item.subscription;
			$rootScope.meeting = item.meeting;
			$rootScope.event_i18n = item.event_i18n;
			$rootScope.meeting_i18n = item.meeting_i18n;
			$rootScope.salutations = item.salutations;

			for (var salutation in $rootScope.salutations) {
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

			$rootScope.allreadySubscribed = item.participant.confirmed;

			if (!item.participant.language)
				$location.path('/chooseLanguage');
			else if (item.participant.state.name === "REPRESENTATIV") {
				$location.path('/representativ');
			} else if ($rootScope.subscription) {
				$location.path('/subscriptionDetails');
			} else {
				$location.path('/chooseMeeting');
			}

		}, errorHandler.curry($scope, $location, Flash));
	};

	$scope.gotoStep2 = function() {
		if ($rootScope.subscription) {
			$location.path('/subscriptionDetails');
		} else {
			$location.path('/chooseMeeting');
		}
	};

	$scope.updateParticipant = function(item) {
		if ($scope.isEmailChanged) {

		}

		item.$update(function(response) {
			Flash.success(response.message);
			$rootScope.participant = new Grails();
			angular.extend($rootScope.participant, response.participant);
			angular.extend($rootScope.participant.marketplaceOptions, response.marketplaceOptions);
			$location.path('/chooseMarketplace');

		}, errorHandler.curry($scope, $location, Flash));
	};
	
	$scope.createRepresentativ = function(item) {
		item.meeting = $rootScope.meeting;
		item.event = $rootScope.event;
		item.language = $rootScope.participant.language;
		
		item.$createRepresentativ(function(response) {
			Flash.success(response.message);
			$location.path('/chooseRepresentativeEnd');

		}, errorHandler.curry($scope, $location, Flash));
	};

	$scope.confirmParticipant = function(item) {
			item.confirmed = true;
			item.$confirm(function(response) {
				Flash.success(response.message);
				$rootScope.participant = new Grails();
				angular.extend($rootScope.participant, response.participant);
				angular.extend($rootScope.participant.marketplaceOptions, response.marketplaceOptions);
				$scope.confirmedee = true;
	
			}, errorHandler.curry($scope, $location, Flash));
			
		
	};

	$scope.updateOptions = function(item) {
		
				
		item.$update(function(response) {
			Flash.success(response.message);
			$rootScope.participant = new Grails;
			angular.extend($rootScope.participant, response.participant);
			angular.extend($rootScope.participant.marketplaceOptions, response.marketplaceOptions);
			$location.path('/end');
			
		}, errorHandler.curry($scope, $location, Flash));

		//$location.path('/end');
	};

	$scope.emailChanged = function() {
		$scope.isEmailChanged = true;
	};
	
	$scope.isSelected = function(object) {
		if (object instanceof String || object instanceof boolean) {
			return object != null && object
		}
		
		return false
	}

	$scope.updateAccount = function(item, state) {
		var representativ = false;
		item.state = state;
		item.confirmed = false;
		item.state ===  "CONFIRMED" ? item.account = true : item.account = false;
		
		item.$update(function(response) {
			Flash.success(response.message);
			// $rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
			angular.extend($rootScope.participant.marketplaceOptions, item.marketplaceOptions);
			if ($rootScope.participant.account) {
				$location.path('/personelData');
			} else {
				if (item.state.name === "REPRESENTATIV") {
					$rootScope.item = new Grails;
					$location.path('/chooseRepresentative'); 
				}  else {
					$location.path('/end');
				}
			}

		}, errorHandler.curry($scope, $location, Flash));
	};
});

scaffoldingModule
		.directive(
				'bsdtpicker',
				function($parse) {
					var baseUrl = $('body').data('template-url');
					return {
						restrict : 'C',
						// The linking function will add behavior to the
						// template
						link : function(scope, element, attrs) {
							var options = {
								pickSeconds : false
							}, ngModel = $parse(element.find('input').data(
									'ng-model'));
							if (attrs.pickdate !== undefined) {
								options.pickDate = attrs.pickdate === "true";
							}

							if (attrs.language) {
								options.language = attrs.language;
							}
							element.datetimepicker(options);

							element.on('changeDate',
											function(e) {
												scope.$apply(function(scope) {
															// Change binded
															// variable
															if (options.pickDate){
																ngModel.assign(scope,("0" + e.date.getDate()).substr(-2,2) + "."+ ("0" + (e.date.getMonth() + 1)).substr(-2,2)+ "."+ e.date.getFullYear());
															}
																
														});
											});
						}
					};
				}).directive('stepNav', function() {
			var baseUrl = $('body').data('template-url');
			return {
				restrict : 'C',
				scope : {
					menustep : '='
				},
				link : function(scope, element, attrs) {
				}
			};
		});