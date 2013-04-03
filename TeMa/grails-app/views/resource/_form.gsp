<%@ page import="de.elementEvents.tema.resource.Resource" %>



<div class="fieldcontain ${hasErrors(bean: resourceInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="resource.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${resourceInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="resource.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${de.elementEvents.tema.event.Event.list()}" optionKey="id" required="" value="${resourceInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceInstance, field: 'i18n', 'error')} ">
	<label for="i18n">
		<g:message code="resource.i18n.label" default="I18n" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${resourceInstance?.i18n?}" var="i">
    <li><g:link controller="resource_i18n" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="resource_i18n" action="create" params="['resource.id': resourceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'resource_i18n.label', default: 'Resource_i18n')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: resourceInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="resource.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${resourceInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceInstance, field: 'options', 'error')} ">
	<label for="options">
		<g:message code="resource.options.label" default="Options" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${resourceInstance?.options?}" var="o">
    <li><g:link controller="resourceOption" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="resourceOption" action="create" params="['resource.id': resourceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'resourceOption.label', default: 'ResourceOption')])}</g:link>
</li>
</ul>

</div>

