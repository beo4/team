package de.elementEvents.tema.resource

import org.springframework.dao.DataIntegrityViolationException

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import static javax.servlet.http.HttpServletResponse.*

class ResourceController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Resource.count())
		render Resource.list(params) as JSON
    }

    def save() {
        def resourceInstance = new Resource()
		def jsonObject = request.JSON
		
		for ( is in jsonObject.i18n) {
			def resourcei18n = new Resource_i18n()
			def eventLanguage = EventLanguage.get(is.i18n.id)
			resourcei18n.i18n = eventLanguage
			resourcei18n.name = is.name
			resourcei18n.setDescription( is.description)
			resourceInstance.addToI18n(resourcei18n)
			resourcei18n.validate()
		}
		
		int eventId = jsonObject.event.id
		def eventInstance = Event.get(eventId)
		resourceInstance.event = eventInstance
		resourceInstance.validate()
		
        def responseJson = [:]
        if (resourceInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = resourceInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'resource.label', default: 'Resource'), resourceInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = resourceInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }
	
	def create() {
		
		def event = Event.get(params.eventId)
		
		def defaultsValues = [i18n:[],event: event]
		
		if (event) {
			def availableLanuages = event.eventLanguage
			
			Resource resource = new Resource();
			resource.event = event
			
			for ( is in availableLanuages) {
				def resourcei18n = new Resource_i18n()
				resourcei18n.i18n = is
				defaultsValues.i18n.add(resourcei18n)
			}
		}
		JSON.use("deep")
		JodaConverters.registerJsonAndXmlMarshallers()
		render defaultsValues as JSON
	}

    def get() {
        def resourceInstance = Resource.get(params.id)
        if (resourceInstance) {
			render resourceInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def resourceInstance = Resource.get(params.id)
        if (!resourceInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (resourceInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'resource.label', default: 'Resource')],
						default: 'Another user has updated this Resource while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        resourceInstance.properties = request.JSON

        if (resourceInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = resourceInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'resource.label', default: 'Resource'), resourceInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = resourceInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def resourceInstance = Resource.get(params.id)
        if (!resourceInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            resourceInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'resource.label', default: 'Resource'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'resource.label', default: 'Resource'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'resource.label', default: 'Resource'), params.id])]
        render responseJson as JSON
    }
}
