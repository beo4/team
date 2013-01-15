package de.elementEvents.tema.event

import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.user.User;

class Event {
	
	

	static hasMany = [meetings: Meeting, participants: User, i18n: Event_i18n, eventLanguage: EventLanguage]
	
    static constraints = {
		eventLanguage minSize: 1
		meetings nullable: true
		participants nullable: true
		i18n nullable: true
    }
}
