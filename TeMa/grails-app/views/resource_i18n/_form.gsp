<%@ page import="de.elementEvents.tema.resource.Resource_i18n" %>



<div class="fieldcontain ${hasErrors(bean: resource_i18nInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="resource_i18n.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${resource_i18nInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resource_i18nInstance, field: 'i18n', 'error')} required">
	<label for="i18n">
		<g:message code="resource_i18n.i18n.label" default="I18n" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="i18n" name="i18n.id" from="${de.elementEvents.tema.event.EventLanguage.list()}" optionKey="id" required="" value="${resource_i18nInstance?.i18n?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resource_i18nInstance, field: 'language', 'error')} required">
	<label for="language">
		<g:message code="resource_i18n.language.label" default="Language" />
		<span class="required-indicator">*</span>
	</label>
	<g:localeSelect name="language" value="${resource_i18nInstance?.language}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: resource_i18nInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="resource_i18n.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${resource_i18nInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resource_i18nInstance, field: 'resource', 'error')} required">
	<label for="resource">
		<g:message code="resource_i18n.resource.label" default="Resource" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="resource" name="resource.id" from="${de.elementEvents.tema.resource.Resource.list()}" optionKey="id" required="" value="${resource_i18nInstance?.resource?.id}" class="many-to-one"/>
</div>

