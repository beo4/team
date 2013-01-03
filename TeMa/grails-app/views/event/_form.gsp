<%@ page import="de.elementEvents.tema.event.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="event.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${eventInstance?.description}"/>
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

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'subtitle', 'error')} ">
	<label for="subtitle">
		<g:message code="event.subtitle.label" default="Subtitle" />
		
	</label>
	<g:textField name="subtitle" value="${eventInstance?.subtitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="event.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${eventInstance?.title}"/>
</div>

