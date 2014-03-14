/**
 * This module defines the resource mappings required by Angular JS to map to a
 * standard Grails CRUD URL scheme that uses `"/$controller/$action?/$id?"`.
 */
angular.module('grailsService', ['ngResource']).factory('Grails', function($resource) {
	var baseUrl = $('body').data('base-url');

	return $resource(baseUrl + ':action/:id', {id: '@id'}, {
		get: {method: 'GET', params: {action: 'get'}},
		getSurvey: {method: 'GET', params: {action: 'getSurvey'}},
		getMeetings: {method: 'GET', params: {action: 'get'}},
		update: {method: 'POST', params: {action: 'update'}},
		updateSurvey: {method: 'POST', params: {action: 'updateSurvey'}},
		confirm: {method: 'POST', params: {action: 'confirm'}},
		save: {method: 'POST', params: {action: 'save'}},
		saveTravelOptions: {method: 'POST', params: {action: 'saveTravelOptions'}},
		updateMarketplaceOptions: {method: 'POST', params: {action: 'updateMarketplaceOptions'}},
		account: {method: 'GET', params: {action: 'loadParticipantData'}},
		createRepresentativ: {method: 'POST', params: {action: 'createRepresentativ'}}
	});
});