package de.elementEvents.tema.resource

import java.util.Locale;

import de.elementEvents.tema.event.EventLanguage;

class Resource_i18n {

	String name
	String description
	
	static belongsTo = [resource: Resource, i18n: EventLanguage]
	
    static constraints = {
		description type: 'text'
    }
}
