package de.elementEvents.tema.event

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.servlet.support.RequestContextUtils as RCU

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
	
	def languageLocale() {
		def locale, result
		if (params.locale) {
			 
			def cc = params.locale.split("_")
			if (cc.size() == 1){
				locale = new Locale(cc[0])
				result = locale.getDisplayLanguage()
			}
			else if (cc.size() == 2) {
				locale = new Locale(cc[0],cc[1])
				result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
			}
			 
			 
		 } else {
		 locale = new Locale("de","DE")
		 result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
		 }
		
		render result
	}
	
	def returnEventLanguage() {
		def eventLanguage = new EventLanguage()
		def locale, result
		
		if (params.locale) {
			
		   def cc = params.locale.split("_")
		   if (cc.size() == 1){
			   locale = new Locale(cc[0])
			   result = locale.getDisplayLanguage()
		   }
		   else if (cc.size() == 2) {
			   locale = new Locale(cc[0],cc[1])
			   result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
		   }
			
			
		} else {
		locale = new Locale("de","DE")
		result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
		}
		
		eventLanguage.language = locale
		eventLanguage.languageName = result
		
		render eventLanguage as JSON
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
