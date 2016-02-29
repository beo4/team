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
		<img src="cid:VWN-SLK2014" height="118" width="389" alt="Volkswagen Nutzfahrzeuge" />
	</div>
</div>
<p style="clear:both;"></p>
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
<p>&nbsp;</p>
<p style="text-align: justify;">
das Gesch&auml;ftsjahr 2016 war von einigen Herausforderungen f&uuml;r den Nutzfahrzeuge Service gepr&auml;gt. Gemeinsam mit Ihnen m&ouml;chten wir das vergangene Jahr Revue passieren lassen und Ihnen einen Ausblick auf 2015 geben.
</p>
<p>
Hierzu laden wir Sie herzlich ein: 
</p>
<p style="text-align: center;"><strong>
Volkswagen Nutzfahrzeuge Serviceleiter Konferenz 2016<br>
am <joda:format value="${meeting.start}" pattern="dd.MM.yyyy"/> um 09:30 Uhr<br>
im ${meeting_i18n.description}<br>
Konferenzbeginn ist um 10:00 Uhr.</strong>
</p>
<p style="text-align: justify;">
Im Rahmen der Konferenz gehen wir besonders auf die geplanten Ma&szlig;nahmen und Ziele f&uuml;r das Servicejahr 2016 ein. Dar&uuml;ber hinaus pr&auml;sentieren wir Ihnen relevante Neuigkeiten zu den Themenbereichen After Sales Qualit&auml;t und Prozesse, Technik, Kundenzufriedenheit sowie Original Teile und Service Vermarktung. 
</p>
<p style="text-align: justify;">
Wie in den vergangenen Jahren haben Sie wieder die M&ouml;glichkeit, auf einem Marktplatz mit unseren Experten direkt in den Dialog zu treten und zu diskutieren.
</p>
<p style="text-align: justify;">
Bitte beachten Sie, dass diese Einladung unter dem Vorbehalt der Genehmigung durch Ihren Arbeitgeber ausgesprochen wird. Tragen Sie bitte Sorge daf&uuml;r, dass Sie die notwendige Genehmigung entsprechend der in Ihrem Hause geltenden Regularien einholen.
</p>
<p  style="text-align: justify;">
Bitte melden Sie sich unter folgendem Link bis zum <joda:format value="${meeting.start.minusDays(21)}" pattern="dd.MM.yyyy"/> verbindlich an oder ab. Sie haben auch die M&ouml;glichkeit einen Vertreter zu schicken.
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