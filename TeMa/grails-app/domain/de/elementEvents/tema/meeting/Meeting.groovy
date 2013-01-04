package de.elementEvents.tema.meeting


import org.joda.time.LocalDateTime;

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.subscription.Subscription;

class Meeting {
	
	LocalDateTime start
	LocalDateTime end
	
	static belongsTo = [event: Event]
	
	static hasMany = [subscriber: Subscription, i18n: Meeting_i18n]

    static constraints = {
    }
}
