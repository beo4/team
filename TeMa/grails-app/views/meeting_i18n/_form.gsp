<%@ page import="de.elementEvents.tema.meeting.Meeting_i18n" %>



<div class="fieldcontain ${hasErrors(bean: meeting_i18nInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="meeting_i18n.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${meeting_i18nInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meeting_i18nInstance, field: 'i18n', 'error')} required">
	<label for="i18n">
		<g:message code="meeting_i18n.i18n.label" default="I18n" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="i18n" name="i18n.id" from="${de.elementEvents.tema.event.EventLanguage.list()}" optionKey="id" required="" value="${meeting_i18nInstance?.i18n?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meeting_i18nInstance, field: 'meeting', 'error')} required">
	<label for="meeting">
		<g:message code="meeting_i18n.meeting.label" default="Meeting" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="meeting" name="meeting.id" from="${de.elementEvents.tema.meeting.Meeting.list()}" optionKey="id" required="" value="${meeting_i18nInstance?.meeting?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meeting_i18nInstance, field: 'subtitle', 'error')} ">
	<label for="subtitle">
		<g:message code="meeting_i18n.subtitle.label" default="Subtitle" />
		
	</label>
	<g:textField name="subtitle" value="${meeting_i18nInstance?.subtitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meeting_i18nInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="meeting_i18n.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${meeting_i18nInstance?.title}"/>
</div>

