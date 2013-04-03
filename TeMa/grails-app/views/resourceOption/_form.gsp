<%@ page import="de.elementEvents.tema.resource.ResourceOption" %>



<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'availableEnd', 'error')} required">
	<label for="availableEnd">
		<g:message code="resourceOption.availableEnd.label" default="Available End" />
		<span class="required-indicator">*</span>
	</label>
	<joda:dateTimePicker name="availableEnd" value="${resourceOptionInstance?.availableEnd}" ></joda:dateTimePicker>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'availableStart', 'error')} required">
	<label for="availableStart">
		<g:message code="resourceOption.availableStart.label" default="Available Start" />
		<span class="required-indicator">*</span>
	</label>
	<joda:dateTimePicker name="availableStart" value="${resourceOptionInstance?.availableStart}" ></joda:dateTimePicker>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'i18n', 'error')} ">
	<label for="i18n">
		<g:message code="resourceOption.i18n.label" default="I18n" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${resourceOptionInstance?.i18n?}" var="i">
    <li><g:link controller="resourceOption_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="resourceOption_i18n" action="create" params="['resourceOption.id': resourceOptionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'resourceOption_i18n.label', default: 'ResourceOption_i18n')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'meeting', 'error')} required">
	<label for="meeting">
		<g:message code="resourceOption.meeting.label" default="Meeting" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="meeting" name="meeting.id" from="${de.elementEvents.tema.meeting.Meeting.list()}" optionKey="id" required="" value="${resourceOptionInstance?.meeting?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'resource', 'error')} required">
	<label for="resource">
		<g:message code="resourceOption.resource.label" default="Resource" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="resource" name="resource.id" from="${de.elementEvents.tema.resource.Resource.list()}" optionKey="id" required="" value="${resourceOptionInstance?.resource?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOptionInstance, field: 'subscriptions', 'error')} ">
	<label for="subscriptions">
		<g:message code="resourceOption.subscriptions.label" default="Subscriptions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${resourceOptionInstance?.subscriptions?}" var="s">
    <li><g:link controller="subscriptionResourceOption" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="subscriptionResourceOption" action="create" params="['resourceOption.id': resourceOptionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'subscriptionResourceOption.label', default: 'SubscriptionResourceOption')])}</g:link>
</li>
</ul>

</div>

