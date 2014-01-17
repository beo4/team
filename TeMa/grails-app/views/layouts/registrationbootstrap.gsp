<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<meta charset="utf-8">
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<r:require modules="scaffolding"/> 

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">
		
		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body  data-ng-app="scaffolding" 
		  data-base-url="${pageProperty(name: 'body.data-base-url', default: createLink(action: 'index').replaceAll(/index$/, ''))}"
		  data-app-url="${createLink(uri: '/')}"
		  data-template-url="${pageProperty(name: 'body.data-template-url', default: createLink(uri: "/ng-templates/$controllerName"))}"
		  data-common-template-url="${pageProperty(name: 'body.data-common-template-url', default: createLink(uri: '/ng-templates'))}">

		

		
			<g:layoutBody/>
		<div class="row-fluid">
			<div class="span12">
			</div>
		</div>
		<div class="container-fluid">
		<div class="row-fluid">
				<div class="span8 offset2" >
        	<div class="span11 offset1">
        		Sollten Sie organisatorische oder technische Fragen haben, steht Ihnen Ihr Organisationsteam gerne telefonisch
					 unter der <span style="white-space: nowrap;">Rufnummer 08031 - 90 10 866</span> oder per E-Mail unter 
					<g:link url="mailto:support@vwn.serviceleiterkonferenz.de">support@vwn.serviceleiterkonferenz.de</g:link> zur Verf&uuml;gung.
        	</div>
        	</div>
        	</div>
		</div>
		<div class="container-fluid">
			<hr>

			<footer>
				<div class="pull-right">
					<a href="http://www.volkswagen-nutzfahrzeuge.de/de/tools/navigation/footer/rechtliches/anbieterkennzeichnung.html" target="_blank">Impressum</a>&nbsp;|&nbsp;
					<a href="http://www.volkswagen-nutzfahrzeuge.de/de/tools/navigation/footer/rechtliches.html" target="_blank">Rechtliches</a>&nbsp;|&nbsp;
					<a href="http://www.volkswagen-commercial-vehicles.com/" target="_blank">International</a>&nbsp;|&nbsp;
					<a href="http://www.volkswagen.de/de.html" target="_blank">Volkswagen AG</a>&nbsp;|&nbsp;
					<a href="http://www.volkswagen-nutzfahrzeuge.de/de.html" target="_blank">Volkswagen Nutzfahrzeuge</a>
				</div>
			</footer>
		</div>

		<r:layoutResources/>

	</body>
</html>