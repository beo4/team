
<%@ page import="de.elementEvents.tema.meeting.Meeting" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'meeting.label', default: 'Meeting')}" />
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
				
					<g:if test="${meetingInstance?.end}">
						<dt><g:message code="meeting.end.label" default="End" /></dt>
						
							<dd><g:fieldValue bean="${meetingInstance}" field="end"/></dd>
						
					</g:if>
				
					<g:if test="${meetingInstance?.event}">
						<dt><g:message code="meeting.event.label" default="Event" /></dt>
						
							<dd><g:link controller="event" action="show" id="${meetingInstance?.event?.id}">${meetingInstance?.event?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${meetingInstance?.i18n}">
						<dt><g:message code="meeting.i18n.label" default="I18n" /></dt>
						
							<g:each in="${meetingInstance.i18n}" var="i">
							<dd><g:link controller="meeting_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${meetingInstance?.start}">
						<dt><g:message code="meeting.start.label" default="Start" /></dt>
						
							<dd><g:fieldValue bean="${meetingInstance}" field="start"/></dd>
						
					</g:if>
				
					<g:if test="${meetingInstance?.subscriber}">
						<dt><g:message code="meeting.subscriber.label" default="Subscriber" /></dt>
						
							<g:each in="${meetingInstance.subscriber}" var="s">
							<dd><g:link controller="registration" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${meetingInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${meetingInstance?.id}">
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
