<%@page import="de.elementEvents.tema.user.Salutation" %>
<body style="font-family:'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif">
<div style="max-width: 600px">
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>

<br>
<p style="text-align: justify;">
vielen Dank f&uuml;r die erfolgreiche Anmeldung zur Serviceleiterkonferenz 2014 von Volkswagen Nutzfahrzeuge.
</p>
<div>
<p style="text-align: justify;">
Im Anhang finden Sie noch einmal eine &Uuml;bersicht Ihrer relevanten Anmeldedaten als PDF.
Sollten sich bez&uuml;glich Ihrer Anmeldung kurzfristig &Auml;nderungen ergeben, k&ouml;nnen Sie sich jederzeit unter folgendem Link: Domainname mit ihrem Code: ${participant.loginToken} einloggen.
F&uuml;r Fragen steht Ihnen jederzeit unser Organisationsteam unter der Hotline 08031/9010866 zur Verf&uuml;gung. 
</p>
<p style="text-align: center;">
Volkswagen Nutzfahrzeuge Serviceleiter Konferenz 2014<br/>
am <joda:format value="${meeting.start}" pattern="dd.MM.yyyy"/> um 09:30 Uhr<br/>
im ${meeting_i18n.subtitle} ein.<br/>
Konferenzbeginn ist um 10:00 Uhr.
</p>

<div>
<p>
Bitte informieren Sie uns umgehend, sollten Sie die Teilnahme an der
Veranstaltung komplett stornieren m&uuml;ssen.
Eine &Auml;nderung Ihrer pers&ouml;nlichen Daten ist bis zum
<joda:format value="${meeting.start.minusDays(14)}" pattern="dd.MM.yyyy"/> m&ouml;glich.
</p>
<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr Serviceleiterkonferenz 2014 Organsiationteam.
</p>
<p>
fon. +49.8031.9010.860<br>
support@vwn.serviceleiterkonferenz.de
</p>
</div>
	
</div>
</div>
</body>