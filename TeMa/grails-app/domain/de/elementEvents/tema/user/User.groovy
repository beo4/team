package de.elementEvents.tema.user

import de.elementEvents.tema.event.EventLanguage;

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String email
	String firstname
	String lastname
	
	EventLanguage language

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email: true, blank: true, nullable: true
		firstname blank: true, nullable: true
		lastname blank: true, nullable: true
		language blank: true, nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
