
<%@ page import="de.elementEvents.tema.subscription.Subscription" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="registrationbootstrap">
        <g:set var="entityName" value="${message(code: 'subscription.label', default: 'Subscription')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <r:require module="registration-angular-scaffolding"/>
    </head>
    <body data-ng-app="scaffolding" data-base-url="${createLink(uri: '/registration/')}">
        <div class="header">
			<div class="header-inner-wrapper clearfix">
				<div id="logo">
					<a href="/de_de/index.jsp">
						<img src="${ g.resource( dir:'images', file:'gy-print-logo_tcm2141-136421.png' ) }" alt="" height="58" width="178" class="print">
						<img src="${ g.resource( dir:'images', file:'goodyear-logo_tcm2141-114649.png' ) }" alt="Goodyear Safety together" width="178" height="57">
					</a>
				</div>
			</div>
		</div>
        <div class="container" role="main" data-ng-view>
        
        </div>
    </body>
</html>
