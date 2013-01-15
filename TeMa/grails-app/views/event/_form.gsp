<%@ page import="de.elementEvents.tema.event.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'eventLanguage', 'error')} ">
	<label for="eventLanguage">
		<g:message code="event.eventLanguage.label" default="Event Language" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.eventLanguage?}" var="e">
    <li><g:link controller="eventLanguage" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="eventLanguage" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'eventLanguage.label', default: 'EventLanguage')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'i18n', 'error')} ">
	<label for="i18n">
		<g:message code="event.i18n.label" default="I18n" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.i18n?}" var="i">
    <li><g:link controller="event_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="event_i18n" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'event_i18n.label', default: 'Event_i18n')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'meetings', 'error')} ">
	<label for="meetings">
		<g:message code="event.meetings.label" default="Meetings" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.meetings?}" var="m">
    <li><g:link controller="meeting" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="meeting" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'meeting.label', default: 'Meeting')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'participants', 'error')} ">
	<label for="participants">
		<g:message code="event.participants.label" default="Participants" />
		
	</label>
	<g:select name="participants" from="${de.elementEvents.tema.user.User.list()}" multiple="multiple" optionKey="id" size="5" value="${eventInstance?.participants*.id}" class="many-to-many"/>
</div>

