package de.elementEvents.tema.user

import org.springframework.dao.DataIntegrityViolationException

import de.elementEvents.tema.event.Event;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import static javax.servlet.http.HttpServletResponse.*

class UserController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', User.count())
		render User.list(params) as JSON
    }

    def save() {
        def userInstance = new User(request.JSON)
		int eventId = request.JSON.event.id
		
		userInstance.event = Event.get(eventId)
		
        def responseJson = [:]
        if (userInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }
	
	def create() {
		
		def event = Event.get(params.eventId)
		
		def defaultsValues = [event: event]
		
		if (event) {
			User user = new User();
			user.event = event
		}
		JSON.use("deep")
		JodaConverters.registerJsonAndXmlMarshallers()
		render defaultsValues as JSON
	}
	
	def createFromFile() {
		
		def file = request.getFile('file')
		
		def defaultsValues = [event: event]
		
		if (event) {
			User user = new User();
			user.event = event
		}
		JSON.use("deep")
		JodaConverters.registerJsonAndXmlMarshallers()
		render defaultsValues as JSON
	}

    def get() {
        def userInstance = User.get(params.id)
        if (userInstance) {
			render userInstance as JSON
        } else {
			notFound params.id
		}
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
						default: 'Another user has updated this User while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        userInstance.properties = request.JSON

        if (userInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
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
            userInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])]
        render responseJson as JSON
    }
}
