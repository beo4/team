package de.elementEvents.tema.subscription

import de.elementEvents.tema.meeting.Meeting
import de.elementEvents.tema.user.User;

class Subscription {
	
	static belongsTo = [meeting: Meeting, user: User]
	
	static hasMany = [bookedOptions: SubscriptionResourceOption]

    static constraints = {
    }
}
