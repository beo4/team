
<%@ page import="de.elementEvents.tema.event.Event" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${eventInstance?.eventLanguage}">
						<dt><g:message code="event.eventLanguage.label" default="Event Language" /></dt>
						
							<g:each in="${eventInstance.eventLanguage}" var="e">
							<dd><g:link controller="eventLanguage" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${eventInstance?.i18n}">
						<dt><g:message code="event.i18n.label" default="I18n" /></dt>
						
							<g:each in="${eventInstance.i18n}" var="i">
							<dd><g:link controller="event_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${eventInstance?.meetings}">
						<dt><g:message code="event.meetings.label" default="Meetings" /></dt>
						
							<g:each in="${eventInstance.meetings}" var="m">
							<dd><g:link controller="meeting" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${eventInstance?.participants}">
						<dt><g:message code="event.participants.label" default="Participants" /></dt>
						
							<g:each in="${eventInstance.participants}" var="p">
							<dd><g:link controller="user" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${eventInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${eventInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
