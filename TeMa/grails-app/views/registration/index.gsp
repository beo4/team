
<%@ page import="de.elementEvents.tema.subscription.Subscription" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="registrationbootstrap">
        <g:set var="entityName" value="${message(code: 'registration.label', default: 'Registration')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <r:require module="registration-angular-scaffolding"/>
    </head>
    <body data-base-url="${createLink(uri: '/registration/')}">
        <div class="header">
        <div class="container">
			<div class="row">
				<div id="logo" class="span12">
					<a href="/de_de/index.jsp">
						<img src="${ g.resource( dir:'images', file:'gy-print-logo_tcm2141-136421.png' ) }" alt="" height="58" width="178" class="print">
						<img src="${ g.resource( dir:'images', file:'goodyear-logo_tcm2141-114649.png' ) }" alt="Goodyear Safety together" width="178" height="57">
					</a>
				</div>
			</div>
			</div>
			<div class="container">
			<div class="navbar">
				<ul class="nav">
					<li data-ng-repeat="step in steps" data-ng-class="{'active': step.active}"><a href="#">{{step.title}}</a></li>
				</ul>
				</div>
			</div>
		</div>
		<div class="container">
		<div class="row">
				<div class="span12 gyteaser">
				<div>
				<h1>GOODYEAR@PORSCHE LEIPZIG 2013.</h1>
				</div>
				<div>
				<h2>Das ultimative Markenerlebnis.</h2>
				</div>
		</div>
		</div>
        <div class="row">
        	<div class="span12 mainContent" role="main" data-ng-view>
        	</div>
        </div>
        </div>

    </body>
</html>
