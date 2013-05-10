<%@page import="de.elementEvents.tema.user.Salutation" %>

<body style="font-family:'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif">
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
<p>
<br><span>wir freuen uns sehr, Sie zur <b>&bdquo;GOODYEAR@PORSCHE LEIPZIG 2013&rdquo;</b> begr&uuml;&szlig;en zu d&uuml;rfen. Nachfolgend erhalten Sie eine Zusammenfassung Ihrer Anmeldedaten.</span>
</p>
<p>

	<h4>Teilnehmerdaten</h4>
	<ul>
		<li>
			${participant.salutation.value} ${participant.title} ${participant.firstname} ${participant.lastname}
		</li>
		<li>
			${participant.street}
		</li>
		<li>
			${participant.plz} ${participant.city}
		</li>
		<g:if test="${participant.mobile}"><li>(Mobil)Telefon: ${fieldValue(bean: participant, field: "mobile")}</li></g:if>
		<g:if test="${participant.company}"><li>Firma: ${fieldValue(bean: participant, field: "company")}</li></g:if>
		<g:if test="${participant.companyadd}"><li>Firmenzusatz: ${fieldValue(bean: participant, field: "companyadd")}</li></g:if>
		<g:if test="${participant.position}"><li>Position: ${fieldValue(bean: participant, field: "position")}</li></g:if>
		<g:if test="${participant.companystreet}"><li>Strasse, Hausnummer: ${fieldValue(bean: participant, field: "companystreet")}</li></g:if>
		<g:if test="${participant.companyplz}"><li>Ort: ${fieldValue(bean: participant, field: "companyplz")}  ${fieldValue(bean: participant, field: "companycity")}</li></g:if>
		<g:if test="${participant.email}"><li>E-Mail: ${fieldValue(bean: participant, field: "email")}</li></g:if>
		
	</ul>
</p>

<h4>Anreise</h4>
<ul>
<g:if test="${participant.travelOptions.arrivalDate}"><li>Datum: ${fieldValue(bean: participant, field: "travelOptions.arrivalDate")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalTime}"><li>Uhrzeit: ${fieldValue(bean: participant, field: "travelOptions.arrivalTime")}</li></g:if>
<g:if test="${participant.travelOptions.licencePlate && (participant.travelOptions.selectedTravelOption == 'PKW')}"><li>Kennzeichen: ${fieldValue(bean: participant, field: "travelOptions.licencePlate")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalStation && (participant.travelOptions.selectedTravelOption == 'Bahn')}"><li>Bahnhof: ${fieldValue(bean: participant, field: "travelOptions.arrivalStation")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalAirport && (participant.travelOptions.selectedTravelOption == 'Flugzeug')}"><li>Flughafen: ${fieldValue(bean: participant, field: "travelOptions.arrivalAirport")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalAirline && (participant.travelOptions.selectedTravelOption == 'Flugzeug')}"><li>Fluglinie: ${fieldValue(bean: participant, field: "travelOptions.arrivalAirline")}</li></g:if>
</ul>

<h4>Abreise</h4>
<ul>
<g:if test="${participant.travelOptions.departureDate}"><li>Datum: ${fieldValue(bean: participant, field: "travelOptions.departureDate")}</li></g:if>
<g:if test="${participant.travelOptions.departureTime && (participant.travelOptions.selectedTravelOption != 'PKW')}"><li>Uhrzeit: ${fieldValue(bean: participant, field: "travelOptions.departureTime")}</li></g:if>
<g:if test="${participant.travelOptions.departureStation && (participant.travelOptions.selectedTravelOption == 'Bahn')}"><li>Bahnhof: ${fieldValue(bean: participant, field: "travelOptions.departureStation")}</li></g:if>
<g:if test="${participant.travelOptions.departureAirport && (participant.travelOptions.selectedTravelOption == 'Flugzeug')}"><li>Flughafen: ${fieldValue(bean: participant, field: "travelOptions.departureAirport")}</li></g:if>
<g:if test="${participant.travelOptions.departureAirline && (participant.travelOptions.selectedTravelOption == 'Flugzeug')}"><li>Fluglinie: ${fieldValue(bean: participant, field: "travelOptions.departureAirline")}</li></g:if>
</ul>

<h4>Spezielle Anforderungen</h4>
<ul>
<g:if test="${participant.otherOptions.vegatarian}"><li>${fieldValue(bean: participant, field: "otherOptions.vegatarian")}</li></g:if>
<g:if test="${participant.otherOptions.vegan}"><li>Ich bin Veganer</li></g:if>
<g:if test="${participant.otherOptions.allergy}"><li>Allergien: ${fieldValue(bean: participant, field: "otherOptions.allergy")}</li></g:if>
<g:if test="${participant.otherOptions.wishes}"><li>Sonderw&uuml;nsche: ${fieldValue(bean: participant, field: "otherOptions.wishes")}</li></g:if>
</ul>

<div>

<div>
<p>
Bitte informieren Sie uns umgehend, sollten Sie die Teilnahme an der
Veranstaltung komplett stornieren m&uuml;ssen.
Eine &Auml;nderung Ihrer pers&ouml;nlichen Daten und/ oder Reisedaten ist bis zum
14.06.2013 m&ouml;glich.
</p>
<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr Goodyear Organisation-Team
</p>
<p>
fon. +49.8031.9010.860<br>
support@goodyear-at-porsche.de
</p>
</div>
	
</div>
</body>