package de.elementEvents.tema.resource

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class ResourceOptionController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', ResourceOption.count())
		render ResourceOption.list(params) as JSON
    }

    def save() {
        def resourceOptionInstance = new ResourceOption(request.JSON)
        def responseJson = [:]
        if (resourceOptionInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = resourceOptionInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'resourceOption.label', default: 'ResourceOption'), resourceOptionInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = resourceOptionInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def resourceOptionInstance = ResourceOption.get(params.id)
        if (resourceOptionInstance) {
			render resourceOptionInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def resourceOptionInstance = ResourceOption.get(params.id)
        if (!resourceOptionInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (resourceOptionInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'resourceOption.label', default: 'ResourceOption')],
						default: 'Another user has updated this ResourceOption while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        resourceOptionInstance.properties = request.JSON

        if (resourceOptionInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = resourceOptionInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'resourceOption.label', default: 'ResourceOption'), resourceOptionInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = resourceOptionInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def resourceOptionInstance = ResourceOption.get(params.id)
        if (!resourceOptionInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            resourceOptionInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'resourceOption.label', default: 'ResourceOption'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'resourceOption.label', default: 'ResourceOption'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'resourceOption.label', default: 'ResourceOption'), params.id])]
        render responseJson as JSON
    }
}
