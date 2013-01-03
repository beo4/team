package de.elementEvents.tema.meeting

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.subscription.Subscription;

class Meeting {
	
	String title
	String subtitle
	String description
	
	static belongsTo = [event: Event]
	
	static hasMany = [subscriber: Subscription]

    static constraints = {
		description type: 'text'
    }
}
