package de.elementEvents.tema.event

import org.springframework.dao.DataIntegrityViolationException

class EventLanguageController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventLanguageInstanceList: EventLanguage.list(params), eventLanguageInstanceTotal: EventLanguage.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[eventLanguageInstance: new EventLanguage(params)]
			break
		case 'POST':
	        def eventLanguageInstance = new EventLanguage(params)
	        if (!eventLanguageInstance.save(flush: true)) {
	            render view: 'create', model: [eventLanguageInstance: eventLanguageInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), eventLanguageInstance.id])
	        redirect action: 'show', id: eventLanguageInstance.id
			break
		}
    }

    def show() {
        def eventLanguageInstance = EventLanguage.get(params.id)
        if (!eventLanguageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
            redirect action: 'list'
            return
        }

        [eventLanguageInstance: eventLanguageInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def eventLanguageInstance = EventLanguage.get(params.id)
	        if (!eventLanguageInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [eventLanguageInstance: eventLanguageInstance]
			break
		case 'POST':
	        def eventLanguageInstance = EventLanguage.get(params.id)
	        if (!eventLanguageInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (eventLanguageInstance.version > version) {
	                eventLanguageInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'eventLanguage.label', default: 'EventLanguage')] as Object[],
	                          "Another user has updated this EventLanguage while you were editing")
	                render view: 'edit', model: [eventLanguageInstance: eventLanguageInstance]
	                return
	            }
	        }

	        eventLanguageInstance.properties = params

	        if (!eventLanguageInstance.save(flush: true)) {
	            render view: 'edit', model: [eventLanguageInstance: eventLanguageInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), eventLanguageInstance.id])
	        redirect action: 'show', id: eventLanguageInstance.id
			break
		}
    }

    def delete() {
        def eventLanguageInstance = EventLanguage.get(params.id)
        if (!eventLanguageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
            redirect action: 'list'
            return
        }

        try {
            eventLanguageInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'eventLanguage.label', default: 'EventLanguage'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
