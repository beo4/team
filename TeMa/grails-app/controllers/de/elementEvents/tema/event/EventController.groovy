package de.elementEvents.tema.event

import org.springframework.dao.DataIntegrityViolationException

class EventController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[eventInstance: new Event(params)]
			break
		case 'POST':
	        def eventInstance = new Event(params)
			
			if (params.descriptionRT) {
				eventInstance.description = params.descriptionRT
			}
				
			
	        if (!eventInstance.save(flush: true)) {
	            render view: 'create', model: [eventInstance: eventInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
	        redirect action: 'show', id: eventInstance.id
			break
		}
    }

    def show() {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
            redirect action: 'list'
            return
        }

        [eventInstance: eventInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def eventInstance = Event.get(params.id)
	        if (!eventInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [eventInstance: eventInstance]
			break
		case 'POST':
	        def eventInstance = Event.get(params.id)
	        if (!eventInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (eventInstance.version > version) {
	                eventInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'event.label', default: 'Event')] as Object[],
	                          "Another user has updated this Event while you were editing")
	                render view: 'edit', model: [eventInstance: eventInstance]
	                return
	            }
	        }

	        eventInstance.properties = params

	        if (!eventInstance.save(flush: true)) {
	            render view: 'edit', model: [eventInstance: eventInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
	        redirect action: 'show', id: eventInstance.id
			break
		}
    }

    def delete() {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
            redirect action: 'list'
            return
        }

        try {
            eventInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
