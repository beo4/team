<%@ page import="de.elementEvents.tema.event.Event_i18n" %>



<div class="fieldcontain ${hasErrors(bean: event_i18nInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="event_i18n.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${event_i18nInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: event_i18nInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="event_i18n.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${de.elementEvents.tema.event.Event.list()}" optionKey="id" required="" value="${event_i18nInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: event_i18nInstance, field: 'i18n', 'error')} required">
	<label for="i18n">
		<g:message code="event_i18n.i18n.label" default="I18n" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="i18n" name="i18n.id" from="${de.elementEvents.tema.event.EventLanguage.list()}" optionKey="id" required="" value="${event_i18nInstance?.i18n?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: event_i18nInstance, field: 'subtitle', 'error')} ">
	<label for="subtitle">
		<g:message code="event_i18n.subtitle.label" default="Subtitle" />
		
	</label>
	<g:textField name="subtitle" value="${event_i18nInstance?.subtitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: event_i18nInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="event_i18n.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${event_i18nInstance?.title}"/>
</div>

