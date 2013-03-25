package de.elementEvents.tema.meeting

import org.springframework.dao.DataIntegrityViolationException

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class MeetingController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Meeting.count())
		render Meeting.list(params) as JSON
    }

    def save() {
        def meetingInstance = new Meeting(request.JSON)
        def responseJson = [:]
        if (meetingInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = meetingInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'meeting.label', default: 'Meeting'), meetingInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = meetingInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def meetingInstance = Meeting.get(params.id)
        if (meetingInstance) {
			render meetingInstance as JSON
        } else {
			notFound params.id
		}
    }
	
	def create() {
		
		def event = Event.get(params.eventId)
		
		def defaultsValues = [i18n:[],event: event]
		
		if (event) {
			def availableLanuages = event.eventLanguage
			
			Meeting meeting = new Meeting();
			meeting.event = event
			
			for ( is in availableLanuages) {
				def meetingi18n = new Meeting_i18n()
				meetingi18n.i18n = is
				meetingi18n.meeting = meeting;
				defaultsValues.i18n.add(meetingi18n)
			}
		}
		render defaultsValues as JSON
	}

    def update() {
        def meetingInstance = Meeting.get(params.id)
        if (!meetingInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (meetingInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'meeting.label', default: 'Meeting')],
						default: 'Another user has updated this Meeting while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        meetingInstance.properties = request.JSON

        if (meetingInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = meetingInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'meeting.label', default: 'Meeting'), meetingInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = meetingInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def meetingInstance = Meeting.get(params.id)
        if (!meetingInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            meetingInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'meeting.label', default: 'Meeting'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'meeting.label', default: 'Meeting'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'meeting.label', default: 'Meeting'), params.id])]
        render responseJson as JSON
    }
}
