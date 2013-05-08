<%@page import="de.elementEvents.tema.user.Salutation" %>

<body>
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
<p>
<br><br><span>wir freuen uns sehr, Sie zur <b>&bdquo;GOODYEAR@PORSCHE LEIPZIG 2013&rdquo;</b> begr&uuml;&szlig;en zu d&uuml;rfen. Nachfolgend erhalten Sie eine Zusammenfassung Ihrer Anmeldedaten.</span>
</p>
<p>

	<h4>Teilnehmerdaten</h4>
	<ul>
		<li>
			${participant.firstname}
		</li>
		<li>
			${participant.lastname}
		</li>
		<li>
			${participant.street}
		</li>
		<li>
			${participant.plz} ${participant.city}
		</li>
	</ul>
</p>

<h4>Anreise</h4>
<ul>
<g:if test="${participant.travelOptions.arrivalDate}"><li>Datum: ${fieldValue(bean: participant, field: "travelOptions.arrivalDate")}</li></g:if>
<g:if test="${participant.travelOptions.licencePlate}"><li>Kennzeichen: ${fieldValue(bean: participant, field: "travelOptions.licencePlate")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalStation}"><li>Bahnhof: ${fieldValue(bean: participant, field: "travelOptions.arrivalStation")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalAirport}"><li>Flughafen: ${fieldValue(bean: participant, field: "travelOptions.arrivalAirport")}</li></g:if>
<g:if test="${participant.travelOptions.arrivalAirline}"><li>Fluglinie: ${fieldValue(bean: participant, field: "travelOptions.arrivalAirline")}</li></g:if>
</ul>

<h4>Abreise</h4>
<ul>
<g:if test="${participant.travelOptions.departureDate}"><li>Datum: ${fieldValue(bean: participant, field: "travelOptions.departureDate")}</li></g:if>
<g:if test="${participant.travelOptions.departureStation}"><li>Bahnhof: ${fieldValue(bean: participant, field: "travelOptions.departureStation")}</li></g:if>
<g:if test="${participant.travelOptions.departureAirport}"><li>Flughafen: ${fieldValue(bean: participant, field: "travelOptions.departureAirport")}</li></g:if>
<g:if test="${participant.travelOptions.departureAirline}"><li>Fluglinie: ${fieldValue(bean: participant, field: "travelOptions.departureAirline")}</li></g:if>
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