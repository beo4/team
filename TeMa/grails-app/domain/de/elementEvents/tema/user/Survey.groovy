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
	Boolean careport // serviceprozesse
	Boolean classicparts
	Boolean euromobil
	Boolean gewahrleistung
	Boolean grosskundenbetreuung
	Boolean ihvpost
	Boolean inspektion
	Boolean kbaservice
	Boolean marktchancen
	Boolean originalteile
	Boolean produktbetreuung //technik live
	Boolean servicemarketing
	Boolean servicequalifizierungen
	Boolean servicestandards //Service Prozesse
	Boolean skpcommerce //it-systeme im Service
	Boolean taxi
	Boolean leasing 
	Boolean merchandising // zubehoer und lifestyle
	Boolean onlinesz //mobile online dienste
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
		aufbauherstellermanagement blank: true, nullable: true //Betriebseinrichtung
		careport blank: true, nullable: true //Fahrzeug-Aufbauten im Service
		classicparts blank: true, nullable: true //VW Classic Parts 
		euromobil blank: true, nullable: true //Euromobil
		gewahrleistung blank: true, nullable: true // Diesel/EA189
		grosskundenbetreuung blank: true, nullable: true //
		ihvpost blank: true, nullable: true //
		inspektion blank: true, nullable: true // IT-Systeme im Service
		kbaservice blank: true, nullable: true 
		marktchancen blank: true, nullable: true
		originalteile blank: true, nullable: true // Original Teile
		produktbetreuung blank: true, nullable: true //großkundenbetreuung
		servicemarketing blank: true, nullable: true //
		servicequalifizierungen blank: true, nullable: true // Service Qualifizierung
		servicestandards blank: true, nullable: true //  Service Prozesse
		skpcommerce blank: true, nullable: true // Service Professionalisierung
		taxi blank: true, nullable: true //Personalmarketing
		leasing blank: true, nullable: true // Volkswagen Financial Services
		merchandising blank: true, nullable: true // VW Zubehör und Lifestyle
		onlinesz blank: true, nullable: true // Mobile Online Dienste
		partnerbetreuung blank: true, nullable: true
		nutzfahrzeuge blank: true, nullable: true
		werkstattsysteme blank: true, nullable: true // Technik & Qualität
    }
    
    static mapping = {
		tablePerHierarchy false
		
        other type: 'text'
		themeText type: 'text'
		marketplaceText type: 'text'
		dailyBusinessText type: 'text'
    }
}
