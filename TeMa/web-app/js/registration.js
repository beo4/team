function RegistrationCtrl($scope, $location, $rootScope, Grails, Flash) {
	$scope.message = Flash.getMessage();
	
	$scope.loadParticipant = function() {
		Grails.get({loginToken: $scope.loginToken}, function(item) {
			$rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
			$rootScope.event = item.participant.event;
			$rootScope.subscription = item.subscription;
			$rootScope.event_i18n = item.event_i18n;
			
			if(!item.participant.language)
					$location.path('/chooseLanguage');
			else
				$location.path('/welcome');
	        
	    }, errorHandler.curry($scope, $location, Flash));
	};
	
	$scope.gotoStep2 = function(){
		$location.path('/personelData');
	}
	
    $scope.updateParticipant = function(item) {
        item.$update(function(response) {
            Flash.success(response.message);
            $rootScope.participant = new Grails;
			angular.extend($rootScope.participant, item.participant);
            if ($rootScope.event.meetingChoosable || !$rootScope.participant.meeting) {
            	$location.path('/chooseMeeting');
            } else {
            	$location.path('/chooseOptions');
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

scaffoldingModule.directive('i18ntabs', function(){
	var baseUrl = $('body').data('template-url');
    return {
        restrict: 'A',
        scope: {i18n: '=',
        	templateurl: '='},
        // The linking function will add behavior to the template
        link: function(scope, element, attrs) {
        },
        controller: function($scope, $element, $dialog) {
        	
            $scope.select = function(pane) {
                angular.forEach($scope.i18n, function(pane) {
                    pane.selected = false;
                });
                pane.selected = true;
            }
            
            $scope.deleteLanguage = function(pane) {
                angular.forEach($scope.i18n, function(pane) {
                    pane.selected = false;
                });
                $scope.panes.splice($scope.i18n.indexOf(pane),1);
            }

            this.addPane = function(pane) {
                $scope.select(pane);
            }
            
            $scope.openMessageBox = function(pane){
        	    var baseUrl = $('body').data('template-url'),
        	    	title = 'Delete ' + pane.languageName,
        	        msg = 'Delete language and all data',
        	    	btns = [{result:'cancel', label: 'Cancel'}, {result:'ok', label: 'OK', cssClass: 'btn-primary'}],
        	    	model ={title: title,message: msg, buttons: btns},
        	    	opts = {
        	              backdrop: true,
        	              keyboard: true,
        	              backdropClick: true,
        	              templateUrl:  baseUrl + '/template/dialog/message.html',
        	              controller: 'MessageBoxController',
        	              resolve: {model: model}
        	            };
        	    
        	    var msgbox = $dialog.dialog(opts);
                msgbox.open().then(function(result){
                   if(result === 'yes') {$scope.deleteLanguage(pane);}
                });
        	  };
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