package de.elementEvents.tema.meeting


import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.subscription.Subscription;

class Meeting {
	
	LocalDateTime start
	LocalDateTime end
	
    boolean registrationEnabled
    
	static belongsTo = [event: Event]
	
	static hasMany = [subscriber: Subscription, i18n: Meeting_i18n]

    static constraints = {
		subscriber nullable: true
    }
    
    public LocalDateTime getDeadline() {
        start.minus(21)
    }
    
    public getDefaultDescription() {
        return subscriber.first().description
    }
    
    public getDefaultTitle() {
        return i18n.first().title
    }
    
    public getDefaultSubtitle() {
        return i18n.first().subtitle
    }
    
    static marshalling={
        json{
            meetingView{
                deep "i18n","eventLanguage"
                attribute "start","end","subscriber"
            }
            meetingList{
                deep "i18n","eventLanguage"
                attribute "start","end"
                ignore "subscriber"
            }

        }
    }
}
