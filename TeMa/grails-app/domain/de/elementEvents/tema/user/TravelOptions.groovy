package de.elementEvents.tema.user

import org.joda.time.DateTime;

class TravelOptions {
	
	String arrivalDate
	String arrivalTime
	String arrivalStation
	String arrivalAirport
	String arrivalAirline
	String licencePlate
	String departureDate
	String departureTime
	String departureStation
	String departureAirport
	String departureAirline
	String selectedTravelOption

    static constraints = {
		arrivalDate blank: true, nullable: true
		arrivalTime blank: true, nullable: true
		arrivalStation blank: true, nullable: true
		arrivalAirport blank: true, nullable: true
		arrivalAirline blank: true, nullable: true
		licencePlate blank: true, nullable: true
		departureDate blank: true, nullable: true
		departureTime blank: true, nullable: true
		departureStation blank: true, nullable: true
		departureAirport blank: true, nullable: true
		departureAirline blank: true, nullable: true
		selectedTravelOption blank: true, nullable: true
        
    }
    
    public getDeparturePlace() {
        departureStation ? departureStation : departureAirport
    }
    
    public getArrivalPlace() {
        arrivalStation ? arrivalStation : arrivalAirport ? arrivalAirport : licencePlate
    }
	
	static belongsTo = [user: User]
}
