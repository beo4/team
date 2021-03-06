package de.elementEvents.tema.meeting

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.util.HtmlUtils;

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import de.elementEvents.tema.subscription.Subscription;
import de.elementEvents.tema.user.Status;
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
        JSON.use("meetingList"){
		render Meeting.list(params) as JSON
        }
    }
    
    def exportList() {
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        
        
        List fields = ["user.correctCompanyAdd", "user.salutation", "user.postalSalutation", "user.title","user.firstname", "user.middlename","user.lastname", "user.email","user.loginToken", "user.street","user.plz", "user.city","user.mobile","user.company", "user.position","user.companystreet","user.companyplz","user.companycity",
                       "meeting.start","meeting.getDeadline","meeting.i18n.first().description", "user.account","user.confirmed","user.representative", "user.status","user.companyadd","user.companyadd1","user.companyadd2","user.companyadd3"]
        Map labels = ["user.correctCompanyAdd":"Betriebsnummer","user.salutation" :"Anrede", "user.postalSalutation": "Anrede Brief", "user.title":"Titel","user.firstname":"Vorname", "user.email":"Email","user.middlename":"Zusatzname","user.lastname":"Nachname", "user.street":"Strasse","user.plz":"PLZ", "user.city":"Ort","user.mobile":"Telefon","user.company":"Firma", "user.position":"Position","companystreet":"Firmen Adresse","companyplz":"Fimen PLZ","user.companycity":"Firmen Ort","user.account":"Teilnahme","user.confirmed":"Datenschutz aktzeptiert",
                      ,"meeting.start":"VA Datum","meeting.getDeadline":"Deadline","meeting.getDefaultDescription":"VA Ort","user.representative":"Vertretung", "user.status":"Anmeldestatus","user.companyadd":"Zusätzliche Betriebsnummer 1","user.companyadd1":"Zusätzliche Betriebsnummer 2","user.companyadd2":"Zusätzliche Betriebsnummer 3","user.companyadd3":"Zusätzliche Betriebsnummer 4"]
        
        
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
	
	def exportSurveyList() {
		response.contentType = grailsApplication.config.grails.mime.types[params.format]
		

		List fields = [
			"user.correctCompanyAdd",
			"user.salutation",
			"user.title",
			"user.firstname",
			"user.lastname",
			"user.company",
			"user.email",
			"user.loginToken",
			"user.position",
			"user.companystreet",
			"user.companyplz",
			"user.companycity",
			"meeting.i18n.first().title",
			"user.survey.entryValue",
			"user.survey.organisationValue",
			"user.survey.theme",
			"user.survey.themeText",
			"user.survey.marketplace",
			"user.survey.marketplaceTime",
			"user.survey.dailyBusiness",
			"user.survey.overallImpression",
			"user.survey.expectation",
			"user.survey.reentry",
			"user.survey.other",
			"user.survey.aufbauherstellermanagement",
            "user.survey.gewahrleistung",
            "user.survey.euromobil",
			"user.survey.careport",
			"user.survey.grosskundenbetreuung",
			"user.survey.ihvpost",
			"user.survey.inspektion",
			"user.survey.onlinesz",
			"user.survey.originalteile",
			"user.survey.partnerbetreuung",
            "user.survey.taxi",
            "user.survey.servicemarketing",
			"user.survey.skpcommerce",
            "user.survey.servicestandards",
			"user.survey.servicequalifizierungen",
			"user.survey.werkstattsysteme",
			"user.survey.leasing",
            "user.survey.classicparts",
			"user.survey.merchandising"
			
		]
		Map labels = ["user.correctCompanyAdd":"Betriebsnummer",
			"user.salutation" :"Anrede",
			"user.title":"Titel",
			"user.firstname":"Vorname",
			"user.email":"Email",
			"user.lastname":"Nachname",
			"user.street":"Strasse",
			"user.plz":"PLZ", "user.city":"Ort",
			"user.mobile":"Telefon","user.company":"Firma",
			"user.position":"Position","companystreet":"Firmen Adresse",
			"companyplz":"Fimen PLZ","user.companycity":"Firmen Ort",
			"user.account":"Teilnahme",
			"user.confirmed":"Datenschutz aktzeptiert",
			"meeting.start":"VA Datum",
			"meeting.getDeadline":"Deadline",
			"meeting.getDefaultDescription":"VA Ort",
			"user.representative":"Vertretung", 
			"user.status":"Anmeldestatus",
			"user.companyadd":"Zusätzliche Betriebsnummer 1",
			"user.companyadd1":"Zusätzliche Betriebsnummer 2",
			"user.companyadd2":"Zusätzliche Betriebsnummer 3",
			"user.companyadd3":"Zusätzliche Betriebsnummer 4",
			"user.survey.entryValue":message(code: "user.survey.entryValue"),
			"user.survey.organisationValue":message(code: "user.survey.organisationValue"),
			"user.survey.theme":message(code: "user.survey.theme"),
			"user.survey.themeText":message(code: "user.survey.themeText"),
			"user.survey.marketplace":message(code: "user.survey.marketplace"),
			"user.survey.marketplaceText":message(code: "user.survey.marketplaceText"),
			"user.survey.marketplaceTime":message(code: "user.survey.marketplaceTime"),
			"user.survey.dailyBusiness":message(code: "user.survey.dailyBusiness"),
			"user.survey.other":message(code: "user.survey.other"),
			"user.survey.overallImpression":message(code: "user.survey.overallImpression"),
			"user.survey.expectation":message(code: "user.survey.expectation"),
			"user.survey.reentry":message(code: "user.survey.reentry"),
			"user.survey.aufbauherstellermanagement":message(code: "user.survey.01.aufbauherstellermanagement"),
            "user.survey.gewahrleistung":message(code: "user.survey.02.gewahrleistung"),
            "user.survey.euromobil":message(code: "user.survey.03.euromobil"),
            "user.survey.careport":message(code: "user.survey.04.careport"),
            "user.survey.grosskundenbetreuung":message(code: "user.survey.05.grosskundenbetreuung"),
            "user.survey.ihvpost":message(code: "user.survey.06.ihvpost"),
            "user.survey.inspektion":message(code: "user.survey.07.inspektion"),
            "user.survey.onlinesz":message(code: "user.survey.08.onlinesz"),
            "user.survey.originalteile":message(code: "user.survey.09.originalteile"),
            "user.survey.partnerbetreuung":message(code: "user.survey.10.partnerbetreuung"),
            "user.survey.taxi":message(code: "user.survey.11.taxi"),
            "user.survey.servicemarketing":message(code: "user.survey.12.servicemarketing"),
            "user.survey.skpcommerce":message(code: "user.survey.13.skpcommerce"),
            "user.survey.servicestandards":message(code: "user.survey.14.servicestandards"),
            "user.survey.servicequalifizierungen":message(code: "user.survey.15.servicequalifizierungen"),
            "user.survey.werkstattsysteme":message(code: "user.survey.16.werkstattsysteme"),
            "user.survey.leasing":message(code: "user.survey.17.leasing"),
            "user.survey.classicparts":message(code: "user.survey.18.classicparts"),
            "user.survey.merchandising":message(code: "user.survey.19.merchandising")]


		// Formatter closure
		def dateformat = { domain, value ->
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
			return fmt.print(value)
		}
		
		def decodeHTML = {domain, value ->
			return HtmlUtils.htmlUnescape(value[0]).replaceAll("<br/>", " ")
		}
		
		def decodeSurvey = {domain, value ->
			if (value==null) return ""
			
			def result = ""
			switch (value) {
				case 1..10:
					result = "-"
					break
				case 11..20:
					result = "0"
					break
				case 21..30:
					result = "+"
					break
				case 31..40:
					result = "++"
					break
				default:
					result = ""
			}
			return result 
		}

		Map formatters = ["meeting.start" : dateformat, 
						  "meeting.getDeadline" : dateformat, 
						  "meeting.i18n.first().description" : decodeHTML,
						  "user.survey.entryValue" : decodeSurvey,
						"user.survey.organisationValue" : decodeSurvey,
						"user.survey.theme" : decodeSurvey,
						"user.survey.marketplace" : decodeSurvey,
						"user.survey.marketplaceTime" : decodeSurvey,
						"user.survey.dailyBusiness" : decodeSurvey,
						"user.survey.overallImpression" : decodeSurvey,
						"user.survey.expectation" : decodeSurvey,
						"user.survey.reentry" : decodeSurvey
						]
		
		if (params.meetingId){
			def meetingInstance =  Meeting.get(params.meetingId)
			
			
			def subscriptionList = Subscription.findAll("\
										from Subscription as s \
											where s.meeting = :meeting and s.user.account = 1 and s.user.state = :confirmed\
										", [meeting: meetingInstance, confirmed: Status.CONFIRMED])
			
			def firstLocalisation = meetingInstance.i18n.asList()[0]
			def filename = "Fragebogen_" + firstLocalisation.title + "_" + firstLocalisation.subtitle
			Map parameters = [title: "${meetingInstance.id}_Fragebogen_${firstLocalisation.title}"]
			response.setHeader("Content-disposition", "attachment; filename=${filename}.${params.extension}")
			
			
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
            JSON.use("meetingView"){
			render meetingInstance as JSON
            }
        } else {
			notFound params.id
		}
    }
    
    def toggleRegistration() {
        def meetingInstance = Meeting.get(params.id)
        if (meetingInstance) {
            def responseJson = [:]
            meetingInstance.registrationEnabled = !meetingInstance.registrationEnabled
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
            
            JSON.use("meetingView"){
            render meetingInstance as JSON
            }
        } else {
            notFound params.id
        }
    }
	
	def toggleSurvey() {
		def meetingInstance = Meeting.get(params.id)
		if (meetingInstance) {
			def responseJson = [:]
			meetingInstance.surveyEnabled = !meetingInstance.surveyEnabled
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
			
			JSON.use("meetingView"){
			render meetingInstance as JSON
			}
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
