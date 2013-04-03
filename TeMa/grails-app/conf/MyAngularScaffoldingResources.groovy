import grails.util.GrailsUtil

def fileVersion = GrailsUtil.isDevelopmentEnv() ? '1.0.0' : '1.0.0.min'

modules = {
    angular {
        resource id: 'js', url: [plugin: 'angular-scaffolding', dir: 'js/angular', file: "angular-${fileVersion}.js"], nominify: true
    }
    'angular-resource' {
		dependsOn 'angular'
        resource id: 'js', url: [plugin: 'angular-scaffolding', dir: 'js/angular', file: "angular-resource-${fileVersion}.js"], nominify: true
    }

    'my-angular-scaffolding' {
        dependsOn 'jquery', 'angular-resource'
        resource id: 'js', url: [dir: 'js', file: 'grails-default.js']
        resource id: 'js', url: [dir: 'js', file: 'scaffolding.js']
        resource id: 'css', url: [plugin: 'angular-scaffolding', dir: 'css', file: 'scaffolding.css']
    }
	
	'my-angular-ui-scaffolding' {
		dependsOn 'my-angular-scaffolding'
		resource id: 'js', url: [dir: 'js', file: 'angular-ui.js']
		resource id: 'js', url: [dir: 'js', file: 'angular-ui-ieshiv.js']
		resource id: 'js', url: [dir: 'js', file: 'ui-bootstrap-tpls-0.1.0-SNAPSHOT.js']
		resource id: 'css', url: [dir: 'css', file: 'angular-ui.css']
		resource id: 'js', url: [dir: 'js', file: 'bootstrap-datetimepicker.min.js']
		resource id: 'css', url: [dir: 'css', file: 'bootstrap-datetimepicker.min.css']
	}
	
	'event-angular-scaffolding' {
        dependsOn 'my-angular-ui-scaffolding'
		resource id: 'js', url: [dir: 'js', file: 'event.js']
    }
}