package de.elementEvents.tema.user

import de.elementEvents.tema.event.Event;
import de.elementEvents.tema.event.EventLanguage;
import de.elementEvents.tema.meeting.Meeting

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	Salutation salutation
	
	String email
	String firstname
	String middlename
	String lastname
	String title
	
	String street = ""
	String plz = ""
	String city = ""
	String country = ""
	
	String phone = ""
	String mobile = ""
	
	String company = ""
	String position = ""
	String companystreet = ""
	String companyplz = ""
	String companycity = ""
	
	String loginToken 
	
	
	boolean account = false
	
	EventLanguage language
	
	static belongsTo = [event: Event]

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email: true, blank: true, nullable: true
		firstname blank: true, nullable: true
		lastname blank: true, nullable: true
		language blank: true, nullable: true
		event blank: true, nullable: true
		loginToken blank: true, nullable: true
		street blank: true, nullable: true
		plz blank: true, nullable: true
		city blank: true, nullable: true
		country blank: true, nullable: true
		phone blank: true, nullable: true
		mobile blank: true, nullable: true
		middlename  blank: true, nullable: true
		title blank: true, nullable: true
		company blank:true , nullable: true
		position blank:true , nullable: true
		companystreet blank:true , nullable: true
		companyplz blank:true , nullable: true
		companycity blank:true , nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
		// optionally, replace the dashes by adding .replaceAll('-','')
        loginToken = UUID.randomUUID().toString().replaceAll('-','')
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


enum Salutation {
	MR("Mr"), MS("Ms"), MRS("Mrs")
	Salutation(String value) { this.value = value }
	final String value
	String getKey() { name() }
	String toString() { value }
	
}