package de.elementEvents.tema.user

import java.lang.annotation.Retention;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

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
    String fax = ""
	
	String company = ""
	String companyadd = ""
    String companyadd1 = ""
    String companyadd2 = ""
    String companyadd3 = ""
    String companynr = ""
	String position = ""
    String positionAddon = ""
	String companystreet = ""
	String companyplz = ""
	String companycity = ""
	
	
	String loginToken 
	
	TravelOptions travelOptions
    OtherOption otherOptions
    MarketplaceOptions marketplaceOptions
	Survey survey
	
	
	boolean account = false
	boolean confirmed = false
    boolean representative = false
    
    Status state = Status.UDEF
    
    
	
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
        fax blank: true, nullable: true
		mobile blank: true, nullable: true
		middlename  blank: true, nullable: true
		title blank: true, nullable: true
		company blank:true , nullable: true
		companyadd blank:true , nullable: true
        companyadd1 blank:true , nullable: true
        companyadd2 blank:true , nullable: true
        companyadd3 blank:true , nullable: true
		position blank:true , nullable: true
		companystreet blank:true , nullable: true
		companyplz blank:true , nullable: true
		companycity blank:true , nullable: true
		travelOptions blank:true , nullable: true
        otherOptions blank:true , nullable: true
        travelOptions unique: true
        otherOptions unique: true , nullable: true
        marketplaceOptions unique: true , nullable: true
		survey unique: true , nullable: true
        state blank:true , nullable: true
	}

	static mapping = {
		password column: '`password`'
        travelOptions cascade:'all-delete-orphan'
        otherOptions cascade:'all-delete-orphan'
        marketplaceOptions cascade:'all-delete-orphan'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
		// optionally, replace the dashes by adding .replaceAll('-','')
        loginToken = UUID.randomUUID().toString().replaceAll('-','').subSequence(0, 8)
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
    
    public getOtherOptionsString () {
        def returnString = ""
        if (otherOptions){ 
            otherOptions.vegan ? returnString += "Veganer \n " : ""
            otherOptions.vegatarian ? returnString += otherOptions.vegatarian + " \n " :""
            otherOptions.allergy ? returnString += "Allergien: " + otherOptions.allergy + " \n " :""
            otherOptions.wishes ? returnString += "Sonderwünsche: " + otherOptions.wishes + " \n " :""
        }
        return returnString
    }
    
    public getStatus () {
        switch (state) {
        case Status.UDEF:
            return "unbekannt"
            break;
        case Status.REPRESENTATIV:
            return "Vertretung geschickt";
            break;
        case Status.CONFIRMED:
            return "zugesagt";
            break;
        case Status.REFUSED:
            return "abgesagt";
            break;
        default:
            "unbekannt";
        }
    }
    
    public getCorrectCompanyAdd() {
        return StringUtils.isEmpty(companynr) ? companyadd : companynr
    }
    
    public getPostalSalutation() {
        return (salutation == Salutation.MR) ? "Sehr geehrter Herr" : "Sehr geehrte Frau"
    }
}


enum Salutation {
	MS("Frau"), MR("Herr")
	Salutation(String value) { this.value = value }
	final String value
	String getKey() { name() }
	String toString() { value }
}

enum Status {
    UDEF("undefined"), CONFIRMED("confirmed"), REFUSED('refused'), REPRESENTATIV('representativ')
    Status(String value) { this.value = value }
    final String value
    String getKey() { name() }
    String toString() { value }
}