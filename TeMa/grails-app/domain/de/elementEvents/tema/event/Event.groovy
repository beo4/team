package de.elementEvents.tema.event

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
	
	
}
