package de.elementEvents.tema.registration

import java.awt.GraphicsConfiguration.DefaultBufferCapabilities;

import org.springframework.dao.DataIntegrityViolationException

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.Event_i18n;
import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.subscription.Subscription
import de.elementEvents.tema.user.User;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import grails.plugins.springsecurity.SpringSecurityService;
import static javax.servlet.http.HttpServletResponse.*

class RegistrationController {
	static public main = true

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService

    def index() { }

    def save() {
        def subscriptionInstance = new Subscription(request.JSON)
        def responseJson = [:]
        if (subscriptionInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = subscriptionInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = subscriptionInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def userInstance = User.findByLoginToken(params.loginToken)
        if (userInstance) {
			//login User
			def jsonResponse = [:]
			jsonResponse.participant = userInstance
			def subscriptionInstance = Subscription.findByUser(userInstance);
			def event = Event.get(userInstance.event.id)
			def language = userInstance.language
			
			// if user has no language choosen and only one is available set it
			if(language == null && event.eventLanguage.size()==1){
				userInstance.language = event.eventLanguage.first()
				userInstance.save(flush: true)
			}
			
			if (userInstance.language != null) {
				jsonResponse.event_i18n = Event_i18n.findByEventAndEventLanguage(event, userInstance.language)
			}
			
			if (!subscriptionInstance){
				subscriptionInstance = new Subscription();
				subscriptionInstance.user = userInstance;
			} 
			
			jsonResponse.meeting = subscriptionInstance.meeting
			
			
			jsonResponse.subscription = subscriptionInstance
			
			//springSecurityService.reauthenticate userInstance.username
			JodaConverters.registerJsonAndXmlMarshallers()
			render jsonResponse as JSON
        } else {
			notFound params.loginToken
		}
    }
	
	def getMeetings() {
		def eventInstance = Event.get(params.event)
		def meetings = eventInstance.meetings
		
		
		
		def responseJson = [:]

		responseJson.meetings = meetings
		
		render responseJson as JSON
		
	}

    def update() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (userInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'user.label', default: 'User')],
						default: 'Another user has updated this Subscription while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        userInstance.properties = request.JSON

        if (userInstance.save(flush: false)) {
            response.status = SC_OK
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			responseJson.participant = userInstance
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            subscriptionInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Participant'), params.loginToken])]
        render responseJson as JSON
    }
}
