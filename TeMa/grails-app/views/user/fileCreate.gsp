<%@ page import="de.elementEvents.tema.user.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
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
						<li class="active">
							<g:link class="create" action="fileCreate">
								<i class="icon-plus icon-white"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.create.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${userInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${userInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				
				<g:if test="${event}">
						<dt><g:message code="event.title.label" default="Event Titel" /></dt>
						
							<dd><g:fieldValue bean="${event}" field="defaultTitle"/></dd>
						
				</g:if>		
				
				<g:if test="${meeting}">
						<dt><g:message code="meeting.title.label" default="Meeting Titel" /></dt>
						
							<dd><g:fieldValue bean="${meeting}" field="defaultTitle"/></dd>
							
							<dt><g:message code="event.subtitle.label" default="Meeting Subtitel" /></dt>
						
							<dd><g:fieldValue bean="${meeting}" field="defaultSubtitle"/></dd>
						
				</g:if>			
			
				<fieldset>
					<g:uploadForm name="myUpload" action="createFromFile">
						<g:hiddenField name="eventId" value="${eventId}"/>
						<g:hiddenField name="meetingId" value="${meetingId}"/>
					    <input type="file" name="file" />
					    <g:submitButton name="upload"/>
					</g:uploadForm>
				</fieldset>
				
			</div>

		</div>
	</body>
</html>
