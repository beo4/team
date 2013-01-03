package de.elementEvents.tema.event

import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.user.User;

class Event {

	String title
	String subtitle
	String description
	
	static hasMany = [meetings: Meeting, participants: User]
	
    static constraints = {
		description type: 'text'
    }
}
