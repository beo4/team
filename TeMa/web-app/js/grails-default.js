/**
 * This module defines the resource mappings required by Angular JS to map to a
 * standard Grails CRUD URL scheme that uses `"/$controller/$action?/$id?"`.
 */
angular.module('grailsService', ['ngResource']).factory('Grails', function($resource) {
	var baseUrl = $('body').data('base-url');

	return $resource(baseUrl + ':action/:id', {id: '@id'}, {
		list: {method: 'GET', params: {action: 'list'}, isArray: true},
		create: {method: 'GET', params: {action: 'create'}},
		get: {method: 'GET', params: {action: 'get'}},
		save: {method: 'POST', params: {action: 'save'}},
		update: {method: 'POST', params: {action: 'update'}},
		newI18n:{method: 'GET', params: {action: 'addI18n'}},
		delete: {method: 'POST', params: {action: 'delete'}}
	});
});