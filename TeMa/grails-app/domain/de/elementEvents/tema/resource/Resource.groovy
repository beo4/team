package de.elementEvents.tema.resource

class Resource {
	
	String name
	String description
	
	static hasMany = [options: ResourceOption, i18n: Resource_i18n]

    static constraints = {

    }
}
