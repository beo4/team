package de.elementEvents.tema.event

class EventLanguage {
	
	Locale language
	String languageName
	
	static belongsTo = [event: Event]

    static constraints = {
    }
	
	@Override
	public String toString() {
		return language.getDisplayLanguage() + " (" + language.getDisplayCountry()  + ")";
	}
}
