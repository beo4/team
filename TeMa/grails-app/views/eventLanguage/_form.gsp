<%@ page import="de.elementEvents.tema.event.EventLanguage" %>



<div class="fieldcontain ${hasErrors(bean: eventLanguageInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="eventLanguage.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${de.elementEvents.tema.event.Event.list()}" optionKey="id" required="" value="${eventLanguageInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventLanguageInstance, field: 'language', 'error')} required">
	<label for="language">
		<g:message code="eventLanguage.language.label" default="Language" />
		<span class="required-indicator">*</span>
	</label>
	<g:localeSelect name="language" value="${eventLanguageInstance?.language}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventLanguageInstance, field: 'languageName', 'error')} ">
	<label for="languageName">
		<g:message code="eventLanguage.languageName.label" default="Language Name" />
		
	</label>
	<g:textField name="languageName" value="${eventLanguageInstance?.languageName}"/>
</div>

