package de.elementEvents.tema.event

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class EventController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Event.count())
		render Event.list(params) as JSON
    }

    def save() {
        def eventInstance = new Event(request.JSON)
        def responseJson = [:]
        if (eventInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = eventInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = eventInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
			render eventInstance as JSON
        } else {
			notFound params.id
		}
    }
	
	def create() {
		def availableLanuages = Locale.getAvailableLocales()
		
		if (availableLanuages) {
			def defaultsValues = [languages:[]]
			for (language in availableLanuages) {
				defaultsValues.languages.add([country:language.country, displayName: language.displayName, displayCountry: language.displayCountry, language:language.language, code:language.language + '_' + language.country])
			}
			//def defaultsValues=[test:availableLanuages]
			
			render defaultsValues as JSON
		} else {
			notFound "Locales"
		}
	}

	def addI18n() {
		def locale, result, eventLanguageInstance =  new EventLanguage(params), event_i18n = new Event_i18n()
		if (params.locale) {
				def cc = params.locale.split("_")
				if (cc.size() == 1){
					locale = new Locale(cc[0])
					result = locale.getDisplayLanguage()
				}
				else if (cc.size() == 2) {
					locale = new Locale(cc[1],cc[0])
					result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
				}
				 
				 
			 } else {
			 locale = new Locale("de","DE")
			 }
	
			 eventLanguageInstance.languageName = locale.getDisplayLanguage();
			 eventLanguageInstance.language = locale
			 
			 event_i18n.i18n = eventLanguageInstance
			 
			 render event_i18n as JSON
	}
	
    def update() {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (eventInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'event.label', default: 'Event')],
						default: 'Another user has updated this Event while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        eventInstance.properties = request.JSON

        if (eventInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = eventInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = eventInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            eventInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])]
        render responseJson as JSON
    }
}
