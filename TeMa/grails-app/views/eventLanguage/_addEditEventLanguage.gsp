
		<g:set var="entityName" value="${message(code: 'eventLanguage.label', default: 'EventLanguage')}" />
			<div class="span9">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${eventLanguageInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${eventLanguageInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				<fieldset>
					<f:all bean="eventLanguageInstance" except="event" prefix="${eventLanguageInstance?.language.toString()}"/>
				</fieldset>
			</div>
