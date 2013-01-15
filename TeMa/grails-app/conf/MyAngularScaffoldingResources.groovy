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
}