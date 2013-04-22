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
		getMeetings: {method: 'GET', params: {action: 'get'}},
		update: {method: 'POST', params: {action: 'update'}},
		save: {method: 'POST', params: {action: 'save'}},
		saveTravelOptions: {method: 'POST', params: {action: 'saveTravelOptions'}},
		account: {method: 'GET', params: {action: 'loadParticipantData'}},
		delete: {method: 'POST', params: {action: 'delete'}}
	});
});