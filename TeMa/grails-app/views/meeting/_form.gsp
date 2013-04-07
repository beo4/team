<%@ page import="de.elementEvents.tema.meeting.Meeting" %>



<div class="fieldcontain ${hasErrors(bean: meetingInstance, field: 'end', 'error')} required">
	<label for="end">
		<g:message code="meeting.end.label" default="End" />
		<span class="required-indicator">*</span>
	</label>
	<joda:dateTimePicker name="end" value="${meetingInstance?.end}" ></joda:dateTimePicker>
</div>

<div class="fieldcontain ${hasErrors(bean: meetingInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="meeting.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${de.elementEvents.tema.event.Event.list()}" optionKey="id" required="" value="${meetingInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meetingInstance, field: 'i18n', 'error')} ">
	<label for="i18n">
		<g:message code="meeting.i18n.label" default="I18n" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${meetingInstance?.i18n?}" var="i">
    <li><g:link controller="meeting_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="meeting_i18n" action="create" params="['meeting.id': meetingInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'meeting_i18n.label', default: 'Meeting_i18n')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: meetingInstance, field: 'start', 'error')} required">
	<label for="start">
		<g:message code="meeting.start.label" default="Start" />
		<span class="required-indicator">*</span>
	</label>
	<joda:dateTimePicker name="start" value="${meetingInstance?.start}" ></joda:dateTimePicker>
</div>

<div class="fieldcontain ${hasErrors(bean: meetingInstance, field: 'subscriber', 'error')} ">
	<label for="subscriber">
		<g:message code="meeting.subscriber.label" default="Subscriber" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${meetingInstance?.subscriber?}" var="s">
    <li><g:link controller="registration" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="registration" action="create" params="['meeting.id': meetingInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'subscription.label', default: 'Subscription')])}</g:link>
</li>
</ul>

</div>

