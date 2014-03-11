package de.elementEvents.tema.event

import java.text.AttributedCharacterIterator.Attribute;

import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.resource.Resource;
import de.elementEvents.tema.user.User;

class Event {

	static hasMany = [meetings: Meeting, resources: Resource, participants: User, i18n: Event_i18n, eventLanguage: EventLanguage]
	
    static constraints = {
		eventLanguage minSize: 1
		meetings nullable: true
		participants nullable: true
		i18n nullable: true
		resources nullable: true
    }
	
	static mapping = {
		eventLanguage: 'join'
		eventLanguage: lazy: false
		i18n: lazy: false
		i18n: 'join'
	}
    
    static marshalling={
        json{
            eventView{
                deep "i18n","eventLanguage"
                ignore "participants"
                serializer {
                    "meetings"
                }
            }

        }
	}
    
    public getDefaultDescription() {
        return i18n.first().description
    }
    
    public getDefaultTitle() {
        return i18n.first().title
    }
    
    public getDefaultSubtitle() {
        return i18n.first().subtitle
    }
    
    
	
}
