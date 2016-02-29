
<%@ page import="de.elementEvents.tema.subscription.Subscription"%>
<html>
    <head>
        <meta name="layout" content="registrationbootstrap">
<g:set var="entityName"
	value="${message(code: 'registration.label', default: 'Registration')}" />
<title><g:message code="registration.default.label" /></title>

<r:require module="registration-angular-scaffolding-a" />
</head>
<body data-base-url="${createLink(uri: '/registration/')}">
	<div class="header">
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="logo" class="span12">
					<img src="${ g.resource( dir:'images', file:'VWN.png' ) }"
						alt="Volkswagen Nutzfahrzeuge" height="108" width="118">
				</div>
			</div>
		</div>
		<div class="container-fluid"></div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="offset1 span10 gyteaser"></div>
		</div>
		<div class="row-fluid">
			<div class="span8 offset2">
				<div class="span11 offset1">
					<div class="accordion" id="accordion2">
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseOne"> Wie sage ich
									bei einer Veranstaltung ab? </a>
							</div>
							<div id="collapseOne" class="accordion-body collapse in">
								<div class="accordion-inner">Dr&uuml;cken Sie einfach nach dem Login mit Ihrem pers&ouml;nlichen Code die &#x201e;Absage&#x201c; Schaltfl&auml;che.</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseTwo"> Ich habe
									meine Anmeldung unterbrochen. Wie geht es weiter? </a>
							</div>
							<div id="collapseTwo" class="accordion-body collapse in">
								<div class="accordion-inner">
								Sie k&ouml;nnen sich jederzeit mit Ihrem pers&ouml;nlichen Code wieder einloggen und die Anmeldung fortsetzen. 
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseThree"> Meine
									Daten sind leider nicht korrekt. Wie kann ich diese
									&auml;ndern? </a>
							</div>
							<div id="collapseThree" class="accordion-body collapse in">
								<div class="accordion-inner">
								Sollten Ihre Daten aus verschiedensten Gr&uuml;nden, wie zum Beispiel kurzfristiger &Auml;nderungen, nicht korrekt sein, loggen Sie sich bitte mit Ihrem pers&ouml;nlichen Code ein und &auml;ndern Sie Ihre Daten ganz einfach ab.
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseFour"> Ich
									m&ouml;chte einen Vertreter schicken. Was ist zu tun? </a>
							</div>
							<div id="collapseFour" class="accordion-body collapse in">
								<div class="accordion-inner">
								Dr&uuml;cken Sie einfach nach dem Login mit Ihrem pers&ouml;nlichen Code die &#x201e;Vertretung schicken&#x201c; Schaltfl&auml;che und geben Sie in Folge dessen die pers&ouml;nlichen Daten Ihres Vertreters ein. Ihre Vertretung erh&auml;lt daraufhin eine Einladung via E-Mail mit einem pers&ouml;nlichen Login-Code.
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseFive"> Mein
									Vertreter m&ouml;chte einen Vertreter schicken. Kann er das
									tun? </a>
							</div>
							<div id="collapseFive" class="accordion-body collapse in">
								<div class="accordion-inner">Einem Vertreter ist es nicht mehr m&ouml;glich eine weitere Vertretung anzumelden.</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseSix"> Ich muss
									leider wieder absagen, hatte aber bereits zugesagt. Was ist zu
									tun? </a>
							</div>
							<div id="collapseSix" class="accordion-body collapse in">
								<div class="accordion-inner">
								Sie k&ouml;nnen sich bis 14 Tage vor Veranstaltungsbeginn, mit Ihrem pers&ouml;nlichen Code jederzeit erneut einloggen und Ihre Teilnahme an der Veranstaltung absagen. Sollte Ihre Absage sich innerhalb des oben genannten Zeitraums bewegen, bitten wir Sie sich kurz pers&ouml;nlich bei unserem Organisationsteam unter 08031/9010866 abzumelden.
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseSeven"> Ich
									m&ouml;chte jetzt doch teilnehmen, hatte meine Teilnahme aber
									bereits abgesagt. Kann ich das noch r&uuml;ckg&auml;ngig
									machen? </a>
							</div>
							<div id="collapseSeven" class="accordion-body collapse in">
								<div class="accordion-inner">
								Bitte kontaktieren Sie in diesem Fall unser Organisationsteam unter 08031/9010866. Hier erhalten Sie einen neuen Login-Code um sich erneut anmelden zu k&ouml;nnen.
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseEight">
						Wozu ben&ouml;tigen Sie meine E-Mail Adresse?
						</a>
							</div>
							<div id="collapseEight" class="accordion-body collapse in">
								<div class="accordion-inner">
									Sie erhalten in Folge Ihrer Anmeldungen alle relevanten Details zur Veranstaltung via E-Mail. Ihre E-Mail Adresse wird nach der Veranstaltung umgehend aus unserem System gel&ouml;scht und nicht mehr verwendet.
								</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseNine">
									Was passiert mit meiner Anmeldung?
									</a>
							</div>
							<div id="collapseNine" class="accordion-body collapse in">
								<div class="accordion-inner">Ihre Anmeldung wird systemseitig verarbeitet und Ihre pers&ouml;nlichen Daten in Folge dessen umgehend gel&ouml;scht. Ihre Daten werden selbstverst&auml;ndlich streng vertraulich behandelt und nur im Rahmen der Serviceleiterkonferenz genutzt.</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseTen">
									Was bedeutet die rote Umrandung einiger Felder, die ich erhalte, wenn ich die Seite, auf der ich mich gerade befinde, &uuml;ber die &#x201e;Weiter&#x201c;- Schaltfl&auml;che verlassen will?
									</a>
							</div>
							<div id="collapseTen" class="accordion-body collapse in">
								<div class="accordion-inner">Die rot umrandeten Felder sind Pflichtfelder, die im Rahmen Ihrer Anmeldung, ausgef&uuml;llt werden m&uuml;ssen. Ohne die Eingabe dieser Daten ist eine erfolgreiche Anmeldung nicht m&ouml;glich.</div>
							</div>
						</div>

						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseEleven">
									Wie ist der Dresscode auf der Konferenz?
									 </a>
							</div>
							<div id="collapseEleven" class="accordion-body collapse in">
								<div class="accordion-inner">Business wear</div>
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseTwelve"> 
									Ich habe eine Frage, auf die ich in Ihren FAQ&#x00b4;s keine Antwort finde. Wie bekomme ich Hilfe?
								</a>
							</div>
							<div id="collapseTwelve" class="accordion-body collapse in">
								<div class="accordion-inner">Bitte kontaktieren Sie in diesem Fall unser Organisationsteam unter der Telefonnummer 08031/9010866 oder schreiben Sie uns eine E-Mail an  support@vwn.serviceleiterkonferenz.de</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	

</body>
</html>
