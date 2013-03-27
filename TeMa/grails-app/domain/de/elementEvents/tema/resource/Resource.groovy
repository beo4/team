package de.elementEvents.tema.resource

import de.elementEvents.tema.event.Event;

class Resource {
	
	static hasMany = [options: ResourceOption, i18n: Resource_i18n]
	
	static belongsTo = [event: Event]

    static constraints = {

    }
}
