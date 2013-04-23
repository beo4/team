function RegistrationCtrl($scope, $location, $rootScope, Grails, Flash) {
	$scope.message = Flash.getMessage();
	$scope.isEmailChanged = false;
	$scope.travelDetail = new Grails;
	
	$scope.doTheBack = function() {
		  if ($location.$$path=== "/chooseOptions") {
			  $location.path('/personelData');
		  } else if ($location.$$path=== "/personelData") {
			  $location.path('/subscriptionDetails');
		  } else if ($location.$$path=== "/end") {
			  $location.path('/chooseOptions');
		  }
		  
	};
	
	$rootScope.getLoggedInUser = function(){
		//try to load a logged in user
		return Grails.get({}, function(item) {
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
				                                {value:"Nein"},
				                                {value:"Ja"}
				                          ]
				
				if(!item.participant.language)
						$location.path('/chooseLanguage');
				else if ($rootScope.subscription) {
					$location.path('/subscriptionDetails');
				} else {
					$location.path('/chooseMeeting');
				}
		        
		    }, errorHandler.curry($scope, $location, Flash));
	}
	
	$rootScope.travelOptions = {
	                            PKW: {name: 'PKW', active:false},
	                            Bahn: {name: 'Bahn', active:false},
	                            Flugzeug: {name: 'Flugzeug', active:false},
	}	
	
	$scope.loadParticipant = function() {
		Grails.get({loginToken: $scope.loginToken}, function(item) {
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
			                                ];
			$rootScope.allreadySubscribed = item.participant.confirmed;
			
			if(!item.participant.language)
					$location.path('/chooseLanguage');
			else if ($rootScope.subscription) {
				$location.path('/subscriptionDetails');
			} else {
				$location.path('/chooseMeeting');
			}
	        
	    }, errorHandler.curry($scope, $location, Flash));
	};
	
	$scope.gotoStep2 = function(){
		if ($rootScope.subscription) {
			$location.path('/subscriptionDetails');
		} else {
			$location.path('/chooseMeeting');
		}
	}
	
    $scope.updateParticipant = function(item) {
    	if ($scope.isEmailChanged) {
    		
    	}
    	
        item.$update(function(response) {
            Flash.success(response.message);
            $rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
			$location.path('/chooseOptions');
            
        }, errorHandler.curry($scope, $location, Flash));
    };
    
    $scope.confirmParticipant = function(item) {
    	
        item.$update(function(response) {
            Flash.success(response.message);
            $rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
			$scope.confirmedee = true;
            
        }, errorHandler.curry($scope, $location, Flash));
    };
    
    $scope.updateOptions = function(item) {

        item.$saveTravelOptions(function(response) {
            Flash.success(response.message);
            $rootScope.participant = new Grails;
			$location.path('/end');
            
        }, errorHandler.curry($scope, $location, Flash));
        
        $location.path('/end');
    };
    
    $scope.emailChanged = function() {
    	$scope.isEmailChanged = true;
    }
    
    $scope.updateAccount= function(item) {
    	item.confirmed=false;
        item.$update(function(response) {
            Flash.success(response.message);
            $rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
            if ($rootScope.participant.account) {
            	$location.path('/personelData');
            } else {
            	$location.path('/end');
            }
            
        }, errorHandler.curry($scope, $location, Flash));
    };
}

function MeetingCtrl($scope, $location, $rootScope, Grails, Flash) {
	$scope.message = Flash.getMessage();
	
	$scope.participant = $rootScope.participant;
	$scope.event_i18n = $rootScope.event_i18n;
	$scope.event = $rootScope.event;
	
	Grails.getMeetings({participant: $scope.participant.id, event: $scope.event.id}, function(item) {
        $scope.meetings = item;
    }, errorHandler.curry($scope, $location, Flash));
	
}

scaffoldingModule.directive('bsdtpicker', function(){
	var baseUrl = $('body').data('template-url');
    return {
        restrict: 'C',
        // The linking function will add behavior to the template
        link: function(scope, element, attrs) {
        	var options = {}
        	if (attrs.pickdate!==undefined){
        		options.pickDate = attrs.pickdate === "true";
        	}
        	
        	if (attrs.language){
        		options.language = attrs.language;
        	}
        	element.datetimepicker(options);
        }
      }
    }).
    directive('stepNav', function() {
    	var baseUrl = $('body').data('template-url');
        return {
          restrict: 'C',
          scope: {
              menustep: '='
            },
          link: function(scope, element, attrs) {
          }
        };
      })