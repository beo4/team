
<%@ page import="de.elementEvents.tema.resource.ResourceOption" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'resourceOption.label', default: 'ResourceOption')}" />
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
						
							<g:sortableColumn property="availableEnd" title="${message(code: 'resourceOption.availableEnd.label', default: 'Available End')}" />
						
							<g:sortableColumn property="availableStart" title="${message(code: 'resourceOption.availableStart.label', default: 'Available Start')}" />
						
							<th class="header"><g:message code="resourceOption.meeting.label" default="Meeting" /></th>
						
							<th class="header"><g:message code="resourceOption.resource.label" default="Resource" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${resourceOptionInstanceList}" var="resourceOptionInstance">
						<tr>
						
							<td>${fieldValue(bean: resourceOptionInstance, field: "availableEnd")}</td>
						
							<td>${fieldValue(bean: resourceOptionInstance, field: "availableStart")}</td>
						
							<td>${fieldValue(bean: resourceOptionInstance, field: "meeting")}</td>
						
							<td>${fieldValue(bean: resourceOptionInstance, field: "resource")}</td>
						
							<td class="link">
								<g:link action="show" id="${resourceOptionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${resourceOptionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
