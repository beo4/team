
<%@ page import="de.elementEvents.tema.resource.Resource" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'resource.label', default: 'Resource')}" />
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
				
					<g:if test="${resourceInstance?.description}">
						<dt><g:message code="resource.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${resourceInstance}" field="description"/></dd>
						
					</g:if>
				
					<g:if test="${resourceInstance?.event}">
						<dt><g:message code="resource.event.label" default="Event" /></dt>
						
							<dd><g:link controller="event" action="show" id="${resourceInstance?.event?.id}">${resourceInstance?.event?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${resourceInstance?.i18n}">
						<dt><g:message code="resource.i18n.label" default="I18n" /></dt>
						
							<g:each in="${resourceInstance.i18n}" var="i">
							<dd><g:link controller="resource_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${resourceInstance?.name}">
						<dt><g:message code="resource.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${resourceInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${resourceInstance?.options}">
						<dt><g:message code="resource.options.label" default="Options" /></dt>
						
							<g:each in="${resourceInstance.options}" var="o">
							<dd><g:link controller="resourceOption" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${resourceInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${resourceInstance?.id}">
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
