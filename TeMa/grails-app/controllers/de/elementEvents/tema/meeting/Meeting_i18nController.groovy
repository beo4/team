package de.elementEvents.tema.meeting

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class Meeting_i18nController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Meeting_i18n.count())
		render Meeting_i18n.list(params) as JSON
    }

    def save() {
        def meeting_i18nInstance = new Meeting_i18n(request.JSON)
        def responseJson = [:]
        if (meeting_i18nInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = meeting_i18nInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n'), meeting_i18nInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = meeting_i18nInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def meeting_i18nInstance = Meeting_i18n.get(params.id)
        if (meeting_i18nInstance) {
			render meeting_i18nInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def meeting_i18nInstance = Meeting_i18n.get(params.id)
        if (!meeting_i18nInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (meeting_i18nInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n')],
						default: 'Another user has updated this Meeting_i18n while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        meeting_i18nInstance.properties = request.JSON

        if (meeting_i18nInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = meeting_i18nInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n'), meeting_i18nInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = meeting_i18nInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def meeting_i18nInstance = Meeting_i18n.get(params.id)
        if (!meeting_i18nInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            meeting_i18nInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n'), params.id])]
        render responseJson as JSON
    }
}
