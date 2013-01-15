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
					<g:formRemote name="createEvent" url="[action: 'create']" class="form-horizontal" before=""  >
						<fieldset>
							<g:message code="event.addLanguage.label" default="Add language"/>
							<div class="input-prepend input-append">
	      							<span class="add-on"><i class="icon-flag"></i></span>
									<g:localeSelect name="myLocale" value="${localeToAdd}" />
									<button class="btn btn-success" type="button" onclick="create.addLanguage($('#myLocale option:selected'));"><i class="icon-plus icon-white"></i></button>
							</div>
							<g:if test="${!eventInstance.eventLanguage}">
								<bootstrap:alert class="alert-info"><g:message code="event.addLanguage.addLangugaeFirst.label"/></bootstrap:alert>
								<ul class="nav nav-tabs hidden">
								</ul>
								<div class="tab-content hidden">
								</div>
							</g:if>
							<g:else>
							<ul class="nav nav-tabs">

							</ul>
							</g:else>
						
							<div class="form-actions">
								<button type="button" class="btn btn-primary" onclick="alert(create.getBeans());">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
							
						</fieldset>
					</g:formRemote>
				</fieldset>
				
			</div>

		</div>
		<g:javascript>
		var eventInstance = new Object(),
			content = $('.tab-content'),
			tab = $('.nav.nav-tabs'),
			create = new function() {
		
			this.addLanguage = function (source) {
				var locale;
				
				${remoteFunction(action: 'returnEventLanguage',
                       params: '\'locale=\' + source.val()',
					   onSuccess: 'create.addTab(source,data); ',
					   options: '[asynchronous: true]')}
				 
				
				//remove the alert
				$(".alert").alert('close')
				source.remove();
		    };
		    
		    this.addTab = function(source,name) {
		    	//add tab to table
			    var insertedTab = $("<a href=\"#"+source.val()+"\" data-toggle=\"tab\">"+ name.languageName + "</a>"),
			    	insertedContent = $("<div class=\"tab-pane\" id=\"" + source.val() +"\">");
			    	
					tab.removeClass('hidden').append(
						$('<li class="speech">').append(
							insertedTab
						)
					),
				
				content.removeClass('hidden').append(
							insertedContent
						);
					
				${remoteFunction(action: 'addEditEventLanguage',
					   controller: 'EventLanguage',
                       params: '\'locale=\' + source.val()',
					   onSuccess: 'create.addTabContent(insertedContent,data)')}
				
				insertedTab.tab('show');
		    };
		    
		    this.addTabContent= function(target, content) {
		    	target.html(content);
		    };
		    
		    this.getBeans = function() {
		    	$(".tab-pane").each(function(index, para) {
		    		var language =  $(para).find('#'+$(para).attr('id')+'\\.language').val(),
		    			languageName = $(para).find('#'+$(para).attr('id')+'\\.languageName').val();
		    			
		    		
		    		eventInstance[$(para).attr('id')] = new Object();
		    		
		    	});
		    };
		    
		    
		}
		</g:javascript>
	</body>
</html>
