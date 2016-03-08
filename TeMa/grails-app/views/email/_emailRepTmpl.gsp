<%@page import="org.joda.time.LocalDateTime;de.elementEvents.tema.user.Salutation" %>
<html>
<head>
<style type="text/css">
@import url(http://www.volkswagen-nutzfahrzeuge.de/de.font_config.css);
</style>
</head>
<body style="font-family: 'VWD4 Black', 'VWD4 Book' , Arial, 'Lucida Grande', sans-serif">
<div style="max-width: 800px">
<div style="width: 100%; clear:both;">
	<div style="float:left;">
		<img src="cid:VWN" alt="Volkswagen Nutzfahrzeuge" height="108" width="118"/>
	</div>
	<div style="float:right;">
		<img src="cid:VWN-SLK2014" height="107" width="376" alt="Volkswagen Nutzfahrzeuge" />
	</div>
</div>
<p style="clear:both;"></p>
<p>
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
</p>
<p>
<g:message encodeAs="HTML" code="registration.mail.rep.open"/>
</p>
<p style="text-align: center;"><strong>
Volkswagen Nutzfahrzeuge Serviceleiter Konferenz 2016<br>
am <joda:format value="${meeting.start}" pattern="dd.MM.yyyy"/> um 09:30 Uhr<br>
<g:if test="${meeting.id == 18}">in die</g:if><g:if test="${meeting.id != 18}">im</g:if> ${meeting_i18n.description} ein.<br>
Konferenzbeginn ist um 10:00 Uhr.</strong>
</p>
<p style="text-align: justify;">
<g:message encodeAs="HTML" code="registration.mail.rep.konferenz"/> 
</p>
<p style="text-align: justify;">
<g:message encodeAs="HTML" code="registration.mail.rep.marketplace" />
</p>
<p style="text-align: justify;">
<g:message encodeAs="HTML" code="registration.mail.rep.policy" />
</p>
<p  style="text-align: justify;">
<g:message encodeAs="HTML" code="registration.mail.rep.anmelden" /> <joda:format value="${meeting.start.minusDays(21)}" pattern="dd.MM.yyyy"/> <g:message encodeAs="HTML" code="registration.mail.rep.anmelden2" />
</p>
<p>  <g:link url="https://vwn.serviceleiterkonferenz.de/#/registration">https://vwn.serviceleiterkonferenz.de</g:link> </p>
<p>
Ihr pers&ouml;nlicher Zugangscode lautet: <strong>${participant.loginToken}</strong>
</p>
<table style="width: 100%">
	<tr>
		<td>
		ppa.					
		</td>
		<td>
		i.V.
		</td>
	</tr>
	<tr>
		<td>
		Carsten Sass			
		</td>
		<td>
		Marion Rampas
		</td>
	</tr>
	<tr>
		<td>
		Leiter Vertrieb Deutschland			
		</td>
		<td>
		Leiterin Service Deutschland 
		</td>
	</tr>
</table>
<p>
F&uuml;r R&uuml;ckfragen zu Ihrer Anmeldung steht Ihnen das Organisationsteam unter der E-Mail support@vwn.serviceleiterkonferenz.de oder telefonisch unter 08031/9010866 zur Verf&uuml;gung.
</p>
<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr Serviceleiter Konferenz 2016 Organisationsteam.
</p>
</div>
</body>
</html>