package de.elementEvents.tema.resource

import org.joda.time.LocalDateTime;

import de.elementEvents.tema.meeting.Meeting;
import de.elementEvents.tema.subscription.SubscriptionResourceOption;

class ResourceOption {
	
	LocalDateTime availableStart
	LocalDateTime availableEnd
	
	
	Meeting meeting
	
	static belongsTo = [resource: Resource]
	
	static hasMany = [subscriptions: SubscriptionResourceOption, i18n: ResourceOption_i18n]

    static constraints = {
		meeting: nullable: true
    }
}
