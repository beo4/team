package de.elementEvents.tema.subscription

import de.elementEvents.tema.resource.ResourceOption

class SubscriptionResourceOption {
	
	String optionValue
	
	static belongsTo = [resourceOption: ResourceOption, subscription: Subscription]

    static constraints = {
    }
}
