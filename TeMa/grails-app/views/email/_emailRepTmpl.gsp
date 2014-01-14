<%@page import="org.joda.time.LocalDateTime;de.elementEvents.tema.user.Salutation" %>
<body style="font-family:'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif">
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
<p>
das Gesch&auml;ftsjahr 2013 war von einigen Herausforderungen f&uuml;r den Nutzfahrzeuge Service gepr&auml;gt. Gemeinsam mit Ihnen m&ouml;chten wir das vergangene Jahr Revue passieren lassen und Ihnen einen Ausblick auf 2014 geben.
</p>
<p>
Hierzu laden wir Sie herzlich zur
</p>
<p>
Volkswagen Nutzfahrzeuge Serviceleiter Konferenz 2014<br>
am <joda:format value="${meeting.start}" pattern="dd.MM.yyyy"/> um 09:30 Uhr<br>
im ${meeting_i18n.subtitle} ein.<br>
Konferenzbeginn ist um 10:00 Uhr.
</p>
<p>
Im Rahmen der Konferenz gehen wir besonders auf die geplanten Ma&szlig;nahmen und Ziele f&uuml;r das Servicejahr 2014 ein. Dar&uuml;ber hinaus pr&auml;sentieren wir Ihnen relevante Neuigkeiten zu den Themenbereichen After Sales Qualit&auml;t und Prozesse, Technik, Kundenzufriedenheit sowie Original Teile und Service Vermarktung. 
</p>
<p>
Wie in den vergangenen Jahren haben Sie wieder die M&ouml;glichkeit, auf einem Marktplatz mit unseren Experten direkt in den Dialog zu treten und zu diskutieren.
</p>
<p>
Bitte beachten Sie, dass diese Einladung unter dem Vorbehalt der Genehmigung durch Ihren Arbeitgeber ausgesprochen wird. Tragen Sie bitte Sorge daf&uuml;r, dass Sie die notwendige Genehmigung entsprechend der in Ihrem Hause geltenden Regularien einholen.
</p>
<p>
Bitte melden Sie sich unter folgendem Link bis zum <joda:format value="${meeting.start.minusDays(7)}" pattern="dd.MM.yyyy"/> verbindlich an oder ab. Sie haben auch die M&ouml;glichkeit einen Vertreter zu schicken.
</p>
<p>  [Link] </p>
<p>
Ihr pers&ouml;nlicher Zugangscode lautet: ${participant.loginToken}
</p>
<p>
F&uuml;r R&uuml;ckfragen zu Ihrer Anmeldung steht Ihnen das Organisationsteam unter slk@vwn-veranstaltung.de zur Verf&uuml;gung.
</p>
<p>
Weiterf&uuml;hrende Informationen zur Konferenz und eine Best&auml;tigung per E-Mail erhalten Sie im Anschluss an Ihre Anmeldung. 
</p>



	

<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr VW Nutzfahrzeuge Organisationteam
</p>
<p>
fon. +49.8031.9010.860<br>
info@serviceleiterkonferenz.de
</p>

</body>