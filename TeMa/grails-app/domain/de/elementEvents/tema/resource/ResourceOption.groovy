package de.elementEvents.tema.resource

import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.subscription.SubscriptionResourceOption;

class ResourceOption {
	
	String name
	
	Meeting meeting
	
	static belongsTo = [resource: Resource]
	
	static hasMany = [subscriptions: SubscriptionResourceOption]

    static constraints = {
		meeting: nullable: true
    }
}
