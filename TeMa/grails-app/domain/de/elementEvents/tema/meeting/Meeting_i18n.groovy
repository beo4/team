package de.elementEvents.tema.meeting

import java.util.Locale;

import de.elementEvents.tema.event.EventLanguage;

class Meeting_i18n {

	String title
	String subtitle
	String description
	
	static belongsTo = [meeting: Meeting, i18n: EventLanguage]
	
    static mapping = {
		description type: 'text'
    }
}
