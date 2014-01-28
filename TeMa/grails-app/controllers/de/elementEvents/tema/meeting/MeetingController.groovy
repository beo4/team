package de.elementEvents.tema.meeting

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.util.HtmlUtils;

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import de.elementEvents.tema.subscription.Subscription;
import de.elementEvents.tema.user.User;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import static javax.servlet.http.HttpServletResponse.*

class MeetingController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def exportService
    def grailsApplication  //inject GrailsApplication

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Meeting.count())
		JodaConverters.registerJsonAndXmlMarshallers()
		render Meeting.list(params) as JSON
    }
    
    def exportList() {
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        
        
        List fields = ["user.companyadd", "user.salutation", "user.postalSalutation", "user.title","user.firstname", "user.middlename","user.lastname", "user.email","user.loginToken", "user.street","user.plz", "user.city","user.mobile","user.company", "user.position","user.companystreet","user.companyplz","user.companycity",
                       "meeting.start","meeting.getDeadline","meeting.i18n.first().description", "user.account","user.confirmed"]
        Map labels = ["user.companyadd":"Betriebsnummer","user.salutation" :"Anrede", "user.postalSalutation": "Anrede Brief", "user.title":"Titel","user.firstname":"Vorname", "user.email":"Email","user.middlename":"Zusatzname","user.lastname":"Nachname", "user.street":"Strasse","user.plz":"PLZ", "user.city":"Ort","user.mobile":"Telefon","user.company":"Firma", "user.position":"Position","companystreet":"Firmen Adresse","companyplz":"Fimen PLZ","user.companycity":"Firmen Ort","user.account":"Teilnahme","user.confirmed":"Datenschutz aktzeptiert",
                      ,"meeting.start":"VA Datum","meeting.getDeadline":"Deadline","meeting.getDefaultDescription":"VA Ort"]
        
        
        // Formatter closure
        def dateformat = { domain, value ->
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
            return fmt.print(value)
        }
        
        def decodeHTML = {domain, value ->
            return HtmlUtils.htmlUnescape(value[0]).replaceAll("<br/>", " ")
        }

        Map formatters = ["meeting.start" : dateformat, "meeting.getDeadline" : dateformat, "meeting.i18n.first().description" : decodeHTML]
        
        if (params.meetingId){
            def meetingInstance =  Meeting.get(params.meetingId)
            
            def firstLocalisation = meetingInstance.i18n.asList()[0]
            def filename = firstLocalisation.title + "_" + firstLocalisation.subtitle 
            Map parameters = [title: "${firstLocalisation.title + "_" + firstLocalisation.subtitle}"]
            response.setHeader("Content-disposition", "attachment; filename=${filename}.${params.extension}")
            
            def subscriptionList = Subscription.findAllByMeeting(meetingInstance)
            
            exportService.export(params.format, response.outputStream,subscriptionList, fields, labels,formatters,parameters)
        }
       

    }

    def save() {
		
		def jsonObject = request.JSON
		def meetingInstance = new Meeting(request.JSON)
		def i18ns = []
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
		meetingInstance.start.parse(jsonObject.start, fmt)
		meetingInstance.end.parse(jsonObject.end, fmt)
		for ( is in jsonObject.i18n) {
			def meetingi18n = new Meeting_i18n(is)
			def eventLanguage = EventLanguage.get(is.i18n.id)
			meetingi18n.i18n = eventLanguage
			meetingInstance.addToI18n(meetingi18n)
		}
		
		int eventId = jsonObject.event.id
		def eventInstance = Event.get(eventId)
		meetingInstance.event = eventInstance
	
        def responseJson = [:]
		
		meetingInstance.validate()
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
				defaultsValues.i18n.add(meetingi18n)
			}
		}
		JSON.use("deep")
		JodaConverters.registerJsonAndXmlMarshallers()
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
