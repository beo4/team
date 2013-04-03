
<%@ page import="de.elementEvents.tema.resource.ResourceOption_i18n" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'resourceOption_i18n.label', default: 'ResourceOption_i18n')}" />
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
				
					<g:if test="${resourceOption_i18nInstance?.i18n}">
						<dt><g:message code="resourceOption_i18n.i18n.label" default="I18n" /></dt>
						
							<dd><g:link controller="eventLanguage" action="show" id="${resourceOption_i18nInstance?.i18n?.id}">${resourceOption_i18nInstance?.i18n?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${resourceOption_i18nInstance?.language}">
						<dt><g:message code="resourceOption_i18n.language.label" default="Language" /></dt>
						
							<dd><g:fieldValue bean="${resourceOption_i18nInstance}" field="language"/></dd>
						
					</g:if>
				
					<g:if test="${resourceOption_i18nInstance?.name}">
						<dt><g:message code="resourceOption_i18n.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${resourceOption_i18nInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${resourceOption_i18nInstance?.resourceOption}">
						<dt><g:message code="resourceOption_i18n.resourceOption.label" default="Resource Option" /></dt>
						
							<dd><g:link controller="resourceOption" action="show" id="${resourceOption_i18nInstance?.resourceOption?.id}">${resourceOption_i18nInstance?.resourceOption?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${resourceOption_i18nInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${resourceOption_i18nInstance?.id}">
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
