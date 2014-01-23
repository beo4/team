<%@page import="org.joda.time.LocalDateTime;de.elementEvents.tema.user.Salutation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <html>
<head>
<title>${participant.firstname} ${participant.lastname}</title>
<style  type="text/css">
  @font-face{
	font-family:"VWD4 Book";
	  src:url("${serverUrl}/css/VWHeadlineOT-Book.eot");
	  src:url("${serverUrl}/css/VWHeadlineOT-Book.eot?#iefix") format("embedded-opentype"),
	  url("${serverUrl}/css/VWHeadlineOT-Book.woff") format("woff"),
	  url("${serverUrl}/css/VWHeadlineOT-Book.svg#webfontX0LFdQBr") format("svg"),
	  url("${serverUrl}/css/VWHeadlineOT-Book.ttf") format("truetype");
	}
	@font-face{
	font-family:"VWD4 Semibold";
	  src:url("${serverUrl}/css/VWHeadlineOT-Semibold.eot");
	  src:url("${serverUrl}/css/VWHeadlineOT-Semibold.eot?#iefix") format("embedded-opentype"),
	  url("${serverUrl}/css/VWHeadlineOT-Semibold.woff") format("woff"),
	  url("${serverUrl}/css/VWHeadlineOT-Semibold.svg#webfontfbDJZY22") format("svg"),
	  url("${serverUrl}/css/VWHeadlineOT-Semibold.ttf") format("truetype");
	}
	@font-face{
	font-family:"VWD4 Black";
	  src:url("${serverUrl}/css/VWHeadlineOT-Black.eot");
	  src:url("${serverUrl}/css/VWHeadlineOT-Black.eot?#iefix") format("embedded-opentype"),
	  url("${serverUrl}/css/VWHeadlineOT-Black.woff") format("woff"),
	  url("${serverUrl}/css/VWHeadlineOT-Black.svg#webfontfbDJZY22") format("svg"),
	  url("${serverUrl}/css/VWHeadlineOT-Black.ttf") format("truetype");
	}
  @page {
    size: 210mm 297mm;
  }
  
  table {
  	width: 100%
  }
	
</style>
</head>
<body style="font-family: 'VWD4 Black', 'VWD4 Book', Arial, 'Lucida Grande', sans-serif">
<div style="width: 100%; clear:both;">
	<div style="float:left;">
		<img src="${serverUrl}/images/VWN.png" alt="Volkswagen Nutzfahrzeuge" height="108" width="118"/>
	</div>
	<div style="float:right;">
		<img src="${serverUrl}/images/VWN-SLK2014-Keyvisual-FINAL-460.jpg" height="108" alt="Volkswagen Nutzfahrzeuge" />
	</div>
</div>
<p>&nbsp;</p>
<div style="width: 100%; clear:both;">
<p>
<g:if test="${participant.salutation == Salutation.MR }"> 
<div style="float:left">Sehr geehrter Herr ${participant.firstname} ${participant.lastname},</div>
</g:if>
<g:if test="${participant.salutation == Salutation.MS }"> 
<div style="float:left">Sehr geehrte Frau ${participant.firstname} ${participant.lastname},</div>
</g:if>
</p>
<p>&nbsp;</p>
<p style="text-align: justify;">
vielen Dank f&uuml;r die erfolgreiche Anmeldung zur Serviceleiter Konferenz 2014 von Volkswagen Nutzfahrzeuge.
</p>
<div class="container-fluid">
			Zum Abgleich noch einmal Ihre angegebenen Daten:
			
					<h2>
						Angaben zur Person
					</h2>
					<table>
					<tr>
							<td width="30%">
								Titel (akademisch)
							</td>
							<td>
								${participant.title}
							</td>
					</tr>

					<tr>
							<td width="30%">
								Vorname
							</td>
							<td>
								${participant.firstname}
							</td>
					</tr>
					<tr>
							<td width="30%">
							  Nachname
							</td>
							<td>
								${participant.lastname}
							</td>
					</tr>

					</table>
						<h2>Angaben zur Firma</h2>
					<table>
					<tr>
							<td width="30%">
									Strasse, Hausnummer
							</td>
							<td>
								${participant.companystreet}
							</td>
					</tr>
					<tr>
							<td>
								PLZ
							</td>
							<td>
								${participant.companyplz}
							</td>
					</tr>
					<tr>
							<td>
							Ort
</td>
							<td>
									${participant.companycity}
							</td>
					</tr>
					<tr>
							<td>
								Betrieb
							</td>
							<td>
										${participant.company}
							</td>
					</tr>
					<tr>
							<td>
								Betriebsnummer
							</td>
							<td>
								${participant.companynr}
							</td>
					</tr>
					<tr>
							<td>
								Position
							</td>
							<td>
								${participant.position}
							</td>
					</tr>
					</table>
					<h2>
						Kontakt
					</h2>			
			<table>
					<tr>
							<td width="30%">
								Telefon
							</td>
							<td>
								${participant.phone}
							</td>
					</tr>
					
					<tr>
							<td>
								Fax
							</td>
							<td>
								${participant.fax}
							</td>
					</tr>

					<tr>
							<td>
								E-Mail
							</td>
							<td>
								${participant.email}
							</td>
					</tr>
		</table>
					<h2>
						Marktplatzthemen
					</h2>
						
					<div class="row-fluid">
							<div class="span10 marketplaceList">
<g:if test="${participant.marketplaceOptions.aufbauherstellermanagement && participant.marketplaceOptions.aufbauherstellermanagement != null}">Aufbauherstellermanagement, </g:if>
<g:if test="${participant.marketplaceOptions.spezialfahrzeuge && participant.marketplaceOptions.spezialfahrzeuge != null}">Business Unit Spezialfahrzeuge, </g:if>
<g:if test="${participant.marketplaceOptions.careport && participant.marketplaceOptions.careport != null}">CarePort, </g:if>
<g:if test="${participant.marketplaceOptions.direktexpress && participant.marketplaceOptions.direktexpress != null}">Direkt Express, </g:if>
<g:if test="${participant.marketplaceOptions.euromobil && participant.marketplaceOptions.euromobil != null}">Euromobil, </g:if>
<g:if test="${participant.marketplaceOptions.feldbeobachtung && participant.marketplaceOptions.feldbeobachtung != null}">Feldbeobachtung, </g:if>
<g:if test="${participant.marketplaceOptions.gewahrleistung && participant.marketplaceOptions.gewahrleistung != null}">Gew&auml;hrleistung, </g:if>
<g:if test="${participant.marketplaceOptions.grosskundenbetreuung && participant.marketplaceOptions.grosskundenbetreuung != null}">Gro&szlig;kundenbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.ihvpost && participant.marketplaceOptions.ihvpost != null}">IHV Post, </g:if>
<g:if test="${participant.marketplaceOptions.inspektion && participant.marketplaceOptions.inspektion != null}">Inspektion &amp; Wartung, </g:if>
<g:if test="${participant.marketplaceOptions.kbaservice && participant.marketplaceOptions.kbaservice != null}">Kundenbindungsanalyse Service, </g:if>
<g:if test="${participant.marketplaceOptions.marktchancen && participant.marketplaceOptions.marktchancen != null}">Marktchancen Web Reporting, </g:if>
<g:if test="${participant.marketplaceOptions.onlinesz && participant.marketplaceOptions.onlinesz != null}">Onlineauftritt Service und Zubeh&ouml;r, </g:if>
<g:if test="${participant.marketplaceOptions.originalteile && participant.marketplaceOptions.originalteile != null}">Original Teile, </g:if>
<g:if test="${participant.marketplaceOptions.originalteilelogistik && participant.marketplaceOptions.originalteilelogistik != null}">Original Teile Logistik GmbH, </g:if>
<g:if test="${participant.marketplaceOptions.partnerbetreuung && participant.marketplaceOptions.partnerbetreuung != null}">Partnerbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.planungimah && participant.marketplaceOptions.planungimah != null}">Planungssysteme im Autohaus, </g:if>
<g:if test="${participant.marketplaceOptions.produktbetreuung && participant.marketplaceOptions.produktbetreuung != null}">Produktbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.servicemarketing && participant.marketplaceOptions.servicemarketing != null}">Service Marketing, </g:if>
<g:if test="${participant.marketplaceOptions.servicequalifizierungen && participant.marketplaceOptions.servicequalifizierungen != null}">Service Qualifizierungen, </g:if>
<g:if test="${participant.marketplaceOptions.servicestandards && participant.marketplaceOptions.servicestandards != null}">Service Standards, </g:if>
<g:if test="${participant.marketplaceOptions.skpcommerce && participant.marketplaceOptions.skpcommerce != null}">SKPcommerce, </g:if>
<g:if test="${participant.marketplaceOptions.taxi && participant.marketplaceOptions.taxi != null}">Taxi, </g:if>
<g:if test="${participant.marketplaceOptions.unfallverhutung && participant.marketplaceOptions.unfallverhutung != null}">Unfallverh&uuml;tungsvorschrift, </g:if>
<g:if test="${participant.marketplaceOptions.classicparts && participant.marketplaceOptions.classicparts != null}">Volkswagen Classic Parts, </g:if>
<g:if test="${participant.marketplaceOptions.leasing && participant.marketplaceOptions.leasing != null}">Volkswagen Leasing, </g:if>
<g:if test="${participant.marketplaceOptions.nutzfahrzeuge && participant.marketplaceOptions.nutzfahrzeuge != null}">Volkswagen Nutzfahrzeuge, </g:if>
<g:if test="${participant.marketplaceOptions.oldtimer && participant.marketplaceOptions.oldtimer != null}">Oldtimer, </g:if>
<g:if test="${participant.marketplaceOptions.serviceapp && participant.marketplaceOptions.serviceapp != null}">Volkswagen Service APP, </g:if>
<g:if test="${participant.marketplaceOptions.merchandising && participant.marketplaceOptions.merchandising != null}">Volkswagen Zubeh&ouml;r &amp; Lifestyle, </g:if>
<g:if test="${participant.marketplaceOptions.other && participant.marketplaceOptions.other != null}">${participant.marketplaceOptions.other} </g:if>
							
							
							</div>
					</div>

</div>
<table style="width: 100%">
	<tr>
		<td>
		i.V.					
		</td>
		<td>
		i.V.
		</td>
	</tr>
	<tr>
		<td>
		M. Rampas					
		</td>
		<td>
		J. Peschke
		</td>
	</tr>
	<tr>
		<td>
		Leiterin Service Deutschland					
		</td>
		<td>
		Leiter Service Marktbetreuung 
		</td>
	</tr>
</table>
<p>
F&uuml;r R&uuml;ckfragen zu Ihrer Anmeldung steht Ihnen das Organisationsteam unter support@vwn.serviceleiterkonferenz.de zur Verf&uuml;gung.
</p>

<p>
Mit freundlichen Gr&uuml;&szlig;en<br/>
Ihr Serviceleiter Konferenz 2014 Organsiationteam.<br/>
fon. +49.8031.9010.860<br/>
support@vwn.serviceleiterkonferenz.de
</p>
</div>
</body>
</html>