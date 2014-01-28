package de.elementEvents.tema.user

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.dao.DataIntegrityViolationException

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.subscription.Subscription;
import de.elementEvents.tema.user.importer.UserExcelImporter;
import grails.converters.JSON
import grails.plugin.jodatime.converters.JodaConverters;
import static javax.servlet.http.HttpServletResponse.*

class UserController {
	static public main = true

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", upload: "POST", delete: "POST"]
    
    def excelImportService

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', User.count())
		render User.list(params) as JSON
    }

    def save() {
        def userInstance = new User(request.JSON)
		int eventId = request.JSON.event.id
		
		userInstance.event = Event.get(eventId)
		userInstance.setSalutation(Salutation.valueOf(request.JSON.salutation.name))
		
        def responseJson = [:]
        if (userInstance.save(flush: true)) {
			
			if (request.JSON.meeting) {
				def meeting = Meeting.get(request.JSON.meeting.id)
				Subscription subscription = new Subscription()
				subscription.meeting = meeting
				subscription.user = userInstance
					if (subscription.save(flush: true)) {
						response.status = SC_CREATED
						responseJson.id = userInstance.id
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
	
	def create() {
		
		def event = Event.get(params.eventId)
		
		def defaultsValues = [event: event]
		
		if (event) {
			User user = new User();
			user.event = event
		}
		if (params.meetingId)
		{
			defaultsValues.meeting = Meeting.get(params.meetingId)
		}
		
		defaultsValues.salutations = Salutation.values();
		
		JSON.use("deep")
		JodaConverters.registerJsonAndXmlMarshallers()
		render defaultsValues as JSON
	}
	
	def createFromFile() {
		
		def file = request.getFile('file')
		
        def event
        def meeting
        if (params.eventId)
        {
            event = Event.get(params.eventId)
        }
        if (params.meetingId)
        {
            meeting = Meeting.get(params.meetingId)
        }
        
        //save the file in tmp
        def storagePath = new File("tmp")
        if(!storagePath.exists()) {
            if (storagePath.mkdirs()){
            } else {
            }
        }
        def userInstanceList = []
        if (!file.isEmpty()) {
            def tmpSave = new File("${storagePath}/tmp.xls");
            if (tmpSave.exists()){
                tmpSave.delete()
            }
            file.transferTo(tmpSave)
            UserExcelImporter importer = new UserExcelImporter(tmpSave.absoluteFile)
            def userlist = importer.getUser()
            
            userlist.each {
                def userInstance = User.findByUsername(it.email)
                if (userInstance){
                    userInstance.properties = it
                } else {
                    userInstance = new User(it)
                }
                userInstance.event = event
                if (it.salutation=="Herr")
                    userInstance.salutation = Salutation.MR
                else {
                    userInstance.salutation = Salutation.MS
                }
               if (!userInstance.username){
                   userInstance.username = userInstance.email+UUID.randomUUID().toString().replaceAll('-','').subSequence(0, 8)
                   userInstance.password = userInstance.username
               }
               userInstance.street = userInstance.street
               
               
               userInstance.validate()
               if (userInstance.save(flush: true)) {
                   def subscription = Subscription.findByUserAndMeeting(userInstance,meeting)
                    if (!subscription){
                        subscription = new Subscription()
                        subscription.meeting = meeting
                        subscription.user = userInstance
                            if (subscription.save(flush: false)) {
                                userInstanceList.add(userInstance)
                            }
                    }
                    
                }
                
            }
        }

		JodaConverters.registerJsonAndXmlMarshallers()
		render userInstanceList as JSON
	}
    
    def fileCreate() {
        def defaultsValues = [eventId:0,meetingId:0 ] 
        if (params.eventId)
        {
            defaultsValues.eventId = params.eventId
        }
        if (params.meetingId)
        {
            defaultsValues.meetingId = params.meetingId
        }
        
        [meetingId:defaultsValues.meetingId,eventId:defaultsValues.eventId]
        
    }
    
    def exportFile() {
        def meetingInstance
        
        if (params.meetingId)
        {
            meetingInstance = Meeting.get(params.meetingId)
            
            meetingInstance.subscriber.each {
                it.user
            }
        }
        
        
        
    }

    def get() {
        def userInstance = User.get(params.id)
        if (userInstance) {
			render userInstance as JSON
        } else {
			notFound params.id
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
						default: 'Another user has updated this User while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        userInstance.properties = request.JSON

        if (userInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = userInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = userInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }
    
    def upload() {
        def event = Event.get(params.eventId)
        
        def defaultsValues = [event: event]
        
        if (event) {
            User user = new User();
            user.event = event
        }
        if (params.meetingId)
        {
            defaultsValues.meeting = Meeting.get(params.meetingId)
        }
        
        def responseJson = [:]
        
        responseJson.defaults = defaultsValues
        
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
            userInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])]
        render responseJson as JSON
    }
    
    
}
