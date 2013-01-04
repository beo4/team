<%@ page import="de.elementEvents.tema.event.Event" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils as RCU" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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
							<g:link class="create" action="create">
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

				<g:hasErrors bean="${eventInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${eventInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				


				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
							<g:message code="event.addLanguage.label" default="Add language"/>
							<div class="input-prepend input-append">
	      							<span class="add-on"><i class="icon-flag"></i></span>
									<g:localeSelect name="myLocale" value="${localeToAdd}" />
									<button class="btn btn-success" type="button" onclick="create.addLanguage($('#myLocale option:selected'));"><i class="icon-plus icon-white"></i></button>
							</div>
							<g:if test="${!eventInstance.eventLanguage}">
								<bootstrap:alert class="alert-info"><g:message code="event.addLanguage.addLangugaeFirst.label"/></bootstrap:alert>
								<ul class="nav nav-pills hidden">
								</ul>
							</g:if>
							<g:else>
							<ul class="nav nav-pills">
							  <li><a href="#home" data-toggle="tab">Home</a></li>
							  <li><a href="#profile" data-toggle="tab">Profile</a></li>
							  <li><a href="#messages" data-toggle="tab">Messages</a></li>
							  <li><a href="#settings" data-toggle="tab">Settings</a></li>
							</ul>
							</g:else>
						
							<f:all bean="eventInstance"/>
							
							
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
				
			</div>

		</div>
		<g:javascript>
		var create = new function() {
		    this.event = ${eventInstance};
		
			this.addLanguage = function (source) {
				var locale,
				//add a tab for the language
				tab = $('.nav.nav-pills');
				
				${remoteFunction(action: 'returnEventLanguage',
                       params: '\'locale=\' + source.val()',
					   onSuccess: 'create.addTab(tab,source,data); ',
					   options: '[asynchronous: false]')}
				 
				
				//remove the alert
				$(".alert").alert('close')
				source.remove();
		    };
		    
		    this.addTab = function(tab,source,name) {
		    	//add tab to table
			    insertedTab = $("<a href=\""+source.val()+"\" data-toggle=\"tab\">"+ name.languageName + "</a>");
					tab.removeClass('hidden').append(
						$('<li>').append(
							insertedTab
						)
					);
				insertedTab.tab('show');
		    }
		    
		    
		}
		</g:javascript>
	</body>
</html>
