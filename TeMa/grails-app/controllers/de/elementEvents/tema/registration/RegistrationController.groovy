package de.elementEvents.tema.registration

import java.awt.GraphicsConfiguration.DefaultBufferCapabilities;
import java.util.logging.Logger;

import javax.validation.Validation;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;
import org.codehaus.groovy.grails.web.mapping.LinkGenerator;
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.mail.MailException

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import de.elementEvents.tema.event.Event_i18n;
import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.meeting.Meeting_i18n;
import de.elementEvents.tema.subscription.Subscription
import de.elementEvents.tema.user.MarketplaceOptions;
import de.elementEvents.tema.user.OtherOption
import de.elementEvents.tema.user.Salutation;
import de.elementEvents.tema.user.Status;
import de.elementEvents.tema.user.Survey;
import de.elementEvents.tema.user.TravelOptions;
import de.elementEvents.tema.user.User;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import grails.plugin.rendering.pdf.PdfRenderingService;
import grails.plugins.springsecurity.SpringSecurityService;
import groovy.text.SimpleTemplateEngine
import static javax.servlet.http.HttpServletResponse.*

class RegistrationController {
	static public main = true

    static final int SC_UNPROCESSABLE_ENTITY = 422
    
    static final String EMAIL = "support@vwn.serviceleiterkonferenz.de"

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
    def mailService
    def pdfRenderingService
    LinkGenerator grailsLinkGenerator
    
    String serverUrl() {
        // Generate: http://localhost:8080/link-generator
        grailsLinkGenerator.serverBaseURL
    }

    def index() { }
    
    def faq() { 
        render(view:"faq");
    }

    def download() {
        render(view:"download");
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
	
	def getSurvey() {
		def surveyInstance
		if (params.surveyId) {
			surveyInstance = Survey.get(params.surveyId)
			if (surveyInstance){
				render  surveyInstance as JSON
			} else {
				notFound params.surveyId
			}
		}
	}

    def get() {
		def userInstance
		if (params.loginToken) {
			userInstance = User.findByLoginToken(params.loginToken)
			if (userInstance){
				SpringSecurityUtils.reauthenticate userInstance.username,""
				
			}
			
		} else if (springSecurityService.loggedIn) {
			userInstance = User.get(springSecurityService.currentUser.id)
		}
        if (userInstance) {
			//login User
			def jsonResponse = [:]
			jsonResponse.participant = userInstance
			if (springSecurityService.loggedIn) {
				jsonResponse.isLoggedIn = true
			}
			def subscriptionInstance = Subscription.findByUser(userInstance);
			def event = Event.get(userInstance.event.id)
			def language = userInstance.language
			
			// if user has no language choosen and only one is available set it
			if(language == null && event.eventLanguage.size()==1){
				userInstance.language = event.eventLanguage.first()
				userInstance.save(flush: true)
			}
			
			if (userInstance.language != null) {
				jsonResponse.event_i18n = Event_i18n.findByEventAndEventLanguage(event, userInstance.language)
			}
			
			if (!subscriptionInstance){
				subscriptionInstance = new Subscription();
				subscriptionInstance.user = userInstance;
			} 
			
			jsonResponse.meeting = subscriptionInstance.meeting
			
            if (userInstance.language != null) {
                jsonResponse.meeting_i18n = Meeting_i18n.findByMeetingAndI18n(subscriptionInstance.meeting, userInstance.language)
            }
			
			jsonResponse.subscription = subscriptionInstance
			jsonResponse.salutations = Salutation.values()
            jsonResponse.marketplaceOptions = userInstance.marketplaceOptions
			
			//springSecurityService.reauthenticate userInstance.username
			JodaConverters.registerJsonAndXmlMarshallers()
			render jsonResponse as JSON
        } else {
			notFound params.loginToken
		}
    }
	
	def getMeetings() {
		def eventInstance = Event.get(params.event)
		def meetings = eventInstance.meetings
		
		def responseJson = [:]

		responseJson.meetings = meetings
		
		render responseJson as JSON
		
	}
	
	def saveTravelOptions() {
        def jsonObject = request.JSON
        
        def userInstance = User.get(request.JSON.id)
        def travelOptionsInstance
        if (!userInstance.travelOptions){
            travelOptionsInstance = new TravelOptions(request.JSON.travelOptions)
            userInstance.travelOptions
        }
        else {
            travelOptionsInstance = userInstance.travelOptions
            travelOptionsInstance.properties = request.JSON.travelOptions
        }
        
        def responseJson = [:]
        if (!travelOptionsInstance.selectedTravelOption) {
            response.status = SC_UNPROCESSABLE_ENTITY
            
            responseJson.errors = ["selectedTravelOption":'Bitte wählen Sie eine Option aus']
            render responseJson as JSON
            return
        }
        
        
        //travelOptionsInstance.selectedTravelOption = jsonObject.travelOptions.selectedTravelOption.name
        
        def otherOptionsInstance
        if (!userInstance.otherOptions){
            otherOptionsInstance = new OtherOption(request.JSON.otherOptions)
            userInstance.otherOptions = otherOptionsInstance
        }
        else {
            otherOptionsInstance = userInstance.otherOptions
            otherOptionsInstance.properties = request.JSON.otherOptions
        }
        
        if (!otherOptionsInstance.vegatarian) {
            response.status = SC_UNPROCESSABLE_ENTITY
            
            responseJson.errors = ["vegatarian":'Bitte wählen Sie eine Option aus']
            render responseJson as JSON
            return
        }
        if (otherOptionsInstance.vegan==null) {
            response.status = SC_UNPROCESSABLE_ENTITY
            
            responseJson.errors = ["vegan":'Bitte wählen Sie eine Option aus']
            render responseJson as JSON
            return
        }
		
		if (travelOptionsInstance.save(flush: true) && otherOptionsInstance.save(flush: true) && userInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
            responseJson.participant = userInstance
            responseJson.travelOptions = travelOptionsInstance
            responseJson.otherOptions = otherOptionsInstance
		} else {
			response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
		}
		render responseJson as JSON
		
	}
    
    def confirm() {
       def userInstance = User.get(params.id)
        if (!userInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (userInstance.version > request.JSON.version) {
                response.status = SC_CONFLICT
                responseJson.message = message(code: 'default.optimistic.locking.failure',
                        args: [message(code: 'user.label', default: 'User')],
                        default: 'Another user has updated this Subscription while you were editing')
                cache false
                render responseJson as JSON
                return
            }
        }

        userInstance.properties = request.JSON
        
        if (!userInstance.confirmed) {
            response.status = SC_UNPROCESSABLE_ENTITY
            
            responseJson.errors = ["confirmed":'Bitte aktzeptieren Sie die Datenschutzerklärung']
            render responseJson as JSON
            return
        } else {
        
        update()
        }
   
    }

    def update() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (userInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'user.label', default: 'User')],
						default: 'Another user has updated this Subscription while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }
        
        userInstance.properties = request.JSON
        
        
        def marketplaceOptionsArr = request.JSON.marketplaceOptions
        def marketplaceOptions = userInstance.marketplaceOptions
        marketplaceOptions.properties = marketplaceOptionsArr
        
        userInstance.setSalutation(Salutation.valueOf(request.JSON.salutation.name))
        
        
        
        
        if (userInstance.save(flush: false)) {
            response.status = SC_OK
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			responseJson.participant = userInstance
            responseJson.marketplaceOptions = userInstance.marketplaceOptions
            if (userInstance.confirmed){
                sendNotificationEmail(userInstance)
            }
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        
        render responseJson as JSON
    }
	
	def updateSurvey() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			notFound params.id
			return
		}

		def responseJson = [:]

		if (request.JSON.version != null) {
			if (userInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'user.label', default: 'User')],
						default: 'Another user has updated this Subscription while you were editing')
				cache false
				render responseJson as JSON
				return
			}
		}
		
		def survey = request.JSON.survey
		def surveyOptions = userInstance.survey
		if (!surveyOptions) {
			userInstance.survey = new Survey(survey)
		} else {
			surveyOptions.properties = survey
		}
		
		if (userInstance.save(flush: false)) {
			response.status = SC_OK
			responseJson.id = userInstance.id
			responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			responseJson.participant = userInstance
			
		} else {
			response.status = SC_UNPROCESSABLE_ENTITY
			responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
				[(it.field): message(error: it)]
			}
		}
		
		render responseJson as JSON
	}

    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
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
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Participant'), params.loginToken])]
        render responseJson as JSON
    }
    
    
    private sendNotificationEmail(User user){
        def subscriptionInstance = Subscription.findByUser(user);
        def serverUrl = serverUrl()
        
        Meeting_i18n i18n = Meeting_i18n.findByMeetingAndI18n(subscriptionInstance.meeting, user.language)
        
        def vwLogo = grailsApplication.parentContext.getResource('images/VWN.png').getFile().getBytes()
        def slkLogo = grailsApplication.parentContext.getResource('images/Keyvisual_3D_2016_small.jpg').getFile().getBytes()
        
        ByteArrayOutputStream bytes = pdfRenderingService.render(template: "/pdf/confirmation", model: [participant: user, meeting_i18n:i18n, meeting:subscriptionInstance.meeting, serverUrl:serverUrl, vwlogo:vwLogo, slklogo:slkLogo])
        
        def fileName = 'Teilnehmerdaten ' + user.firstname + ' ' + user.lastname + '.pdf'
        
        try {
        mailService.sendMail {
            multipart true
            bcc EMAIL
            to user.email
            from EMAIL
            replyTo EMAIL
            subject "Teilnahmebestätigung"
            html g.render(template:"/email/emailTmpl",
                model:[participant: user, meeting_i18n:i18n, meeting:subscriptionInstance.meeting, serverUrl:serverUrl])
            attach fileName,'application/pdf', bytes.toByteArray()
            inline 'VWN','image/png', grailsApplication.parentContext.getResource('images/VWN.png').getFile().readBytes()
            inline 'VWN-SLK2014','image/jpg', grailsApplication.parentContext.getResource('images/Keyvisual_3D_2016_420.jpg').getFile().readBytes()
          }
        } catch (Exception e) {
            log.error "Error: ${e.message}", e
        }
        bytes.close()    
    }
    
    private sendRepresentativNotificationEmail(User user, Meeting meeting){
        Meeting_i18n i18n = Meeting_i18n.findByMeetingAndI18n(meeting, user.language)
        def serverUrl = serverUrl()
        try {
        mailService.sendMail {
            multipart true
            bcc "support@vwn.serviceleiterkonferenz.de"
            to user.email
            from EMAIL
            replyTo EMAIL
            subject "Einladung"
            html g.render(template:"/email/emailRepTmpl",
                model:[participant:user, meeting_i18n:i18n, meeting:meeting, serverUrl:serverUrl])
            inline 'VWN','image/png', grailsApplication.parentContext.getResource('images/VWN.png').getFile().readBytes()
            inline 'VWN-SLK2014','image/jpg', grailsApplication.parentContext.getResource('images/Keyvisual_3D_2016_420.jpg').getFile().readBytes()
          }
        } catch (Exception e) {
            log.error "Error: ${e.message}", e
        }
        
    }
    
    def createRepresentativ() {
        def userInstance = new User(request.JSON)
        int eventId = request.JSON.event.id
        
        userInstance.event = Event.get(eventId)
        userInstance.setSalutation(Salutation.valueOf(request.JSON.salutation.name))
        
        userInstance.language = EventLanguage.get(request.JSON.language.id)
        
        userInstance.username = UUID.randomUUID().toString().replaceAll("-", "")
        userInstance.password = UUID.randomUUID().toString().replaceAll("-", "")
        
        userInstance.representative = true        
        
        def responseJson = [:]
        
        
        if (request.JSON.emailrepeat != userInstance.email) {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.rejectValue("email", "nicht identisch")
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
            
            
        }else if (userInstance.save(flush: true)) {
            
            if (request.JSON.meeting) {
                def meeting = Meeting.get(request.JSON.meeting.id)
                Subscription subscription = new Subscription()
                subscription.meeting = meeting
                subscription.user = userInstance
                    if (subscription.save(flush: true)) {
                        response.status = SC_CREATED
                        responseJson.id = userInstance.id
                        sendRepresentativNotificationEmail(userInstance, subscription.meeting)
                        responseJson.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                    }
                }
            
            
            
            response.status = SC_CREATED
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }
    
    
}
