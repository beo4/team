
<%@ page import="de.elementEvents.tema.event.Event" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap">
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <r:require module="event-angular-scaffolding"/>
    </head>
    <body data-ng-app="scaffolding" data-base-url="${createLink(uri: '/event/')}">
        <a href="#list-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="row-fluid">
        	<div class="span3">
								<div class="well" role="navigation">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<a class="list" href="#list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</a>
						</li>
						<li>
							<a class="create" href="#create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="span9" role="main" data-ng-view>
			</div>
        </div>
    </body>
</html>
