package de.elementEvents.tema.event

class Event_i18n {

	
	String title
	String subtitle
	String description
	
	static belongsTo = [event: Event, eventLanguage: EventLanguage]
	
    static constraints = {
		description type: 'text'
    }
}
