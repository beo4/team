package de.elementEvents.tema.resource

import java.util.Locale;

import de.elementEvents.tema.event.EventLanguage;

class ResourceOption_i18n {
	
	Locale language
	
	String name
	
	static belongsTo = [resourceOption: ResourceOption, i18n: EventLanguage]

    static constraints = {
    }
}
