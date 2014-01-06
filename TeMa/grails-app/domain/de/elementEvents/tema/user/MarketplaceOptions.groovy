package de.elementEvents.tema.user

class MarketplaceOptions {
    
    Boolean aufbauherstellermanagement
    Boolean spezialfahrzeuge
    Boolean careport
    Boolean classicparts
    Boolean euromobil
    Boolean feldbeobachtung
    Boolean gewahrleistung
    Boolean grosskundenbetreuung
    Boolean heritage
    Boolean ihvpost
    Boolean unfallverhutung
    Boolean inspektion
    Boolean kbaservice
    Boolean marktchancen
    Boolean originalteile
    Boolean serviceapp
    Boolean planungimah
    Boolean produktbetreuung
    Boolean servicemarketing
    Boolean servicequalifizierungen
    Boolean servicestandards
    Boolean skpcommerce
    Boolean taxi
    Boolean leasing
    Boolean merchandising
    
    String other
    
    static belongsTo = [user:User]

    static constraints = {
        aufbauherstellermanagement blank: true, nullable: true
        spezialfahrzeuge blank: true, nullable: true
        careport blank: true, nullable: true
        classicparts blank: true, nullable: true
        euromobil blank: true, nullable: true
        feldbeobachtung blank: true, nullable: true
        gewahrleistung blank: true, nullable: true
        grosskundenbetreuung blank: true, nullable: true
        heritage blank: true, nullable: true
        ihvpost blank: true, nullable: true
        unfallverhutung blank: true, nullable: true
        inspektion blank: true, nullable: true
        kbaservice blank: true, nullable: true
        marktchancen blank: true, nullable: true
        originalteile blank: true, nullable: true
        serviceapp blank: true, nullable: true
        planungimah blank: true, nullable: true
        produktbetreuung blank: true, nullable: true
        servicemarketing blank: true, nullable: true
        servicequalifizierungen blank: true, nullable: true
        servicestandards blank: true, nullable: true
        skpcommerce blank: true, nullable: true
        taxi blank: true, nullable: true
        leasing blank: true, nullable: true
        merchandising blank: true, nullable: true
        other blank: true, nullable: true
        
    }
    
    static mapping = {
        other type: 'text'
    }
}