package de.elementEvents.tema.subscription

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class SubscriptionController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Subscription.count())
		render Subscription.list(params) as JSON
    }

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
        def subscriptionInstance = Subscription.get(params.id)
        if (subscriptionInstance) {
			render subscriptionInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def subscriptionInstance = Subscription.get(params.id)
        if (!subscriptionInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (subscriptionInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'subscription.label', default: 'Subscription')],
						default: 'Another user has updated this Subscription while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        subscriptionInstance.properties = request.JSON

        if (subscriptionInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = subscriptionInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = subscriptionInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def subscriptionInstance = Subscription.get(params.id)
        if (!subscriptionInstance) {
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
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), params.id])]
        render responseJson as JSON
    }
}
