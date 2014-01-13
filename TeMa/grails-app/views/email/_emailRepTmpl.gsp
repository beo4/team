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
			${participant.loginToken}
		</li>
		
		
	</ul>
</p>

<p>
Mit freundlichen Gr&uuml;&szlig;en<br>
Ihr Goodyear Organisation-Team
</p>
<p>
fon. +49.8031.9010.860<br>
support@goodyear-at-porsche.de
</p>

</body>