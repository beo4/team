package de.elementEvents.tema.user

class Survey {
    
	int entryValue
	int organisationValue
	int theme
	String themeText
	
	int marketplace
	String marketplaceText
	
	
	int marketplaceTime
	int dailyBusiness
	String dailyBusinessText
	
	int overallImpression
	int expectation
	int reentry
	
	Boolean aufbauherstellermanagement
	Boolean careport
	Boolean classicparts
	Boolean euromobil
	Boolean gewahrleistung
	Boolean grosskundenbetreuung
	Boolean ihvpost
	Boolean inspektion
	Boolean kbaservice
	Boolean marktchancen
	Boolean originalteile
	Boolean produktbetreuung
	Boolean servicemarketing
	Boolean servicequalifizierungen
	Boolean servicestandards
	Boolean skpcommerce
	Boolean taxi
	Boolean leasing 
	Boolean merchandising 
	Boolean onlinesz 
	Boolean partnerbetreuung 
	Boolean nutzfahrzeuge 
	Boolean werkstattsysteme 
	    
    String other
    
    static belongsTo = [user:User]

    static constraints = {
            
		entryValue blank: true, nullable: true
		organisationValue blank: true, nullable: true
		theme blank: true, nullable: true
		themeText blank: true, nullable: true 
		marketplace blank: true, nullable: true
		marketplaceText blank: true, nullable: true
		marketplaceTime blank: true, nullable: true
		dailyBusiness blank: true, nullable: true
		dailyBusinessText blank: true, nullable: true
		overallImpression blank: true, nullable: true
		expectation blank: true, nullable: true
		reentry blank: true, nullable: true
		other blank: true, nullable: true
		aufbauherstellermanagement blank: true, nullable: true
		careport blank: true, nullable: true
		classicparts blank: true, nullable: true
		euromobil blank: true, nullable: true
		gewahrleistung blank: true, nullable: true
		grosskundenbetreuung blank: true, nullable: true
		ihvpost blank: true, nullable: true
		inspektion blank: true, nullable: true
		kbaservice blank: true, nullable: true
		marktchancen blank: true, nullable: true
		originalteile blank: true, nullable: true
		produktbetreuung blank: true, nullable: true
		servicemarketing blank: true, nullable: true
		servicequalifizierungen blank: true, nullable: true
		servicestandards blank: true, nullable: true
		skpcommerce blank: true, nullable: true
		taxi blank: true, nullable: true
		leasing blank: true, nullable: true
		merchandising blank: true, nullable: true
		onlinesz blank: true, nullable: true
		partnerbetreuung blank: true, nullable: true
		nutzfahrzeuge blank: true, nullable: true
		werkstattsysteme blank: true, nullable: true
    }
    
    static mapping = {
		tablePerHierarchy false
		
        other type: 'text'
		themeText type: 'text'
		marketplaceText type: 'text'
		dailyBusinessText type: 'text'
    }
}
