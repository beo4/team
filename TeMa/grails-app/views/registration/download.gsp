
<%@ page import="de.elementEvents.tema.subscription.Subscription" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="registrationbootstrap">
        <g:set var="entityName" value="${message(code: 'registration.label', default: 'Registration')}" />
        <title><g:message code="registration.default.label"/></title>

        <r:require module="registration-angular-scaffolding"/>
    </head>
    <body data-base-url="${createLink(uri: '/registration/')}">
        <div class="header">
        <div class="container-fluid">
			<div class="row-fluid">
				<div id="logo" class="span12">
						<img src="${ g.resource( dir:'images', file:'VWN.png' ) }" alt="Volkswagen Nutzfahrzeuge" height="108" width="118">
				</div>
			</div>
			</div>
			<div class="container-fluid">
			</div>
		</div>
		<div class="container-fluid">
		<div class="row-fluid">
				<div class="offset1 span10 gyteaser">
				
		</div>
		</div>
        <div class="row-fluid">
        	<div class="span8 offset2" >
        	<div class="span11 offset1 mainContent">
        		<h2>Informationen folgen</h2>
        	</div>
        	</div>
        </div>
        </div>

    </body>
</html>
