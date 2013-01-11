
<%@ page import="de.elementEvents.tema.event.EventLanguage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'eventLanguage.label', default: 'EventLanguage')}" />
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
				
					<g:if test="${eventLanguageInstance?.event}">
						<dt><g:message code="eventLanguage.event.label" default="Event" /></dt>
						
							<dd><g:link controller="event" action="show" id="${eventLanguageInstance?.event?.id}">${eventLanguageInstance?.event?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${eventLanguageInstance?.language}">
						<dt><g:message code="eventLanguage.language.label" default="Language" /></dt>
						
							<dd><g:fieldValue bean="${eventLanguageInstance}" field="language"/></dd>
						
					</g:if>
				
					<g:if test="${eventLanguageInstance?.languageName}">
						<dt><g:message code="eventLanguage.languageName.label" default="Language Name" /></dt>
						
							<dd><g:fieldValue bean="${eventLanguageInstance}" field="languageName"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${eventLanguageInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${eventLanguageInstance?.id}">
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
