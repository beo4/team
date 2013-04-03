
<%@ page import="de.elementEvents.tema.resource.ResourceOption" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'resourceOption.label', default: 'ResourceOption')}" />
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
				
					<g:if test="${resourceOptionInstance?.availableEnd}">
						<dt><g:message code="resourceOption.availableEnd.label" default="Available End" /></dt>
						
							<dd><g:fieldValue bean="${resourceOptionInstance}" field="availableEnd"/></dd>
						
					</g:if>
				
					<g:if test="${resourceOptionInstance?.availableStart}">
						<dt><g:message code="resourceOption.availableStart.label" default="Available Start" /></dt>
						
							<dd><g:fieldValue bean="${resourceOptionInstance}" field="availableStart"/></dd>
						
					</g:if>
				
					<g:if test="${resourceOptionInstance?.i18n}">
						<dt><g:message code="resourceOption.i18n.label" default="I18n" /></dt>
						
							<g:each in="${resourceOptionInstance.i18n}" var="i">
							<dd><g:link controller="resourceOption_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${resourceOptionInstance?.meeting}">
						<dt><g:message code="resourceOption.meeting.label" default="Meeting" /></dt>
						
							<dd><g:link controller="meeting" action="show" id="${resourceOptionInstance?.meeting?.id}">${resourceOptionInstance?.meeting?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${resourceOptionInstance?.resource}">
						<dt><g:message code="resourceOption.resource.label" default="Resource" /></dt>
						
							<dd><g:link controller="resource" action="show" id="${resourceOptionInstance?.resource?.id}">${resourceOptionInstance?.resource?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${resourceOptionInstance?.subscriptions}">
						<dt><g:message code="resourceOption.subscriptions.label" default="Subscriptions" /></dt>
						
							<g:each in="${resourceOptionInstance.subscriptions}" var="s">
							<dd><g:link controller="subscriptionResourceOption" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${resourceOptionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${resourceOptionInstance?.id}">
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
