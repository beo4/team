package de.elementEvents.tema.mail

import grails.plugin.mail.MailService;
import de.elementEvents.tema.user.User;

class RegistrationMailService {

    MailService mailService
    
    def sendNotificationEmail(User user, body){
        mailService.sendMail {
            multipart true
            to user.email
            from "support@goodyear-at-porsche.de"
            replyTo "support@goodyear-at-porsche.de"
            subject "Teilnahmebestätigung"
            html body
            attachBytes 'Anfahrtsbeschreibung.pdf','application/pdf', grailsApplication.parentContext.getResource('email/Anfahrtsbeschreibung.pdf').getFile().readBytes()
          }
    }
}
