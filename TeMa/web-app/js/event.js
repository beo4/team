function EventCtrl($scope, $location, Grails, Flash) {
	$scope.item.eventLanguage = [];
	$scope.item.i18n = [];
	
	$scope.addLanguage = function(language) {
		var locale = $scope.getI18n(language);
	}
	
	$scope.defaults = Grails.create({}, function(response) {
        $scope.defaults = response;
    }, errorHandler.curry($scope, $location, Flash));
	
	$scope.getI18n = function(locale) {
		Grails.newI18n({locale: locale}, function(response) {
			$scope.item.eventLanguage.push(response.eventLanguage);
			response.i18n.eventLanguage = response.eventLanguage
			$scope.item.i18n.push(response.i18n);
	    }, errorHandler.curry($scope, $location, Flash));
	}
	
    
}

function MeetingCtrl($scope, $routeParams, $location, Grails, Flash) {

	$scope.item.event;
	$scope.item.i18n = [];
	
	$scope.defaults = Grails.create({eventId: + $routeParams.id}, function(response) {
		$scope.item.event = response.event;
		$scope.item.i18n = response.i18n;
    }, errorHandler.curry($scope, $location, Flash));
}


function UserCtrl($scope, $routeParams, $location, Grails, Flash) {

	$scope.item.event;
	$scope.item.i18n = [];
	
	if ($routeParams.meetingId){
		$scope.defaults = Grails.create({'eventId': + $routeParams.id, 'meetingId': + $routeParams.meetingId}, function(response) {
			$scope.item.event = response.event;
			$scope.item.meeting = response.meeting;
			$scope.item.salutations = response.salutations;
			$scope.i18n = response.i18n;
	    }, errorHandler.curry($scope, $location, Flash));
	} else {
		$scope.defaults = Grails.create({'eventId': + $routeParams.id}, function(response) {
			$scope.item.event = response.event;
			$scope.i18n = response.i18n;
			$scope.item.salutations = response.salutations;
	    }, errorHandler.curry($scope, $location, Flash));
	}
}

function UserFileCtrl($scope, $routeParams, $location, Grails, Flash) {

	if ($routeParams.meetingId){
		$scope.defaults = Grails.create({'eventId': + $routeParams.id, 'meetingId': + $routeParams.meetingId}, function(response) {
			$scope.item.event = response.event;
			$scope.item.meeting = response.meeting;
			$scope.item.salutations = response.salutations;
			$scope.i18n = response.i18n;
	    }, errorHandler.curry($scope, $location, Flash));
	} else {
		$scope.defaults = Grails.create({'eventId': + $routeParams.id}, function(response) {
			$scope.item.event = response.event;
			$scope.i18n = response.i18n;
			$scope.item.salutations = response.salutations;
	    }, errorHandler.curry($scope, $location, Flash));
	}
	
	$scope.upload = function(item) {
        item.$upload(function(response) {
            Flash.success(response.message);
            $location.path('/show/' + response.id);
        }, errorHandler.curry($scope, $location, Flash));
    };
}


function ResourceCtrl($scope, $routeParams, $location, Grails, Flash) {

	$scope.item.event;
	$scope.item.i18n = [];
	
	$scope.defaults = Grails.create({eventId: + $routeParams.id}, function(response) {
		$scope.item.event = response.event;
		$scope.item.i18n = response.i18n;
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