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
  
  body {
	font-family: 'VWD4 Black', 'VWD4 Book', Arial, sans-serif;
	font-size: 12px;
	line-height: 15px;
	color: #333333;
	}
  
  table {
  	width: 100%
  }
  
  h2 {
  	display: block;
	width: 100%;
	padding: 0;
	margin-bottom: 10px;
	font-size: 15px;
	line-height: 20px;
	color: #333333;
	border: 0;
	border-bottom: 1px solid #ff871f;
  }
	
</style>
</head>
<body style="font-family: 'VWD4 Black', 'VWD4 Book', Arial, 'Lucida Grande', sans-serif">
<div style="width: 100%; clear:both;">
	<div style="float:right;">
		<rendering:inlinePng bytes="${vwlogo}" />
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
vielen Dank f&uuml;r die erfolgreiche Anmeldung zur Serviceleiter Konferenz 2015 von Volkswagen Nutzfahrzeuge. Sie wurden f&uuml;r folgende Konferenz angemeldet.
</p>
<p style="text-align: center;"><strong>
Volkswagen Nutzfahrzeuge Serviceleiter Konferenz 2015<br/>
am <joda:format value="${meeting.start}" pattern="dd.MM.yyyy"/> um 09:30 Uhr<br/>
in ${meeting_i18n.subtitle} ein.<br/>
Konferenzbeginn ist um 10:00 Uhr.</strong>
</p>
<div class="container-fluid">
			Zum Abgleich noch einmal Ihre angegebenen Daten:
			
					<h2>
						Angaben zur Person
					</h2>
					<table>

					<tr>
							<td width="30%">
								Vorname:
							</td>
							<td>
								${participant.firstname.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td width="30%">
							  Nachname:
							</td>
							<td>
								${participant.lastname.encodeAsHTML()}
							</td>
					</tr>

					</table>
						<h2>Angaben zur Firma</h2>
					<table>
					<tr>
							<td width="30%">
									Strasse, Hausnummer:
							</td>
							<td>
								${participant.companystreet.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td>
								PLZ:
							</td>
							<td>
								${participant.companyplz.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td>
							Ort:
</td>
							<td>
									${participant.companycity.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td>
								Betrieb:
							</td>
							<td>
										${participant.company.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td>
								Betriebsnummer:
							</td>
							<td>
								${participant.companynr.encodeAsHTML()}
							</td>
					</tr>
					<tr>
							<td>
								Position:
							</td>
							<td>
								<g:if test="${participant.position != 'Sonstiges'}">${participant.position.encodeAsHTML()} </g:if>
								<g:if test="${participant.position == 'Sonstiges'}">${participant.position.encodeAsHTML()} </g:if>
							</td>
					</tr>
					</table>
					<h2>
						Kontakt
					</h2>			
			<table>
					<tr>
							<td width="30%">
								Telefon:
							</td>
							<td>
								${participant.phone.encodeAsHTML()}
							</td>
					</tr>
					
					<tr>
							<td>
								Fax:
							</td>
							<td>
								${participant.fax.encodeAsHTML()}
							</td>
					</tr>

					<tr>
							<td>
								E-Mail:
							</td>
							<td>
								${participant.email.encodeAsHTML()}
							</td>
					</tr>
		</table>
					<h2>
						Servicethemen:
					</h2>
						
					<div class="row-fluid">
							<div class="span10 marketplaceList">
<g:if test="${participant.marketplaceOptions.aufbauherstellermanagement && participant.marketplaceOptions.aufbauherstellermanagement != null}">Aufbauherstellermanagement, </g:if>
<g:if test="${participant.marketplaceOptions.careport && participant.marketplaceOptions.careport != null}">CarePort, </g:if>
<g:if test="${participant.marketplaceOptions.classicparts && participant.marketplaceOptions.classicparts != null}">Classic Parts, </g:if>
<g:if test="${participant.marketplaceOptions.euromobil && participant.marketplaceOptions.euromobil != null}">Euromobil, </g:if>
<g:if test="${participant.marketplaceOptions.gewahrleistung && participant.marketplaceOptions.gewahrleistung != null}">Gew&auml;hrleistung, </g:if>
<g:if test="${participant.marketplaceOptions.grosskundenbetreuung && participant.marketplaceOptions.grosskundenbetreuung != null}">Gro&szlig;kundenbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.heritage && participant.marketplaceOptions.heritage != null}">IT-Systeme im Service, </g:if>
<g:if test="${participant.marketplaceOptions.ihvpost && participant.marketplaceOptions.ihvpost != null}">IHV Post, </g:if>
<g:if test="${participant.marketplaceOptions.kbaservice && participant.marketplaceOptions.kbaservice != null}">Kundenbindungsanalyse Service, </g:if>
<g:if test="${participant.marketplaceOptions.serviceapp && participant.marketplaceOptions.serviceapp != null}">Volkswagen Service APP, </g:if>
<g:if test="${participant.marketplaceOptions.nutzfahrzeugeTechnikCenter && participant.marketplaceOptions.nutzfahrzeugeTechnikCenter != null}">Nutzfahrzeuge Technik Center, </g:if>
<g:if test="${participant.marketplaceOptions.partnerbetreuung && participant.marketplaceOptions.partnerbetreuung != null}">Partnerbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.personalmarketing && participant.marketplaceOptions.personalmarketing != null}">Personalmarketing, </g:if>
<g:if test="${participant.marketplaceOptions.produktbetreuung && participant.marketplaceOptions.produktbetreuung != null}">Produktbetreuung, </g:if>
<g:if test="${participant.marketplaceOptions.servicemarketing && participant.marketplaceOptions.servicemarketing != null}">Service Marketing, </g:if>
<g:if test="${participant.marketplaceOptions.servicequalifizierungen && participant.marketplaceOptions.servicequalifizierungen != null}">Service Qualifizierungen, </g:if>
<g:if test="${participant.marketplaceOptions.servicestandards && participant.marketplaceOptions.servicestandards != null}">Service Standards, </g:if>
<g:if test="${participant.marketplaceOptions.serviceProzesse && participant.marketplaceOptions.serviceProzesse != null}">Service Prozesse, </g:if>
<g:if test="${participant.marketplaceOptions.technikLive && participant.marketplaceOptions.technikLive != null}">Technik &#8222;live&ldquo;, </g:if>
<g:if test="${participant.marketplaceOptions.leasing && participant.marketplaceOptions.leasing != null}">Volkswagen Leasing, </g:if>
<g:if test="${participant.marketplaceOptions.originalteile && participant.marketplaceOptions.originalteile != null}">Original Teile, </g:if>
<g:if test="${participant.marketplaceOptions.merchandising && participant.marketplaceOptions.merchandising != null}">Volkswagen Zubeh&ouml;r &amp; Lifestyle, </g:if>
							
							
							</div>
					</div>

</div>
<p>
F&uuml;r R&uuml;ckfragen zu Ihrer Anmeldung steht Ihnen das Organisationsteam unter der E-Mail support@vwn.serviceleiterkonferenz.de oder telefonisch unter 08031/9010866 zur Verf&uuml;gung.
</p>

<p>
Mit freundlichen Gr&uuml;&szlig;en<br/>
Ihr Serviceleiter Konferenz 2015 Organisationsteam.<br/>
</p>
</div>
<div style="width: 100%; clear:both; margin-top:30px">
	<div style="float:right;">
		<rendering:inlineJpeg bytes="${slklogo}"/>
	</div>
</div>
</body>
</html>