package de.elementEvents.tema.resource

class Resource {
	
	String name
	String description
	
	static hasMany = [options: ResourceOption]

    static constraints = {
		description: type: 'text'
    }
}
