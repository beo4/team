
<%@ page import="de.elementEvents.tema.event.Event_i18n" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'event_i18n.label', default: 'Event_i18n')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="description" title="${message(code: 'event_i18n.description.label', default: 'Description')}" />
						
							<th class="header"><g:message code="event_i18n.event.label" default="Event" /></th>
						
							<th class="header"><g:message code="event_i18n.i18n.label" default="I18n" /></th>
						
							<g:sortableColumn property="subtitle" title="${message(code: 'event_i18n.subtitle.label', default: 'Subtitle')}" />
						
							<g:sortableColumn property="title" title="${message(code: 'event_i18n.title.label', default: 'Title')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${event_i18nInstanceList}" var="event_i18nInstance">
						<tr>
						
							<td>${fieldValue(bean: event_i18nInstance, field: "description")}</td>
						
							<td>${fieldValue(bean: event_i18nInstance, field: "event")}</td>
						
							<td>${fieldValue(bean: event_i18nInstance, field: "i18n")}</td>
						
							<td>${fieldValue(bean: event_i18nInstance, field: "subtitle")}</td>
						
							<td>${fieldValue(bean: event_i18nInstance, field: "title")}</td>
						
							<td class="link">
								<g:link action="show" id="${event_i18nInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${event_i18nInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
