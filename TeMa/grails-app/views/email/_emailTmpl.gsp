<%@page import="de.elementEvents.tema.user.Salutation" %>
<html>
<head>
<style type="text/css">
@import url(http://www.volkswagen-nutzfahrzeuge.de/de.font_config.css);
</style>
</head>
<body style="font-family: 'VWD4 Black', 'VWD4 Book', Arial, 'Lucida Grande', sans-serif">
<div style="max-width: 800px">
<div style="width: 100%; clear:both;">
	<div style="float:left;">
		<img src="cid:VWN" alt="Volkswagen Nutzfahrzeuge" height="108" width="118"/>
	</div>
	<div style="float:right;">
		<img src="cid:VWN-SLK2014" height="174" width="389" alt="Volkswagen Nutzfahrzeuge" />
	</div>
</div>
<p style="clear:both;"></p>
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>

<br>
<p style="text-align: justify;">
vielen Dank f&uuml;r die erfolgreiche Anmeldung zur Serviceleiter Konferenz 2014 von Volkswagen Nutzfahrzeuge.
</p>
<div>
<p style="text-align: justify;">
Im Anhang finden Sie noch einmal eine &Uuml;bersicht Ihrer relevanten Anmeldedaten als PDF.
Sollten sich bez&uuml;glich Ihrer Anmeldung kurzfristig &Auml;nderungen ergeben, k&ouml;nnen Sie sich jederzeit unter folgendem Link: </p>
<p><g:link url="https://vwn.serviceleiterkonferenz.de/#/registration">https://vwn.serviceleiterkonferenz.de</g:link></p>
<p>mit ihrem Code:</p>
<p><strong> ${participant.loginToken} </strong></p><p>einloggen.</p>
<p>
F&uuml;r R&uuml;ckfragen zu Ihrer Anmeldung steht Ihnen das Organisationsteam unter der E-Mail support@vwn.serviceleiterkonferenz.de oder telefonisch unter 08031/9010866 zur Verf&uuml;gung.
</p>
<div>
<p>

Eine &Auml;nderung Ihrer pers&ouml;nlichen Daten ist bis zum
<joda:format value="${meeting.start.minusDays(14)}" pattern="dd.MM.yyyy"/> m&ouml;glich.
</p>
<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr Serviceleiter Konferenz 2014 Organisationsteam.
</p>
</div>
	
</div>
</div>
</body>
</html>