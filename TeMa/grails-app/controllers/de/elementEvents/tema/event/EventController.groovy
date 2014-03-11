package de.elementEvents.tema.event

import org.springframework.dao.DataIntegrityViolationException
import pl.touk.excel.export.WebXlsxExporter
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import static javax.servlet.http.HttpServletResponse.*

class EventController {
	static public main = true

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def exportService

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Event.count())
        
		def jsonResult = Event.list(params)
		JodaConverters.registerJsonAndXmlMarshallers()
        JSON.use("eventList"){
		render jsonResult as JSON
        }
    }

    def save() {
		def jsonObject = request.JSON
		def eventInstance = new Event()
		def eventLanguages = []
		def i18ns = []
		
		for ( is in jsonObject.i18n) {
			def eventi18n = new Event_i18n(is)
			def eventLanguage = new EventLanguage(is.eventLanguage)
			eventi18n.eventLanguage = eventLanguage
			eventInstance.addToI18n(eventi18n)
			eventInstance.addToEventLanguage(eventLanguage)
			
		}
		
        
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
			JSON.use("eventView"){
    			JodaConverters.registerJsonAndXmlMarshallers()
    			render eventInstance as JSON
			}
        } else {
			notFound params.id
		}
    }
	
	def create() {
		def availableLanuages = Locale.getAvailableLocales()
		
		if (availableLanuages) {
			def defaultsValues = [languages:[],event: new Event()]
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
				if (cc.size() == 1 || (cc[1].isEmpty() || cc[0].isEmpty())){
					locale = new Locale(cc[0])
					result = locale.getDisplayLanguage()
				}
				else if (cc.size() == 2) {
					locale = new Locale(cc[0],cc[1])
					result = locale.getDisplayLanguage() + " (" + locale.getDisplayCountry() + ")"
				}
				 
				 
			 } else {
			 locale = new Locale("de","DE")
			 }
	
			 eventLanguageInstance.languageName = result
			 eventLanguageInstance.language = locale
			 event_i18n.eventLanguage = eventLanguageInstance
			 
			 def returnresult = [eventLanguage: eventLanguageInstance, i18n: event_i18n]
			 
			 render returnresult as JSON
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
    
    def exportList() {
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        
        List fields = ["salutation", "title","firstname", "middlename","lastname", "email","loginToken", "street","plz", "city","mobile","company", "companyadd","position","companystreet","companyplz","companycity","travelOptions.arrivalDate","travelOptions.departureDate"]
        Map labels = ["salutation" :"Anrede", "title":"Titel","firstname":"Vorname", "email":"Email","middlename":"Zusatzname","lastname":"Nachname", "street":"Strasse","plz":"PLZ", "city":"Ort","mobile":"Telefon","company":"Firma", "companyadd":"Firma2","position":"Position","companystreet":"Firmen Adresse","companyplz":"Fimen PLZ","companycity":"Firmen Ort","travelOptions.arrivalDate":"Ankuft Datum","travelOptions.departureDate":"Abreise Datum"]
        Map formatters = [:]
        
        
        if (params.eventId){
            def eventInstance =  Event.get(params.eventId)
            def firstLocalisation = eventInstance.i18n.asList()[0]
            def filename = firstLocalisation.title + "_" + firstLocalisation.subtitle
            Map parameters = [title: "${firstLocalisation.title + "_" + firstLocalisation.subtitle}"]
            response.setHeader("Content-disposition", "attachment; filename=${filename}.${params.extension}")
            
            def userList = eventInstance.meetings*.subscriber*.user
            exportService.export(params.format, response.outputStream,userList, fields, labels,formatters,parameters)
        }
       

    }
    
    def exportList2() {
        
        def row1 = [
            "Login Token",
            "Registrierungsstatus",
            "Datenschutz",
            "Anrede",
            "Titel",
            "Vorname",
            "Zweitname",
            "Nachname",
            "Straße, Hausnr.",
            "PLZ",
            "Ort",
            "Mobilnr.",
            "Anreise per PKW/ Bahn/ Flugzeug",
            "Anreisedatum",
            "Anreiseflughafen / Bahnhof / Nummernschild",
            "Anreise Airline",
            "Airline Ankunftszeit",
            "Abreisedatum",
            "Abreiseflughafen / Bahnhof",
            "Abreise Airline",
            "Abflugszeit",
            "Spezielle Anforderungen",
            "Firma",
            "Firmenzusatz",
            "Position",
            "Straße, Hausnr.",
            "PLZ",
            "Ort",
            "Email"            
            ]
        
        
        def withProperties = [
            "loginToken", 
            "status",
            "confirmed",
            "salutation.value", 
            "title",
            "firstname", 
            "middlename",
            "lastname", 
            "street",
            "plz", 
            "city",
            "mobile",
            "travelOptions.selectedTravelOption",
            "travelOptions.arrivalDate",
            "travelOptions.arrivalPlace",
            "travelOptions.arrivalAirline",
            "travelOptions.arrivalTime",
            "travelOptions.departureDate",
            "travelOptions.departurePlace",
            "travelOptions.departureAirline",
            "travelOptions.departureTime",
            "otherOptionsString",
            "company", "companyadd","position","companystreet","companyplz","companycity","email"
            ]
      
        if (params.eventId){
            def eventInstance =  Event.get(params.eventId)
            def firstLocalisation = eventInstance.i18n.asList()[0]
            def filename = firstLocalisation.title + "_" + firstLocalisation.subtitle
            def user = eventInstance.participants.toList()
            

            
            new WebXlsxExporter().with {
                setResponseHeaders(response)
                int index = 0
                fillRow(row1,index)
                index++
                add(user ,withProperties,1)
                save(response.outputStream)
            }
        }
        
    }
}
