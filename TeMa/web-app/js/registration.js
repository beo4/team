function RegistrationCtrl($scope, $location, $rootScope, Grails, Flash) {
	$scope.message = Flash.getMessage();
	$scope.isEmailChanged = false;
	$rootScope.steps = [
			{active:true, title: 'Home'},
			{active:false, title: 'Anmeldung'},
			{active:false, title: 'Teilnehmerdaten'},
			{active:false, title: 'Registrierung'},
			{active:false, title: 'Bestätigung'},
	];
	
	$rootScope.travelDetail = new Grails;
	
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
				                                {value:"Ja"},
				                                {value:"Nein"}
				                                ]
				
				if(!item.participant.language)
						$location.path('/chooseLanguage');
				else if ($rootScope.subscription) {
					$location.path('/subscriptionDetails');
					$rootScope.setStep('Anmeldung');
				} else {
					$location.path('/chooseMeeting');
					$rootScope.setStep('Anmeldung');
				}
		        
		    }, errorHandler.curry($scope, $location, Flash));
	}
	
	$rootScope.travelOptions = {
	                            PKW: {name: 'PKW', active:false},
	                            Bahn: {name: 'Bahn', active:false},
	                            Flugzeug: {name: 'Flugzeug', active:false},
	}	
	$rootScope.setStep = function(activatestep) {
		for (step in $rootScope.steps) {
			if ($rootScope.steps[step].title == activatestep) {
				$rootScope.steps[step].active = true;
			} else {
				$rootScope.steps[step].active = false;
			}
		}
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
			                                ]
			
			if(!item.participant.language)
					$location.path('/chooseLanguage');
			else if ($rootScope.subscription) {
				$location.path('/subscriptionDetails');
				$rootScope.setStep('Anmeldung');
			} else {
				$location.path('/chooseMeeting');
				$rootScope.setStep('Anmeldung');
			}
	        
	    }, errorHandler.curry($scope, $location, Flash));
	};
	
	$scope.gotoStep2 = function(){
		if ($rootScope.subscription) {
			$location.path('/subscriptionDetails');
			$rootScope.setStep('Anmeldung');
		} else {
			$location.path('/chooseMeeting');
			$rootScope.setStep('Anmeldung');
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
    directive('i18npane', function() {
    	var baseUrl = $('body').data('template-url');
        return {
          require: '^i18ntabs',
          restrict: 'A',
          link: function(scope, element, attrs, tabsCtrl) {
          }
        };
      }).directive('customUploader', function(){
    	    return {
    	        restrict: 'E',
    	        scope: {},
    	        template: '<div class="custom-uploader-container">Drop Files Here<input type="file" class="custom-uploader-input"/><button ng-click="upload()" ng-disabled="notReady">Upload</button></div>',
    	        controller: function($scope, $customUploaderService) {
    	           $scope.notReady = true;
    	           $scope.upload = function() {
    	              //scope.files is set in the linking function below.
    	        	  Grails.uploadUserFile($scope.files)
    	           };
    	           $customUploaderService.onUploadProgress = function(progress) {
    	              //do something here.
    	           };
    	           $customUploaderService.onComplete = function(result) {
    	              // do something here.
    	           };
    	        },
    	        link: function(scope, elem, attr, ctrl) {
    	           fileInput = elem.find('input[type="file"]');
    	           fileInput.bind('change', function(e) {               
    	                scope.notReady = e.target.files.length > 0;
    	                scope.files = [];
    	                for(var i = 0; i < e.target.files.length; i++) {
    	                    //set files in the scope
    	                    var file = e.target.files[i];
    	                    scope.files.push({ name: file.name, type: file.type, size: file.size });
    	                }
    	           });
    	        }
    	    }
    	 });